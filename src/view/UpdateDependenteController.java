package view;

import java.sql.Connection;
import java.sql.SQLException;

import emissoralabbd.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Dependente;
import model.DependenteDAO;
import util.DBconnection;
import util.DateUtil;
import util.ErrorHandler;

public class UpdateDependenteController {
	
	@FXML
    private TextField dataNascimentoDe;
	@FXML
    private RadioButton rFem;
    @FXML
    private RadioButton rMas;
    @FXML
    private ToggleGroup sexoDe;
	@FXML
    private Button confirmar;
	@FXML
    private Button cancelar;
	 // Reference to the main application.
    private MainApp mainApp;
    private Stage dialogStage;
    private Dependente Dependente;
    
    @FXML
    private void initialize() {    	
    }

    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
    	DBconnection connection = new DBconnection();
    	Connection conn = DBconnection.getConexao();
    	DependenteDAO dao = new  DependenteDAO(conn);
    	
    	Dependente.setDataNascimentoDe(DateUtil.parse(dataNascimentoDe.getText()));    	
    	if(rMas.isSelected())
    		Dependente.setSexoDe("M");
    	else if(rFem.isSelected())
    		Dependente.setSexoDe("F");
    	else
    		Dependente.setSexoDe(null);
    	
    	try{
    		dao.updateDependente(Dependente);
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
    
    public void setDependente(Dependente func) {
        this.Dependente = func;
    }
}	
