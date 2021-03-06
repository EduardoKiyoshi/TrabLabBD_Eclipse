package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuncionarioDAO {
	private Connection conn;
	
	public FuncionarioDAO(Connection conn){		
		this.conn = conn;
	}
	
	public ObservableList findAll() throws SQLException {
		   ObservableList FuncionarioList = FXCollections.observableArrayList();
		   String selectQuery = "SELECT IDFU, NOMECOMPLETOFU, dataNascimentoFU, cpfFu, salarioFu, FUNCIONARIO.idTipoFu, TIPOFUNCIONARIO.descricaoTipoFu FROM FUNCIONARIO "
		   		+ "JOIN TIPOFUNCIONARIO ON TIPOFUNCIONARIO.idTipoFu = FUNCIONARIO.idTipoFu";

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  FuncionarioList.add(createFuncionario(resultSet));
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return FuncionarioList;
	}
	public Funcionario find(int indFu) throws SQLException {
			Funcionario func = new Funcionario();
		   String selectQuery = "SELECT IDFU, NOMECOMPLETOFU, dataNascimentoFU, cpfFu, salarioFu, FUNCIONARIO.idTipoFu, TIPOFUNCIONARIO.descricaoTipoFu FROM FUNCIONARIO "
		   		+ "JOIN TIPOFUNCIONARIO ON TIPOFUNCIONARIO.idTipoFu = FUNCIONARIO.idTipoFu WHERE IDFU = " + indFu;

		   try{ 
		        PreparedStatement pStatement = conn.prepareStatement(selectQuery);
		        ResultSet resultSet = pStatement.executeQuery();
		      while (resultSet.next()) {
		    	  func = createFuncionario(resultSet);
		      }
		   } catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		   }
		   return func;
	}
	public boolean insertFuncionario(Funcionario func) throws SQLException {
		Statement st = null;
		String insertQuery = "INSERT INTO FUNCIONARIO "
				+ "VALUES("
				+ "SEQ_idFu.NEXTVAL ,'"
				+ func.getNomeCompletoFu() +"',"
				+ " TO_DATE('" + func.getDataNascimentoFu() + "', 'DD/MM/YYYY'), '"
				+ func.getCpfFu() +"',"
				+ func.getSalarioFu() + ","
				+ func.getIdTipoFu()  + 
				" )";	
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}	
	public boolean updateFuncionario(int idFu, Funcionario newFunc) throws SQLException {
		Statement st = null;
		String insertQuery = "UPDATE FUNCIONARIO SET " +
				" nomeCompletoFu = '"+ newFunc.getNomeCompletoFu() +"',"
				+ " DATANASCIMENTOFU = TO_DATE('" + newFunc.getDataNascimentoFu() + "', 'DD/MM/YYYY'), "
				+ " IDTIPOFU = " +newFunc.getIdTipoFu()  + 
				" WHERE IDFU = " + idFu;	
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}	
	public boolean deleteFuncionario(int idFu) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE FROM FUNCIONARIO WHERE IDFU = " + idFu;
		TrabalhoDAO trabalhoDAO = null;
		try{
			trabalhoDAO = new TrabalhoDAO(conn);
			trabalhoDAO.deleteTrabalho(idFu);
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}	
	private Funcionario createFuncionario(ResultSet resultSet) throws SQLException {
		Funcionario fun = null;
		DependenteDAO dependenteDAO = null;
		try{			
			fun = new Funcionario();
			fun.setIdFu(resultSet.getInt("idFu"));
			fun.setNomeCompletoFu(resultSet.getString("nomeCompletoFu"));			
			fun.setDataNascimentoFu( resultSet.getDate("dataNascimentoFu").toLocalDate());
			fun.setCpfFu(resultSet.getString("cpfFu"));
			fun.setSalarioFu(resultSet.getString("salarioFu"));
			fun.setIdTipoFu(resultSet.getInt("idTipoFu"));
			fun.setDescricaoTipoFu(resultSet.getString("descricaoTipoFu"));
			/*
			dependenteDAO = new DependenteDAO(conn);
			fun.setDependentes(dependenteDAO.find(fun));*/
		}catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);
		    throw sqlex;
		}
		return fun;
	}
}
