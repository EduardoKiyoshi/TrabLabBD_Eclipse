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

import model.Comercial;
import model.ComercialDAO;
import model.Jornal;
import model.JornalDAO;


public class TodosComerciaisController {
	
	@FXML
    private TableView<Comercial> comercialTable;
    @FXML
    private TableColumn<Comercial, String> tituloColumn;
    @FXML
    private TableColumn<Comercial, Integer> departamentoColumn;
    @FXML
    private TableColumn<Comercial, String> descricaoColumn;
   

    
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
        ComercialDAO dao = new ComercialDAO(conn);
        try{
        	comercialTable.setItems(dao.findAll());        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
    }
    
    @FXML
    private void handleInsertComercial() {
        //mainApp.showInsertComercial();    
        
    }
    @FXML
    private void handleUpdateComercial() {
        Comercial selectedComercial = comercialTable.getSelectionModel().getSelectedItem();
        if (selectedComercial != null) {
           //mainApp.showUpdateComercial(selectedComercial);
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Comercial Selected");
            alert.setContentText("Please select a comercial in the table.");
            
            alert.showAndWait();
        }
    }
    
    @FXML
    private void handleDeleteComercial() {
    	Connection conn = DBconnection.getConexao();
    	ComercialDAO dao = null;
        Comercial selectedComercial = comercialTable.getSelectionModel().getSelectedItem();
        int selectedIndex = comercialTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        if (selectedComercial != null) {
        	// Nothing selected.
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Comercial");
            alert.setHeaderText("Exlusao de Comercial");
            alert.setContentText("Tem certeza que deseja excluir esse funcionario?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
        		try{
	        		dao = new ComercialDAO(conn);
	        		dao.deleteComercial(selectedComercial.getIdPr());
	        		comercialTable.getItems().remove(selectedIndex);
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
            alert.setHeaderText("No Comercial Selected");
            alert.setContentText("Please select a comercial in the table.");
            alert.showAndWait();
        }
    }

}
