/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_parte2;


import emissoralabbd.MainApp;
import java.sql.Connection;
import java.sql.SQLException;

import consultas_parte2.TrabalhoTodosDepartamentosDAO.Row;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Funcionario;
import model.FuncionarioDAO;
import util.DBconnection;

/**
 *
 * @author lenovo
 */
public class TrabalhoTodosDepartamentosOverviewController {
    private MainApp mainApp;
    
    @FXML
    private TableView<TrabalhoTodosDepartamentosDAO.Row> todosTable = new TableView<TrabalhoTodosDepartamentosDAO.Row>(); 
    @FXML
    private TableColumn<TrabalhoTodosDepartamentosDAO.Row, String> nomeFuncColumn;
    @FXML
    private TableColumn<TrabalhoTodosDepartamentosDAO.Row, String> dataInicioColumn;
    @FXML
    private TableColumn<TrabalhoTodosDepartamentosDAO.Row, String> dataTerminoColumn;
    @FXML
    private TableColumn<TrabalhoTodosDepartamentosDAO.Row, String> nomeDeColumn;
    @FXML
    private void initialize() {
        nomeFuncColumn.setCellValueFactory(
               cellData -> cellData.getValue().nomeFuncProperty());   
        dataInicioColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataInicioProperty());
        dataTerminoColumn.setCellValueFactory(
                cellData -> cellData.getValue().dataTerminoProperty());
        nomeDeColumn.setCellValueFactory(
                cellData -> cellData.getValue().nomeDeProperty());
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        TrabalhoTodosDepartamentosDAO dao = new TrabalhoTodosDepartamentosDAO(conn);
        ObservableList<TrabalhoTodosDepartamentosDAO.Row> lista = FXCollections.observableArrayList();
        try{
            lista = dao.findAll();
            for(TrabalhoTodosDepartamentosDAO.Row e : lista){
                System.out.println(e.getNomeFunc());
            }
            //System.out.println(dao.findAll());
            todosTable.setItems(lista);        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
       
    }
}
