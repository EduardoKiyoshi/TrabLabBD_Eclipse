package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import emissoralabbd.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import util.DBconnection;
import util.ErrorHandler;

public class SelectDependenteController {
	@FXML
    private TableView<Dependente> DependenteTable = new TableView<Dependente>();
    @FXML
    private TableColumn<Dependente, String> nomeColumn;
    @FXML
    private TableColumn<Dependente, LocalDate> dataNascColumn;
    @FXML
    private TableColumn<Dependente, String> sexoColumn;
    
 // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;
    private Funcionario funcionario;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        
        
        // Initialize the person table with the two columns.
    	nomeColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeCompletoDeProperty());
    	dataNascColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataNascimentoDeProperty());
    	sexoColumn.setCellValueFactory(
                cellData -> cellData.getValue().sexoDeProperty());

    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private void handleAddDependente() {
        //Dependente selectedDependente = DependenteTable.getSelectionModel().getSelectedItem();
        boolean okClicked = mainApp.showDependenteAddDialog(funcionario);
    }
    @FXML
    private void handleDeleteDependente() {
    	Connection conn = DBconnection.getConexao();
    	DependenteDAO dao = null;
        Dependente selectedDependente = DependenteTable.getSelectionModel().getSelectedItem();
        int selectedIndex = DependenteTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedDependente != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Dependente");
            alert.setHeaderText("Exlusao de Dependente");
            alert.setContentText("Tem certeza que deseja excluir esse Dependente?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new DependenteDAO(conn);
	        		dao.deleteDependente(selectedDependente.getIdFu(), selectedDependente.getNomeCompletoDe());
	        		DependenteTable.getItems().remove(selectedIndex);
        		}catch (SQLException sqlex) {
                	errorMessage += sqlex.getErrorCode();
        			System.out.println("SQL Error" + sqlex);		    
        		}
        	}
        	if (errorMessage.length() == 0) {
                alert = new Alert(AlertType.CONFIRMATION); 
                alert.initOwner(dialogStage);
                alert.setTitle("Exclusao Confirmada");
                alert.setHeaderText("Sucesso na exclusao");
                alert.showAndWait();
                
                dialogStage.close();
                
            } else {
                // Show the error message.
                alert = new Alert(AlertType.ERROR);
                alert.initOwner(dialogStage);
                alert.setTitle("Invalid Fields");
                alert.setHeaderText("Please correct invalid fields");
                alert.setContentText(ErrorHandler.getMessage(Integer.parseInt(errorMessage)));
                
                alert.showAndWait();
            }

            alert.showAndWait();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleUpdateDependente() {
        Dependente selectedDependente = DependenteTable.getSelectionModel().getSelectedItem();
        if (selectedDependente != null) {
           mainApp.showUpdateDependente(selectedDependente);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    /*
    @FXML
    private void handleConsultarDependente() {
        Dependente selectedDependente = DependenteTable.getSelectionModel().getSelectedItem();
        if (selectedDependente != null) {
           mainApp.showSelectDependente(selectedDependente);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            
            alert.showAndWait();
        }
    }
    
    }*/
    
    public void setFuncionario(Funcionario func) {
        this.funcionario = func;
     // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        DependenteDAO dao = new DependenteDAO(conn);
        try{
        	DependenteTable.setItems(dao.find(funcionario));        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
    }
    
}
