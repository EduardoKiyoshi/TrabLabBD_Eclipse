package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
