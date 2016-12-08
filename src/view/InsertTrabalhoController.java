package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Departamento;
import model.DepartamentoDAO;
import model.Funcionario;
import model.Gerencia;
import model.GerenciaDAO;
import model.Trabalho;
import model.TrabalhoDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class InsertTrabalhoController {
	@FXML
    private ChoiceBox<String> departamentoChoiceBox;
    /*@FXML
    private ChoiceBox funcionarioChoiceBox;*/
    @FXML
    private TextField dataInicioTr;
    @FXML
    private TextField dataFimTr;
 // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;
    
    private Funcionario funcionario;
    private ObservableList<String> departamentoList;
	private Map<String, Integer> departamentoIdMap;
	
	private Connection con;
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	Connection conn = DBconnection.getConexao();
    	DepartamentoDAO daoTipo = new DepartamentoDAO(conn);		
		ObservableList<Departamento> tipoData = FXCollections.observableArrayList();
		departamentoList = FXCollections.observableArrayList();
		departamentoIdMap = new HashMap<String,Integer>();
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
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        
    }
    @FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
    	
    	TrabalhoDAO dao = new  TrabalhoDAO(con);
    	Trabalho trab = new Trabalho();
    	
    	GerenciaDAO daoGe = new  GerenciaDAO(con);
    	Gerencia gerencia = new Gerencia();
    	
    	trab.setIdFu(funcionario.getIdFu());
    	trab.setDataInicioTr(DateUtil.parse(dataInicioTr.getText())); 
    	trab.setDataFimTr(DateUtil.parse(dataFimTr.getText()));
    	System.out.println(trab.getDataFimTr());
    	trab.setIdDe(departamentoIdMap.get(
    			departamentoChoiceBox.getValue().toString()        			
    			)
    	);
    	try{        		
    		dao.insertTrabalho(trab);  
    		
    		if(funcionario.getIdTipoFu() == 1){
    			gerencia.setIdDe(trab.getIdDe());
    			gerencia.setIdFu(trab.getIdFu());
    			gerencia.setDataInicioGe(DateUtil.parse(trab.getDataInicioTr()));
    			gerencia.setDataFimGe(DateUtil.parse(trab.getDataFimTr()));
    			daoGe.insertGerencia(gerencia);
    		}
    			
    		con.commit();
    		con.setAutoCommit(true);
        }catch (SQLException sqlex) {
        	errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}finally {
		    try { con.close(); } catch (Exception e) { /* ignored */ }
		}
    	
    	
        if (errorMessage.length() == 0) {
            alert = new Alert(AlertType.CONFIRMATION); 
            alert.initOwner(dialogStage);
            alert.setTitle("Insercao Confirmada");
            alert.setHeaderText("Sucesso na insercao");
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

            //return false;
        }
    	
        //okClicked = true;
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setFuncionario(Funcionario func) {
        this.funcionario = func;
    }
    public void setConnection(Connection conn) {
        this.con = conn;
    }
    @FXML
    private void handleCancel() {
    	try{
    		con.rollback();
    		con.setAutoCommit(true);
    	}catch(SQLException ex){
    		System.out.println("Erro no Rollback:" + ex);
    	}finally {
		    try { con.close(); } catch (Exception e) { /* ignored */ }
		}
    	
        dialogStage.close();
    }
}
