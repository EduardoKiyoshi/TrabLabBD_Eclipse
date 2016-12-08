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

import model.ExibicaoComercial;
import model.ExibicaoComercialDAO;
import model.Comercial;

public class ExibicoesComerciaisController {
	
	@FXML
    private TableView<ExibicaoComercial> exibeComerciaisTable;
    @FXML
    private TableColumn<ExibicaoComercial, String> dataColumn;
    @FXML
    private TableColumn<ExibicaoComercial, String> horaInicioColumn;
    @FXML
    private TableColumn<ExibicaoComercial, String> horaFimColumn;
    @FXML
    private TableColumn<ExibicaoComercial, Integer> precoColumn;
    @FXML
    private TableColumn<ExibicaoComercial, String> cnpjClColumn;

    
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
    	dataColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataExCoProperty());
    	horaInicioColumn.setCellValueFactory(
                cellData -> cellData.getValue().horaInicioExCoProperty());
    	horaFimColumn.setCellValueFactory(
                cellData -> cellData.getValue().horaFimExCoProperty());
    	precoColumn.setCellValueFactory(
                cellData -> cellData.getValue().precoExCoProperty().asObject());
    	cnpjClColumn.setCellValueFactory(
                cellData -> cellData.getValue().cnpjClProperty());


    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp, Comercial selectedComercial) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        ExibicaoComercialDAO dao = new ExibicaoComercialDAO(conn);
        try{
        	exibeComerciaisTable.setItems(dao.findExibicaoComercial(selectedComercial));        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
    }

}
