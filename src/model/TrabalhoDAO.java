package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TrabalhoDAO {
    private Connection conn;

    public TrabalhoDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList find(Funcionario func) throws SQLException {
        ObservableList funcionarioList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idFu, idDe, dataInicioTr, dataFimTr FROM TRABALHO WHERE IDFU = "
        		+ func.getIdFu();
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   funcionarioList.add(createTrabalho(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return funcionarioList;
    }
    public ObservableList find(Departamento dep) throws SQLException {
        ObservableList departamentoList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idFu, idDe, dataInicioTr, dataFimTr FROM TRABALHO WHERE IDDE = "
        		+ dep.getIdDe();
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   departamentoList.add(createTrabalho(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return departamentoList;
    }
    
    private Trabalho createTrabalho(ResultSet resultSet) throws SQLException {
    	Trabalho con = null;
        FuncionarioDAO funcionarioDAO = null;
        DepartamentoDAO departamentoDAO = null;
        try{			
                con = new Trabalho();
                con.setIdFu(resultSet.getInt("idPr"));
                con.setIdDe(resultSet.getInt("idDe"));	
                con.setDataInicioTr(resultSet.getDate("dataInicioTr").toLocalDate());		
                con.setDataFimTr(resultSet.getDate("dataFimTr").toLocalDate());
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
