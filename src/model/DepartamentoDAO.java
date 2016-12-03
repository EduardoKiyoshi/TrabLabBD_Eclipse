package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DepartamentoDAO {
private Connection conn;
	
	public DepartamentoDAO(Connection conn){		
		this.conn = conn;
	}
	
	public ObservableList findAll() throws SQLException {
		   ObservableList DepartamentoList = FXCollections.observableArrayList();
		   String selectQuery = "SELECT IDDE, nomeDe, telefoneDe, nroFuncionariosDe, cnpjEm FROM DEPARTAMENTO ";

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  DepartamentoList.add(createDepartamento(resultSet));
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return DepartamentoList;
	}
	public Departamento find(int idDe) throws SQLException {
		Departamento dep = null;
		   String selectQuery = "SELECT IDDE, nomeDe, telefoneDe, nroFuncionariosDe, cnpjEm FROM DEPARTAMENTO where IDDE = " + idDe;

		   try{ 
			   dep = new Departamento();
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  dep = createDepartamento(resultSet);
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return dep;
	}
	public Departamento find(String nomeDe) throws SQLException {
		Departamento dep = null;
		   String selectQuery = "SELECT IDDE, nomeDe, telefoneDe, nroFuncionariosDe, cnpjEm FROM DEPARTAMENTO where nomeDe = " + nomeDe;

		   try{ 
			   dep = new Departamento();
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  dep = createDepartamento(resultSet);
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return dep;
	}
	private Departamento createDepartamento(ResultSet resultSet) throws SQLException {
		Departamento fun = null;
		try{			
			fun = new Departamento();
			fun.setIdDe(resultSet.getInt("IDDE"));
			fun.setNomeDe(resultSet.getString("nomeDe"));			
			fun.setTelefoneDe(resultSet.getString("telefoneDe"));
			fun.setNroFuncionariosDe(resultSet.getInt("nroFuncionariosDe"));
			fun.setCnpjEm(resultSet.getString("cnpjEm"));
		}catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);
		    throw sqlex;
		}
		return fun;
	}
}
