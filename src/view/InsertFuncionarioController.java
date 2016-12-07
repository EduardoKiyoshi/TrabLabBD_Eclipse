package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.Button;
import model.DepartamentoDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.TipoFuncionario;
import model.TipoFuncionarioDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class InsertFuncionarioController {
	@FXML
    	private TextField nameField;
	@FXML
    	private TextField birthdayField;
	@FXML
    	private TextField cpfField;
	@FXML
		private ChoiceBox<String> idTipoBox;
	@FXML
		private Button confirmarButton;
	@FXML
		private Button cancelarButton;
	/*@FXML
    private void handleCancel() {
        dialogStage.close();
    }*/
	
	private ObservableList<String> tipoFuncList;
	private Map<String, Integer> tipoDescricao;
	private Stage dialogStage;
	Funcionario func; 
	@FXML
    private void initialize() {
		Connection conn = DBconnection.getConexao();
		TipoFuncionarioDAO daoTipo = new TipoFuncionarioDAO(conn);		
		ObservableList<TipoFuncionario> tipoData = FXCollections.observableArrayList();
		tipoFuncList = FXCollections.observableArrayList();
		tipoDescricao = new HashMap<String,Integer>();
		try{
			tipoData = daoTipo.findAll();
			for(TipoFuncionario e : tipoData){	
				tipoDescricao.put(e.getDescricaoTipoFu(), e.getIdTipoFu());
				tipoFuncList.add(e.getDescricaoTipoFu());
			}
			idTipoBox.setItems(tipoFuncList);
	    }catch (SQLException sqlex) {
	    	//errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}
    }
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	@FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
        //if (isInputValid()) {
        	DBconnection connection = new DBconnection();
        	Connection conn = DBconnection.getConexao();
        	FuncionarioDAO dao = new  FuncionarioDAO(conn);
        	
        	func = new Funcionario();        	
        	func.setNomeCompletoFu(nameField.getText());
        	func.setDataNascimentoFu(DateUtil.parse(birthdayField.getText()));
        	func.setCpfFu(cpfField.getText());
        	
        	func.setIdTipoFu(tipoDescricao.get(
        				idTipoBox.getValue().toString()        			
        			)
        	);
        		
        	
        	try{        		
        		dao.insertFuncionario(func);  
            }catch (SQLException sqlex) {
            	errorMessage += sqlex.getErrorCode();
    			System.out.println("SQL Error" + sqlex);		    
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
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
	public Funcionario getFuncionario(){
		return func;
	}
}
