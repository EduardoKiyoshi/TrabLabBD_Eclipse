/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_parte2;

import java.sql.Connection;
import java.sql.SQLException;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Row;
import util.DBconnection;

/**
 *
 * @author lenovo
 */
public class TodosFuncionariosController {
    private MainApp mainApp;
    
    @FXML
    private TableView<Row> todosTable = new TableView<Row>(); 
    @FXML
    private TableColumn<Row, String> nomeFuncColumn;
    @FXML
    private TableColumn<Row, String> salarioColumn;
    @FXML
    private TableColumn<Row, String> cargoColumn;
    @FXML
    private TableColumn<Row, String> nomeDepColumn;
    @FXML
    private TableColumn<Row, String> idadeDepColumn;
    @FXML
    private void initialize() {
        nomeFuncColumn.setCellValueFactory(
               cellData -> cellData.getValue().nomeFuncProperty());        
        
        salarioColumn.setCellValueFactory(
                cellData -> cellData.getValue().salarioProperty());
        cargoColumn.setCellValueFactory(
                cellData -> cellData.getValue().cargoProperty());
        nomeDepColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeDepProperty());
        idadeDepColumn.setCellValueFactory(
                cellData -> cellData.getValue().idadeDepProperty());
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        ListaFuncionarioDAO dao = new ListaFuncionarioDAO(conn);
        ObservableList<Row> lista = FXCollections.observableArrayList();
        try{
            lista = dao.findAll();
            for(Row e : lista){
                System.out.println(e.getNomeFunc());
            }
            //System.out.println(dao.findAll());
            todosTable.setItems(lista);        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}	
        
    }
}
