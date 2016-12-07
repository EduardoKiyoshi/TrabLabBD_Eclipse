package view;

import emissoralabbd.MainApp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FuncionarioOverviewController {
	@FXML
    private TableView<Funcionario> funcionarioTable;
    @FXML
    private TableColumn<Funcionario, Integer> idFuColumn;
    @FXML
    private TableColumn<Funcionario, String> nomeColumn;
    @FXML
    private TableColumn<Funcionario, LocalDate> dataNascColumn;
    @FXML
    private TableColumn<Funcionario, String> cpfFuColumn;
    @FXML
    private TableColumn<Funcionario, String> salarioFuColumn;
    @FXML
    private TableColumn<Funcionario, String> descricaoTipoFuColumn;
    
 // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	idFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().idFuProperty().asObject());
    	nomeColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeCompletoFuProperty());
    	dataNascColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataNascimentoFuProperty());
    	cpfFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().cpfFuProperty());
    	salarioFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().salarioFuProperty());
    	descricaoTipoFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().descricaoTipoFuProperty());

        // Clear person details.
        //showPersonDetails(null);
    	/*
        // Listen for selection changes and show the person details when changed.
    	funcionarioTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        */
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

        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        FuncionarioDAO dao = new FuncionarioDAO(conn);
        try{
        	funcionarioTable.setItems(dao.findAll());        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
        
    }
    @FXML
    private void handleSelectDependente() {
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        if (selectedFuncionario != null) {
            boolean okClicked = mainApp.showSelectDependente(selectedFuncionario);

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
    private void handleInsertFuncionario() {
        mainApp.showInsertFuncionario();    
        
    }
    @FXML
    private void handleUpdateFuncionario() {
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        if (selectedFuncionario != null) {
           mainApp.showUpdateFuncionario(selectedFuncionario);
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
    private void handleConsultarDependente() {
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        if (selectedFuncionario != null) {
           mainApp.showSelectDependente(selectedFuncionario);
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
    private void handleConsultarTrabalho() {
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        if (selectedFuncionario != null) {
           mainApp.showSelectTrabalho(selectedFuncionario);
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
    private void handleDeleteFuncionario() {
    	Connection conn = DBconnection.getConexao();
    	FuncionarioDAO dao = null;
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        int selectedIndex = funcionarioTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedFuncionario != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Funcionario");
            alert.setHeaderText("Exlusao de Funcionario");
            alert.setContentText("Tem certeza que deseja excluir esse funcionario?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new FuncionarioDAO(conn);
	        		dao.deleteFuncionario(selectedFuncionario.getIdFu());
	        		funcionarioTable.getItems().remove(selectedIndex);
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
    
}
