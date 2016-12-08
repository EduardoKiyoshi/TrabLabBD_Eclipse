package consultasProgramas;

import emissoralabbd.MainApp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

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

import model.ExibicaoJornal;
import model.ExibicaoJornalDAO;
import model.Jornal;



public class ExibicoesJornaisController {


	@FXML
    private TableView<ExibicaoJornal> exibeJornaisTable;
    @FXML
    private TableColumn<ExibicaoJornal, Integer> funcionarioColumn;
    @FXML
    private TableColumn<ExibicaoJornal, String> dataColumn;
    @FXML
    private TableColumn<ExibicaoJornal, String> horaInicioColumn;
    @FXML
    private TableColumn<ExibicaoJornal, String> horaFimColumn;
    @FXML
    private TableColumn<ExibicaoJornal, Integer> ibopeColumn;
   

    
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
    	funcionarioColumn.setCellValueFactory(
                cellData -> cellData.getValue().idFuProperty().asObject());
    	dataColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataExJoProperty());
    	horaInicioColumn.setCellValueFactory(
                cellData -> cellData.getValue().horaInicioExJoProperty());
    	horaFimColumn.setCellValueFactory(
                cellData -> cellData.getValue().horaFimExJoProperty());
    	ibopeColumn.setCellValueFactory(
                cellData -> cellData.getValue().ibopeExJoProperty().asObject());


    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp, Jornal selectedJornal) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        ExibicaoJornalDAO dao = new ExibicaoJornalDAO(conn);
        try{
        	exibeJornaisTable.setItems(dao.findExibicaoJornal(selectedJornal));        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
    }
    
    @FXML
    private void handleInsertExibicaoJornal() {
        //mainApp.showInsertExibicaoJornal();    
        
    }
    @FXML
    private void handleUpdateExibicaoJornal() {
        ExibicaoJornal selectedExibicaoJornal = exibeJornaisTable.getSelectionModel().getSelectedItem();
        if (selectedExibicaoJornal != null) {
           //mainApp.showUpdateExibicaoJornal(selectedExibicaoJornal);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No news program Selected");
            alert.setContentText("Please select a news program in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteExibicaoJornal() {
    	Connection conn = DBconnection.getConexao();
    	ExibicaoJornalDAO dao = null;
        ExibicaoJornal selectedExibicaoJornal = exibeJornaisTable.getSelectionModel().getSelectedItem();
        int selectedIndex = exibeJornaisTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedExibicaoJornal != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de ExibicaoJornal");
            alert.setHeaderText("Exlusao de ExibicaoJornal");
            alert.setContentText("Tem certeza que deseja excluir esse jornal?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new ExibicaoJornalDAO(conn);
	        		dao.deleteExibicaoJornal(selectedExibicaoJornal);
	        		exibeJornaisTable.getItems().remove(selectedIndex);
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
            alert.setHeaderText("No news program Selected");
            alert.setContentText("Please select a news program in the table.");
            alert.showAndWait();
        }
    }
}
