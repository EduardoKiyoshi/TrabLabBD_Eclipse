package emissoralabbd;

import java.io.IOException;
import java.sql.Connection;

import com.sun.prism.paint.Color;

import consultas_parte2.AtorDependenteOverviewController;
import consultas_parte2.TodosFuncionariosController;
import consultas_parte2.TrabalhoTodosDepartamentosOverviewController;
import model.Dependente;
import model.Funcionario;
import model.Trabalho;
import util.DateUtil;
import view.DependenteInsertDialogController;
import view.FuncionarioOverviewController;
import view.InsertFuncionarioController;
import view.InsertTrabalhoController;
import view.SelectDependenteController;
import view.SelectGerenciaController;
import view.SelectTrabalhoController;
import view.UpdateDependenteController;
import view.UpdateFuncionarioController;
import view.UpdateTrabalhoController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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

        Funcionario func = new Funcionario();
    	func.setIdFu(1);
    	func.setCpfFu("333.333.333-12");
    	String data = new String("12/12/2015");
    	func.setDataNascimentoFu(DateUtil.parse(data));
    	func.setIdTipoFu(1);
    	func.setNomeCompletoFu("Ana Barbosa");
    	
    	showFuncionarioOverview();
    	
    	//showAtorDependenteOverview();
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
    //Consultadas implementas na Parte 2
    public void showAtorDependenteOverview(int codigoProfissao) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/consultas_parte2/AtorDependenteOverview.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            AtorDependenteOverviewController controller = loader.getController();
            controller.setCodigoProfissao(codigoProfissao);
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
    public void showInsertFuncionario() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/InsertFuncionario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            InsertFuncionarioController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            Funcionario novoFuncionario = controller.getFuncionario();
            
            //System.out.println(novoFuncionario.getNomeCompletoFu());
            
			dialogStage.setOnHidden(new EventHandler<WindowEvent>() {
			    public void handle(WindowEvent we) {
			        showFuncionarioOverview();
			    }
			});
            
            // Give the controller access to the main app.
            
            
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showUpdateFuncionario(Funcionario selectedFuncionario){
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/UpdateFuncionario.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Funcionario");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            UpdateFuncionarioController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setFuncionario(selectedFuncionario);
            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return true;//controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    	
    }
    
    public boolean showSelectDependente(Funcionario selectedFuncionario) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/SelectDependente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            rootLayout.setCenter(page);
            
            // Set the person into the controller.
            SelectDependenteController controller = loader.getController();
            controller.setMainApp(this);
            controller.setFuncionario(selectedFuncionario);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
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
            dialogStage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                	showSelectDependente(selectedFuncionario);
                }
            });          
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }    
    public boolean showUpdateDependente(Dependente selectedDependente){

    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/UpdateDependente.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            //rootLayout.setCenter(page);
            
            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Dependente");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
             
            // Set the person into the controller.
            UpdateDependenteController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setMainApp(this);
            controller.setDependente(selectedDependente);            
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return true;//controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    	
    }
    
    public void showSelectTrabalho(Funcionario selectedFuncionario) {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/SelectTrabalho.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            SelectTrabalhoController controller = loader.getController();   
            controller.setFuncionario(selectedFuncionario);
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showInsertTrabalho(Funcionario selectedFuncionario, Connection conn) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/InsertTrabalho.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Set the person into the controller.
            InsertTrabalhoController controller = loader.getController();
            //controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setFuncionario(selectedFuncionario);
            controller.setConnection(conn);
            dialogStage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    showSelectTrabalho(selectedFuncionario);
                }
            });          
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean showUpdateTrabalho(Trabalho trabalho) {
    	try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/UpdateTrabalho.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
         // Set the person into the controller.
            UpdateTrabalhoController controller = loader.getController();
            //controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setTrabalho(trabalho);
            
            dialogStage.setOnHidden(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    showFuncionarioOverview();
                }
            });  
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void showSelectGerencia() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/SelectGerencia.fxml"));
            AnchorPane asd = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(asd);

            // Give the controller access to the main app.
            SelectGerenciaController controller = loader.getController();   
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
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