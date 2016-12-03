package emissoralabbd;

import java.io.IOException;

import consultas_parte2.AtorDependenteOverviewController;
import consultas_parte2.TodosFuncionariosController;
import consultas_parte2.TrabalhoTodosDepartamentosOverviewController;
import model.Funcionario;
import view.DependenteInsertDialogController;
import view.FuncionarioOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
 
    /**
     * Constructor
     */
    public MainApp() {
        
    }


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");

        initRootLayout();
           
        showTodosFuncionariosOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            System.out.println(MainApp.class.getResource("/view"));
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showAtorDependenteOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/consultas_parte2/AtorDependenteOverview.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            AtorDependenteOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showTodosFuncionariosOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/consultas_parte2/TodosFuncionarios.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            TodosFuncionariosController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showFuncionarioOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/FuncionarioOverview.fxml"));
            AnchorPane FuncionarioOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(FuncionarioOverview);

            // Give the controller access to the main app.
            FuncionarioOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showTrabalhoTodosOverview() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/consultas_parte2/TrabalhoTodosDepartamentosOverview.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            TrabalhoTodosDepartamentosOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public boolean showDependenteAddDialog(Funcionario selectedFuncionario) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/DependenteInsertDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            DependenteInsertDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFuncionario(selectedFuncionario);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}