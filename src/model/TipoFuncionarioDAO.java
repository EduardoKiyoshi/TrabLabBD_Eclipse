package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TipoFuncionarioDAO {
private Connection conn;
	
	public TipoFuncionarioDAO(Connection conn){		
		this.conn = conn;
	}
	
	public ObservableList findAll() throws SQLException {
		   ObservableList FuncionarioTipoList = FXCollections.observableArrayList();
		   String selectQuery = "SELECT IDTIPOFU, SalarioBaseTipoFu, DescricaoTipoFu FROM TipoFuncionario";

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  FuncionarioTipoList.add(createTipoFuncionario(resultSet));
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return FuncionarioTipoList;
	}
	
	private TipoFuncionario createTipoFuncionario(ResultSet resultSet) throws SQLException {
		TipoFuncionario fun = null;
		try{			
			fun = new TipoFuncionario();
			fun.setIdTipoFu(resultSet.getInt("IDTIPOFU"));
			fun.setSalarioBaseTipoFu(resultSet.getInt("SalarioBaseTipoFu"));			
			fun.setDescricaoTipoFu( resultSet.getString("DescricaoTipoFu"));
			
		}catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);
		    throw sqlex;
		}
		return fun;
	}
}
