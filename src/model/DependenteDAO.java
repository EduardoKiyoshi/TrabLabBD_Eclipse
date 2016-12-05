package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DependenteDAO {
	private Connection conn;
	
	public DependenteDAO(Connection conn){		
		this.conn = conn;
	}
	public boolean insertDependente(Dependente dep) throws SQLException {
		Statement st = null;
		String insertQuery = "INSERT INTO DEPENDENTE "
				+ "VALUES("+ dep.getIdFu() + ",'"+ dep.getNomeCompletoDe() +"', TO_DATE('"
				+ dep.getDataNascimentoDe() + "', 'DD/MM/YYYY'), UPPER('"+ dep.getSexoDe() +"') )";	
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}	
	public boolean deleteDependente(int idFu,String nomeCompletoDe) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE from DEPENDENTE WHERE IDFU = "
				+ idFu + " AND upper(nomeCompletoDe) = upper('" + nomeCompletoDe + "')";	
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}	
	public ObservableList findAll() throws SQLException {
		   ObservableList DependenteList = FXCollections.observableArrayList();
		   String selectQuery = "SELECT IDFU, NOMECOMPLETODE, dataNascimentoDe, sexoDe FROM DEPENDENTE";

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  DependenteList.add(createDependente(resultSet));
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return DependenteList;
	}
	public boolean updateDependente(Dependente newDep) throws SQLException {
		Statement st = null;
		String insertQuery = " UPDATE DEPENDENTE SET " +
				"DATANASCIMENTODE = TO_DATE('"	+ newDep.getDataNascimentoDe() + "', 'DD/MM/YYYY'), SEXODE = UPPER('"+ newDep.getSexoDe() +"') WHERE IDFU = "
				+ newDep.getIdFu() + " AND nomeCompletoDe = '" + newDep.getNomeCompletoDe() + "'";	
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
	public ObservableList find(Funcionario fun) throws SQLException {
		   ObservableList DependenteList = FXCollections.observableArrayList();
		   System.out.println(fun.getIdFu());
		   String selectQuery = "SELECT IDFU, NOMECOMPLETODE, dataNascimentoDe, sexoDe FROM DEPENDENTE WHERE IDFU = " + fun.getIdFu();

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  
		    	  DependenteList.add(createDependente(resultSet));
		    	  System.out.println(createDependente(resultSet).getNomeCompletoDe());
		    	  System.out.println(fun.getIdFu());
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return DependenteList;
	}
	private Dependente createDependente(ResultSet resultSet) throws SQLException {
		Dependente dep = null;
		try{			
			dep = new Dependente();
			dep.setIdFu(resultSet.getInt("idFu"));
			dep.setNomeCompletoDe(resultSet.getString("nomeCompletoDe"));			
			dep.setDataNascimentoDe( resultSet.getDate("dataNascimentoDe").toLocalDate());
			dep.setSexoDe(resultSet.getString("sexoDe"));
		}catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);
		    throw sqlex;
		}
		return dep;
	}
}


