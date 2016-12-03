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
        String selectQuery = "SELECT FUNC.nomeCompletoFu AS \"Nome do funcionário\",\n" +
            "TO_CHAR(FUNC.salarioFu,'L99G999D99MI', 'NLS_NUMERIC_CHARACTERS = '',.'' NLS_CURRENCY = ''R$''')  AS \"Salário do funcionário\",\n" +
            "tipoFUNC.DESCRICAOTIPOFU  AS \"Cargo\",\n" +
            "NVL(DEP.NOMECOMPLETODE, 'não possui')  AS \"Nome do dependente\",\n" +
            "NVL(TO_CHAR(EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DEP.DATANASCIMENTODE)), '-') AS \"Idade do Dependente\"\n" +
            "FROM FUNCIONARIO FUNC LEFT JOIN DEPENDENTE DEP\n" +
            "ON FUNC.idFu = DEP.idFu\n" +
            "JOIN tipoFuncionario tipoFUNC\n" +
            "ON FUNC.idTipoFu = tipoFUNC.idTipoFu\n" +
            "ORDER BY FUNC.nomeCompletoFu";
        
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
    	/*try{
	    	Connection con = DBconnection.getConexao();
	    	TrabalhoTodosDepartamentos dao = new TrabalhoTodosDepartamentos(con);
	    	List<String> lista = dao.findAll();
	    	//LocalDate data = DateUtil.parse("12/12/2013");
                for(String el : lista ){
                    System.out.println(el);
                }
	    	
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println(e);
		}*/
    }
    
}
