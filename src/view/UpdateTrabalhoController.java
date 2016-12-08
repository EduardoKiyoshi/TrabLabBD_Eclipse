package view;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.FuncionarioDAO;
import model.Trabalho;
import model.TrabalhoDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class UpdateTrabalhoController {
	private Trabalho trabalho;
	private Stage dialogStage;
	@FXML
    private TextField dataTermino;
	@FXML
    private void initialize() {
       
    }
	@FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
    	DBconnection connection = new DBconnection();
    	Connection conn = DBconnection.getConexao();
    	TrabalhoDAO dao = new  TrabalhoDAO(conn);    	
    	try{
    		dao.updateTrabalho(trabalho, DateUtil.parse(dataTermino.getText()));
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
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	public void setTrabalho(Trabalho trabalho){
		this.trabalho = trabalho;
	}
	@FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
