package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

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
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class UpdateFuncionarioController {
	@FXML
    private TextField nomeFu;
	@FXML
    private TextField dataNascimentoFu;
	@FXML
    private ChoiceBox tipoFu;
	@FXML
    private Button confirmar;
	@FXML
    private Button cancelar;
	 // Reference to the main application.
    private MainApp mainApp;

    private Stage dialogStage;
    private Funcionario funcionario;
    
    @FXML
    private void initialize() {
    	/*DependenteDAO dao = new DependenteDAO(conn);
    	ObservableList<Dependente> dependenteData = FXCollections.observableArrayList();
    	try{
    		dependenteData = dao.find(funcionario);     
    		tipoFu.setItems(dependenteData);
        }catch (SQLException sqlex) {
        	//errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}*/
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
    	
    	//Funcionario fun = new Funcionario();
    	funcionario.setNomeCompletoFu(nomeFu.getText());
    	funcionario.setDataNascimentoFu(DateUtil.parse(dataNascimentoFu.getText()));
    	
    	funcionario.setIdTipoFu(2);
    	/*
    	if(rMas.isSelected())
    		dep.setSexoDe("M");
    	else if(rFem.isSelected())
    		dep.setSexoDe("F");
    	else
    		dep.setSexoDe(null);*/
    	try{
    		//conn.setAutoCommit(false);
    		//Savepoint save1 = conn.setSavepoint();
    		dao.updateFuncionario(funcionario.getIdFu(), funcionario);
    		//conn.rollback(save1);
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
