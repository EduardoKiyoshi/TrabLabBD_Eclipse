package view;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import util.DBconnection;
import util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;
import util.ErrorHandler;

/**
 * Dialog to edit details of a person.
 * 
 * @author Marco Jakob
 */
public class DependenteInsertDialogController {
    @FXML
    private Label idFu;
    @FXML
    private Label nomeCompletoFu;
    @FXML
    private TextField nomeCompletoDe;
    @FXML
    private RadioButton rFem;
    @FXML
    private RadioButton rMas;
    @FXML
    private TextField birthdayField;
    @FXML
    private ToggleGroup sexoDe;
    @FXML
    private ChoiceBox dependentes;

    private Stage dialogStage;
    private Funcionario funcionario;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	/*Connection conn = DBconnection.getConexao();
    	DependenteDAO dao = new DependenteDAO(conn);
    	ObservableList<Dependente> dependenteData = FXCollections.observableArrayList();
    	try{
    		dependenteData = dao.find(funcionario);     
    		dependentes.setItems(dependenteData);
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

    

    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	String errorMessage = "";
    	Alert alert = null;
        if (isInputValid()) {
        	DBconnection connection = new DBconnection();
        	Connection conn = DBconnection.getConexao();
        	DependenteDAO dao = new  DependenteDAO(conn);
        	Dependente dep = new Dependente();
        	dep.setIdFu(Integer.parseInt(idFu.getText()));
        	dep.setNomeCompletoDe(nomeCompletoDe.getText());
        	dep.setDataNascimentoDe(DateUtil.parse(birthdayField.getText()));
        	if(rMas.isSelected())
        		dep.setSexoDe("M");
        	else if(rFem.isSelected())
        		dep.setSexoDe("F");
        	else
        		dep.setSexoDe(null);
        	try{
        		conn.setAutoCommit(false);
        		Savepoint save1 = conn.setSavepoint();
        		dao.insertDependente(dep);  
        		conn.rollback(save1);
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
        	
            okClicked = true;
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setFuncionario(Funcionario func) {
        this.funcionario = func;
        Connection conn = DBconnection.getConexao();
        DependenteDAO dao = new DependenteDAO(conn);
    	ObservableList<Dependente> dependenteData = FXCollections.observableArrayList();        
    	ObservableList<String> dependenteDataNomes = FXCollections.observableArrayList();        
    	
        idFu.setText(""+ funcionario.getIdFu()  );
        nomeCompletoFu.setText(funcionario.getNomeCompletoFu() );
        
    	try{
    		dependenteData = dao.find(funcionario);     
    		for(Dependente e : dependenteData){
    			dependenteDataNomes.add(e.getNomeCompletoDe());
    		}
    		dependentes.setItems(dependenteDataNomes);
        }catch (SQLException sqlex) {
        	//errorMessage += sqlex.getErrorCode();
			System.out.println("SQL Error" + sqlex);		    
		}
    }
    
    
}