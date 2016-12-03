package view;

import emissoralabbd.MainApp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import util.DBconnection;
import util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

        // Listen for selection changes and show the person details when changed.
        //personTable.getSelectionModel().selectedItemProperty().addListener(
        //        (observable, oldValue, newValue) -> showPersonDetails(newValue));
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
    private void handleAddDependente() {
        Funcionario selectedFuncionario = funcionarioTable.getSelectionModel().getSelectedItem();
        if (selectedFuncionario != null) {
            boolean okClicked = mainApp.showDependenteAddDialog(selectedFuncionario);

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
