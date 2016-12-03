/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emissoralabbd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import emissoralabbd.TableDocController;
/**
 *
 * @author aulos
 */
public class EmissoraLabBD extends Application {
    ConexaoBD conex = new ConexaoBD();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TableDoc.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setMinHeight(600);
        stage.setMinWidth(800);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
    * Desconecta da base quando fecha janela
    */
    @Override
    public void stop(){
        try{
            conex.desconectaBD();
        }
        catch(Exception ex){
            System.out.println("ERRO "+ex);
        }
    }
    
}
