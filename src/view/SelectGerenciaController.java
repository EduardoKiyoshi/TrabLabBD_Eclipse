package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Departamento;
import model.DepartamentoDAO;
import model.FuncionarioDAO;
import model.Gerencia;
import model.GerenciaDAO;
import util.DBconnection;
import util.ErrorHandler;

public class SelectGerenciaController {
	@FXML
    private TableView<Gerencia> gerenciaTable = new TableView<Gerencia>();
    @FXML
    private TableColumn<Gerencia, Integer> idDeColumn;
    @FXML
    private TableColumn<Gerencia, Integer> idFuColumn;    
    @FXML
    private TableColumn<Gerencia, String> dataInicioTrColumn;
    @FXML
    private TableColumn<Gerencia, String> dataTerminoTrColumn;
    @FXML
    private ChoiceBox<String> departamentoChoiceBox;
    
    private MainApp mainApp;
    private Stage dialogStage;
    private ObservableList<String> departamentoList;
	private Map<String, Integer> departamentoIdMap;
	
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	idFuColumn.setCellValueFactory(
                cellData -> cellData.getValue().idFuProperty().asObject());
    	idDeColumn.setCellValueFactory(
                cellData -> cellData.getValue().idDeProperty().asObject());
    	dataInicioTrColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataInicioGeProperty());
    	dataTerminoTrColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataFimGeProperty());
    	
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();        
        GerenciaDAO daoTr = new GerenciaDAO(conn);  
    	DepartamentoDAO daoTipo = new DepartamentoDAO(conn);		
		ObservableList<Departamento> tipoData = FXCollections.observableArrayList();
		departamentoList = FXCollections.observableArrayList();
		departamentoIdMap = new HashMap<String,Integer>();
		
        try{        	
        	gerenciaTable.setItems(daoTr.findAll());        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
		try{
			tipoData = daoTipo.findAll();
			for(Departamento e : tipoData){	
				departamentoIdMap.put(e.getNomeDe(), e.getIdDe());
				departamentoList.add(e.getNomeDe());
			}
			departamentoChoiceBox.setItems(departamentoList);
	    }catch (SQLException sqlex) {
	    	//errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
		
		departamentoChoiceBox.getSelectionModel().selectFirst();

    }
    @FXML
    private void handleDeleteGerencia() {
    	Connection conn = DBconnection.getConexao();
    	GerenciaDAO dao = null;
    	FuncionarioDAO daoFu = null;
        Gerencia selectedGerencia = gerenciaTable.getSelectionModel().getSelectedItem();
        int selectedIndex = gerenciaTable.getSelectionModel().getSelectedIndex();
        String errorMessage = "";
        
        
        if (selectedGerencia != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Exlusao de Gerencia");
            alert.setHeaderText("Exlusao de Gerencia");
            alert.setContentText("Tem certeza que deseja excluir essa gerencia?");
            Optional<ButtonType> result = alert.showAndWait();
        	if ((result.isPresent()) && (result.get() == ButtonType.OK)) {        		
	        		try{
        				dao = new GerenciaDAO(conn);
    	        		dao.deleteGerencia(selectedGerencia.getIdFu());
    	        		gerenciaTable.getItems().remove(selectedIndex);    	        		
	        		}catch (SQLException sqlex) {
	                	errorMessage += sqlex.getErrorCode();
	        			System.out.println("SQL Error" + sqlex);		    
	        		}finally {
	        		    try { conn.close(); } catch (Exception e) { /* ignored */ }
	        		}
	        		if (errorMessage.length() == 0) {
	                    alert = new Alert(AlertType.CONFIRMATION); 
	                    alert.initOwner(dialogStage);
	                    alert.setTitle("Exclusao Confirmada");
	                    alert.setHeaderText("Sucesso na exclusao");
	                    alert.showAndWait();
	                    
	                } else {
	                    // Show the error message.
	                    alert = new Alert(AlertType.ERROR);
	                    alert.initOwner(dialogStage);
	                    alert.setTitle("Invalid Fields");
	                    alert.setHeaderText("Please correct invalid fields");
	                    alert.setContentText(ErrorHandler.getMessage(Integer.parseInt(errorMessage)));
	                    
	                    alert.showAndWait();
	                }
        	}
        }else{
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
    private void handleBuscarGerenciaDepto() {
    	Connection conn = DBconnection.getConexao();
    	GerenciaDAO dao = null;
    	String errorMessage = "";
    	Departamento dpto = new Departamento();
    	dpto.setIdDe(departamentoIdMap.get(
        				departamentoChoiceBox.getValue().toString()
        			));
    	try{
			dao = new GerenciaDAO(conn);
			gerenciaTable.setItems(dao.find(dpto));  
			//mainApp.
		}catch (SQLException sqlex) {
        	errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
    }
    @FXML
    private void handleBackButton() {
        mainApp.showFuncionarioOverview();
    }
    
}
