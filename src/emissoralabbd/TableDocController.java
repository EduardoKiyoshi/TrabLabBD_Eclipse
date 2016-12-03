/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emissoralabbd;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author aulos
 */
public class TableDocController implements Initializable {
    ConexaoBD conex = new ConexaoBD();
    Statement stmt;
    ResultSet rs;
    
    // Label indicadora de conexão
    @FXML
    private Label labelConexao;
    
    // Botao no menu para abrir/fechar conexao
    @FXML
    private MenuItem opcaoConectar;
    
    /*
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    */
    
    /**
    * Abre/fecha conexão com a base, dependendo do contexto.
    * @trows SQLException,
    */
    @FXML
    private void menuConectarAction(ActionEvent event){
        if(opcaoConectar.getText().equals("Conectar")){
            System.out.println("hi, i'll now attempt to connect!");
            conex.setCon(conex.conectaBD("a7986409","alpha7rho"));
            
            if(conex.getCon() == null){
                System.out.println("FALHA DE CONEXA");
                labelConexao.setText("Conexão: ERRO");
            }
            else{
                System.out.println("Conectado!");
                labelConexao.setText("Conexão: CONECTADO");
                opcaoConectar.setText("Desconectar");
                
                
            }
        }
        else{
            try{
                System.out.println("Tentando desconectar!");
                conex.desconectaBD();
                System.out.println("Desconectado!");
                labelConexao.setText("Conexão: DESCONECTADO");
                opcaoConectar.setText("Conectar");
            }
            catch(SQLException ex){
                System.out.println("ERRO: "+ex);
            }
        }
        
    }
    
    //PARA TESTE APENAS
    //RETORNA TODOS OS NOMES DOS CAMPEONATOS DAS ATIVIDADES EM AULA
    @FXML
    private void testSelect(ActionEvent event){
        try{
            stmt = conex.createStatement();
            rs = stmt.executeQuery("SELECT * FROM CAMPEONATO");
            while(rs.next()){
               String nome = rs.getString("NOMECAMP");
               System.out.println(nome);
            }
        }
        catch(SQLException ex){
            System.out.println("ERRO: "+ex);
        }
    }
    
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
