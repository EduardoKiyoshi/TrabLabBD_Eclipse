package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GerenciaDAO {
	private Connection conn;

    public GerenciaDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList find(Funcionario func) throws SQLException {
        ObservableList funcionarioList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idFu, idDe, dataInicioGe, dataFimGe FROM Gerencia WHERE IDFU = "
        		+ func.getIdFu();
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   funcionarioList.add(createGerencia(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return funcionarioList;
    }
    public ObservableList find(Departamento dep) throws SQLException {
        ObservableList departamentoList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idFu, idDe, dataInicioGe, dataFimGe FROM Gerencia WHERE IDDE = "
        		+ dep.getIdDe();
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   departamentoList.add(createGerencia(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return departamentoList;
    }
    
    private Gerencia createGerencia(ResultSet resultSet) throws SQLException {
    	Gerencia con = null;
        FuncionarioDAO funcionarioDAO = null;
        DepartamentoDAO departamentoDAO = null;
        try{			
                con = new Gerencia();
                con.setIdFu(resultSet.getInt("idPr"));
                con.setIdDe(resultSet.getInt("idDe"));	
                con.setDataInicioGe(resultSet.getDate("dataInicioGe").toLocalDate());		
                con.setDataFimGe(resultSet.getDate("dataFimGe").toLocalDate());
                try{
                	funcionarioDAO = new FuncionarioDAO(conn);
                	departamentoDAO = new DepartamentoDAO(conn);
                	
                	con.setDepartamento(departamentoDAO.find(resultSet.getInt("idDe")));
                	con.setFuncionario(funcionarioDAO.find(resultSet.getInt("idFu")));
                }catch (SQLException sqlex) {
                    System.out.println("SQL Error" + sqlex);
                }
        }catch (SQLException sqlex) {
                System.out.println("SQL Error" + sqlex);
            throw sqlex;
        }
        return con;
    }
}
