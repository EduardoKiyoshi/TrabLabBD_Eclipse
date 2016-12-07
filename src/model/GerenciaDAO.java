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

public class GerenciaDAO {
	private Connection conn;

    public GerenciaDAO(Connection conn){		
            this.conn = conn;
    }
    public boolean insertGerencia(Gerencia trab) throws SQLException {
		Statement st = null;
		String insertQuery = "INSERT INTO Gerencia "
				+ "VALUES("
				+ trab.getIdDe() + ","
				+ trab.getIdFu() + ","
				+ "TO_DATE('"	+ trab.getDataInicioGe() + "', 'DD/MM/YYYY'), ";
				if(trab.getDataFimGe() != null)
					insertQuery = insertQuery + "TO_DATE('"	+ trab.getDataFimGe() + "', 'DD/MM/YYYY')"
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
    public boolean updateGerencia(Gerencia oldTrab, LocalDate newDATAFIMGE) throws SQLException {
		Statement st = null;
		String insertQuery = "UPDATE Gerencia SET DATAFIMGE = ";
		if(newDATAFIMGE != null)
			insertQuery = insertQuery + "TO_DATE('"	+ DateUtil.format(newDATAFIMGE) + "', 'DD/MM/YYYY')"
					+ " WHERE IDDE = " + oldTrab.getIdDe() + " AND IDFU = " + oldTrab.getIdFu() +" AND DATAINICIOGE = TO_DATE('"+ oldTrab.getDataInicioGe()+"', 'DD/MM/YYYY')";
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
    public boolean deleteGerencia(int idFu, int idDe, LocalDate DATAINICIOGE) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE FROM Gerencia WHERE IDFU = " + idFu + " AND idDe = " + idDe + " AND DATAINICIOGE = TO_DATE('" + DateUtil.format(DATAINICIOGE)+ "','DD/MM/YYYY')";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
	}
    public boolean deleteGerencia(int idFu) throws SQLException {
		Statement st = null;
		String insertQuery = "DELETE Gerencia WHERE IDFU = " + idFu;
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
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
                
                resultSet.getDate("dataFimGe");
                if(resultSet.wasNull())
                	con.setDataFimGe(DateUtil.parse("00/00/0000"));
                else
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
