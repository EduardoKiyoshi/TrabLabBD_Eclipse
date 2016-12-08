package consultasProgramas;

import emissoralabbd.MainApp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
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

import model.Jornal;
import model.JornalDAO;



public class TodosJornaisController {
	
	@FXML
    private TableView<Jornal> jornaisTable;
    @FXML
    private TableColumn<Jornal, String> tituloColumn;
    @FXML
    private TableColumn<Jornal, Integer> departamentoColumn;
    @FXML
    private TableColumn<Jornal, String> abrangenciaColumn;
    @FXML
    private TableColumn<Jornal, Integer> duracaoColumn;
    @FXML
    private TableColumn<Jornal, String> descricaoColumn;
   

    
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
    	tituloColumn.setCellValueFactory(
                cellData -> cellData.getValue().tituloPrProperty());
    	departamentoColumn.setCellValueFactory(
                cellData -> cellData.getValue().idDeProperty().asObject());
    	abrangenciaColumn.setCellValueFactory(
                cellData -> cellData.getValue().abrangenciaJoProperty());
    	duracaoColumn.setCellValueFactory(
                cellData -> cellData.getValue().duracaoJoProperty().asObject());
    	descricaoColumn.setCellValueFactory(
                cellData -> cellData.getValue().descricaoPrProperty());


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
        JornalDAO dao = new JornalDAO(conn);
        try{
        	jornaisTable.setItems(dao.findAll());        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
    }
    
    @FXML
    private void handleInsertJornal() {
        //mainApp.showInsertJornal();    
        
    }
    @FXML
    private void handleUpdateJornal() {
        Jornal selectedJornal = jornaisTable.getSelectionModel().getSelectedItem();
        if (selectedJornal != null) {
           //mainApp.showUpdateJornal(selectedJornal);
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
    private void handleDeleteJornal() {
    	Connection conn = DBconnection.getConexao();
    	JornalDAO dao = null;
        Jornal selectedJornal = jornaisTable.getSelectionModel().getSelectedItem();
        int selectedIndex = jornaisTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedJornal != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Jornal");
            alert.setHeaderText("Exlusao de Jornal");
            alert.setContentText("Tem certeza que deseja excluir esse jornal?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new JornalDAO(conn);
	        		dao.deleteJornal(selectedJornal.getIdPr());
	        		jornaisTable.getItems().remove(selectedIndex);
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
