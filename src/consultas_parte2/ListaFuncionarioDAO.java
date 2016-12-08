/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_parte2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBconnection;
import model.Row;

/**
 *
 * @author lenovo
 */
public class ListaFuncionarioDAO {
    private Connection conn;

    public ListaFuncionarioDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList<Row> findAll() throws SQLException {
        ObservableList<Row> list = FXCollections.observableArrayList();
        Row row = null;
        String selectQuery = "SELECT \"Nome do funcionario\",\n" +
            "\"Salario do funcionario\",\n" +
            "\"Cargo\",\n" +
            "\"Nome do dependente\",\n" +
            "\"Idade do Dependente\"\n" +
            "FROM TODOSFUNCIONARIOS_VIEW";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
               row = new Row(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
               list.add(row);
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return list;
    }
    public static void main(String[] args){
        //System.out.println(TrabalhoTodosDepartamentos.class.getResource("/"));
    	try{
	    	Connection con = DBconnection.getConexao();
	    	ListaFuncionarioDAO dao = new ListaFuncionarioDAO(con);
	    	List<Row> lista = dao.findAll();
	    	//LocalDate data = DateUtil.parse("12/12/2013");
                for(Row el : lista ){
                    System.out.println(el.nomeFuncProperty());
                }
	    	
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    }
    
}
