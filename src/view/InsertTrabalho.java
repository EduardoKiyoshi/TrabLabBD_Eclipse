package view;

import java.time.LocalDate;

import emissoralabbd.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Trabalho;

public class InsertTrabalho {
    @FXML
    private ChoiceBox departamento;
    @FXML
    private ChoiceBox funcionario;
    @FXML
    private TextField dataInicio;
    @FXML
    private TextField dataTermino;
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
    	

        // Clear person details.
        //showPersonDetails(null);
    	/*
        // Listen for selection changes and show the person details when changed.
    	trabalhoTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
        */
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
