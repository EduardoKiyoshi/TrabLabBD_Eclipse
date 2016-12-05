package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateTrabalhoController {
	@FXML
    private TextField dataTermino;
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
}
