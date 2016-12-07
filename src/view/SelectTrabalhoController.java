package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import emissoralabbd.MainApp;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Trabalho;
import model.TrabalhoDAO;
import util.DBconnection;
import util.ErrorHandler;

public class SelectTrabalhoController {
	@FXML
    private TableView<Trabalho> trabalhoTable = new TableView<Trabalho>();
    @FXML
    private TableColumn<Trabalho, Integer> idDeColumn;
    @FXML
    private TableColumn<Trabalho, Integer> idFuColumn;    
    @FXML
    private TableColumn<Trabalho, LocalDate> dataInicioTrColumn;
    @FXML
    private TableColumn<Trabalho, LocalDate> dataTerminoTrColumn;
    @FXML
    private ChoiceBox departamento;
    @FXML
    private TextField dataInicio;
    @FXML
    private TextField dataTermino;
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
    	idFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().idFuProperty().asObject());
    	idDeColumn.setCellValueFactory(
                cellData -> cellData.getValue().idDeProperty().asObject());
    	dataInicioTrColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataInicioTrProperty());
    	dataTerminoTrColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataFimTrProperty());
    	

        // Clear person details.
        //showPersonDetails(null);
    	/*
        // Listen for selection changes and show the person details when changed.
    	trabalhoTable.getSelectionModel().selectedItemProperty().addListener(
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
        TrabalhoDAO daoTr = new TrabalhoDAO(conn);              
        try{        	
        	trabalhoTable.setItems(daoTr.find(funcionario));        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
        
    }
    
    @FXML
    private void handleAddTrabalho() {
        Trabalho selectedTrabalho = trabalhoTable.getSelectionModel().getSelectedItem();
        if (funcionario != null) {
            boolean okClicked = mainApp.showInsertTrabalho(funcionario);
            
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Funcionario Selected");
            alert.setContentText("Please select a Funcionario in the table.");

            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleUpdateTrabalho() {
        Trabalho selectedTrabalho = trabalhoTable.getSelectionModel().getSelectedItem();
        if (selectedTrabalho != null) {
           mainApp.showUpdateTrabalho(selectedTrabalho);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Trabalho Selected");
            alert.setContentText("Please select a Trabalho in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteTrabalho() {
    	Connection conn = DBconnection.getConexao();
    	TrabalhoDAO dao = null;
    	FuncionarioDAO daoFu = null;
        Trabalho selectedTrabalho = trabalhoTable.getSelectionModel().getSelectedItem();
        int selectedIndex = trabalhoTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedTrabalho != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Trabalho");
            alert.setHeaderText("Exlusao de Trabalho");
            alert.setContentText("Tem certeza que deseja excluir esse trabalho?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new TrabalhoDAO(conn);
	        		dao.deleteTrabalho(selectedTrabalho.getIdFu());
	        		trabalhoTable.getItems().remove(selectedIndex);
	        		
	        		if(Bindings.isEmpty(trabalhoTable.getItems()).get() == true){
	        			System.out.println("Tabela vazia");
	        			daoFu = new FuncionarioDAO(conn);
	        			daoFu.deleteFuncionario(funcionario.getIdFu());
	        		}
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
    
    public void setFuncionario(Funcionario funcionario){
    	this.funcionario = funcionario;    	
    }
    @FXML
    private void handleBackButton() {
        mainApp.showFuncionarioOverview();
    }
}
