package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Map;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.TipoFuncionario;
import model.TipoFuncionarioDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class UpdateFuncionarioController {
	@FXML
    private TextField nomeFu;
	@FXML
    private TextField dataNascimentoFu;
	@FXML
	private ChoiceBox<String> idTipoBox;
	@FXML
    private Button confirmar;
	@FXML
    private Button cancelar;

    private Stage dialogStage;
    private Funcionario funcionario;
    private ObservableList<String> tipoFuncList;
	private Map<String, Integer> tipoDescricao;
	private MainApp mainApp;
	
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
		}finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
		}
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
    	DBconnection connection = new DBconnection();
    	Connection conn = DBconnection.getConexao();
    	FuncionarioDAO dao = new  FuncionarioDAO(conn);
    	
    	funcionario.setNomeCompletoFu(nomeFu.getText());
    	funcionario.setDataNascimentoFu(DateUtil.parse(dataNascimentoFu.getText()));

    	funcionario.setIdTipoFu(tipoDescricao.get(
    				idTipoBox.getValue().toString()        			
    			)
    	);
    	
    	try{
    		dao.updateFuncionario(funcionario.getIdFu(), funcionario);
    		//conn.rollback(save1);
        }catch (SQLException sqlex) {
        	errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}finally {
		    try { conn.close(); } catch (Exception e) { /* ignored */ }
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
        }
        
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
    	//mainApp.showAtorDependenteOverview();
        dialogStage.close();
    }
    
    public void setFuncionario(Funcionario func) {
        this.funcionario = func;
    }
}
