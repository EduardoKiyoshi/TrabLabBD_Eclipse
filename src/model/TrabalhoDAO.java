package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DateUtil;

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
    public ObservableList find(Departamento dep, LocalDate dataInicio) throws SQLException {
        ObservableList departamentoList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idFu, idDe, dataInicioTr, dataFimTr FROM TRABALHO WHERE IDDE = "
        		+ dep.getIdDe() + " AND ";
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
    public boolean insertTrabalho(Trabalho trab) throws SQLException {
		Statement st = null;
		String insertQuery = "INSERT INTO TRABALHO "
				+ "VALUES("
				+ trab.getIdDe() + ","
				+ trab.getIdFu() + ","
				+ "TO_DATE('"	+ trab.getDataInicioTr() + "', 'DD/MM/YYYY'), ";
				if(trab.getDataFimTr() != null)
					insertQuery = insertQuery + "TO_DATE('"	+ trab.getDataFimTr() + "', 'DD/MM/YYYY')"
					+ ")";
				else	
					insertQuery = insertQuery + "null"
					+ ")";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
    public boolean updateTrabalho(Trabalho oldTrab, LocalDate newDataFimTr) throws SQLException {
		Statement st = null;
		String insertQuery = "UPDATE TRABALHO SET dataFimTr = ";
		if(newDataFimTr != null)
			insertQuery = insertQuery + "TO_DATE('"	+ DateUtil.format(newDataFimTr) + "', 'DD/MM/YYYY')"
			+ " WHERE IDDE = " + oldTrab.getIdDe() + " AND IDFU = " + oldTrab.getIdFu() +" AND DATAINICIOTR = TO_DATE('"+ oldTrab.getDataInicioTr()+"', 'DD/MM/YYYY')";
		else	
			insertQuery = insertQuery + "null"
			+ " WHERE IDDE = " + oldTrab.getIdDe() + " AND IDFU = " + oldTrab.getIdFu() +" AND DATAINICIOTR = TO_DATE('"+ oldTrab.getDataInicioTr()+"', 'DD/MM/YYYY')";
		
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
    public boolean deleteTrabalho(int idFu, int idDe, LocalDate dataInicioTr) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE FROM TRABALHO WHERE IDFU = " + idFu + " AND idDe = " + idDe + " AND dataInicioTr = TO_DATE('" + DateUtil.format(dataInicioTr)+ "','DD/MM/YYYY')";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
    public boolean deleteTrabalho(int idFu) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE TRABALHO WHERE IDFU = " + idFu;
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
    private Trabalho createTrabalho(ResultSet resultSet) throws SQLException {
    	Trabalho con = null;
        FuncionarioDAO funcionarioDAO = null;
        DepartamentoDAO departamentoDAO = null;
        LocalDate dataFim = null;
        try{			
                con = new Trabalho();
                con.setIdFu(resultSet.getInt("idFu"));
                con.setIdDe(resultSet.getInt("idDe"));	
                con.setDataInicioTr(resultSet.getDate("dataInicioTr").toLocalDate());
                resultSet.getDate("dataFimTr");
                if(resultSet.wasNull())
                	con.setDataFimTr(DateUtil.parse("00/00/0000"));
                else
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
