package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExibicaoJornalDAO {
	private Connection conn;

    public ExibicaoJornalDAO(Connection conn){		
            this.conn = conn;
    }
    public ObservableList findExibicaoJornal(Jornal con) throws SQLException {
        ObservableList exibicaoList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, idFu, dataExJo, horaInicioExJo, horaFimExJo, ibopeExJo FROM exibicaoJORNAL"
        		+ " WHERE idPr = "+ con.getIdPr() + "";

        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   //System.out.println(createExibicaoJornal(resultSet).getDataExJo());
               exibicaoList.add(createExibicaoJornal(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return exibicaoList;
    }
    
    private ExibicaoJornal createExibicaoJornal(ResultSet resultSet) throws SQLException {
        ExibicaoJornal con = null;
        try{			
                con = new ExibicaoJornal();
                con.setIdPr(resultSet.getInt("idPr"));
                con.setDataExJo(resultSet.getDate("dataExJo").toLocalDate());	
                con.setHoraInicioExJo(resultSet.getTimestamp("horaInicioExJo").toLocalDateTime().toLocalTime());		
                con.setHoraFimExJo(resultSet.getTimestamp("horaFimExJo").toLocalDateTime().toLocalTime());
                con.setIbopeExJo(resultSet.getInt("ibopeExJo"));
                con.setIdFu(resultSet.getInt("idFu"));
        }catch (SQLException sqlex) {
                System.out.println("SQL Error" + sqlex);
            throw sqlex;
        }
        return con;
    }
    
    public void insereExibicaoJornal(int idPr, int idFu, Date dataExJo, Timestamp horaInicio, 
    		Timestamp horaFim, int ibope) throws SQLException{
    	    	String query = "INSERT INTO exibicaoJornal (idPr, idFu, dataExJo, horaInicioExJo, "
    	    					+ "horaFimExJo, ibopeExJo)"
    	    					+" values(?, ?, ?, ?, ?, ?);";
    	    					   
    	    					   
    	    	try{
    	    		PreparedStatement pstmt = conn.prepareStatement(query);
    	    		pstmt.setInt(1, idPr);
    	    		pstmt.setInt(2, idFu);
    	    		pstmt.setDate(3, dataExJo);
    	    		pstmt.setTimestamp(4, horaInicio);
    	    		pstmt.setTimestamp(5, horaFim);
    	    		pstmt.setInt(6, ibope);
    	    		
    	    		pstmt.executeUpdate();
    	    		
    	    	}
    	    	catch(SQLException sqlex){
    	    		/*
    	    		 * TODO
    	    		 * Detectar tipo de erro (dado invalido ou falha de conexao)
    	    		 * Criar alert dialog detalhando erro
    	    		 */
    	    		sqlex.printStackTrace();
    	    		throw sqlex;
    	    		
    	    	}
    	    	
    	    }
    
    public void deletaExibeJornal(int idPr, int idFu, Date dataExJo, Timestamp horaInicio) throws SQLException{
    	String query = "DELETE FROM JORNAL WHERE idPr = ? AND idFU = ? AND dataExJo = ? AND horaInicio = ?;";
    	try{
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, idPr);
    		pstmt.setInt(2, idFu);
    		pstmt.setDate(3, dataExJo);
    		pstmt.setTimestamp(4, horaInicio);
    		
    		pstmt.executeUpdate();
    		
    	}
    	catch(SQLException sqlex){
    		/*
    		 * TODO
    		 * Detectar tipo de erro (dado invalido ou falha de conexao)
    		 * Criar alert dialog detalhando erro
    		 */
    		sqlex.printStackTrace();
    		throw sqlex;
    		
    	}
    }
    
    public void alteraExibicaoJornal(int idPr, int idFu, Date dataExJo, Timestamp horaInicio,
    		int novoIdPr, int novoIdFu, Date novoDataExJo, Timestamp novoHoraInicio,
    		Timestamp novoHoraFim, int novoIbope) throws SQLException{
    	    	String query = "UPDATE exibicaoJornal "
    	    					+"SET idPr = ?, "
    	    					+"SET idFu = ?, "
    	    					+"SET dataExJo = ?, "
    	    					+"SET horaInicio = ?, "
    	    					+"SET horaFim = ?, "
    	    					+"SET ibope = ? "
    	    					+"WHERE idPr = ? AND idFU = ? AND dataExJo = ? AND horaInicio = ?;";
    	    					   
    	    					   
    	    	try{
    	    		PreparedStatement pstmt = conn.prepareStatement(query);
    	    		pstmt.setInt(1, novoIdPr);
    	    		pstmt.setInt(2, novoIdFu);
    	    		pstmt.setDate(3, novoDataExJo);
    	    		pstmt.setTimestamp(4, novoHoraInicio);
    	    		pstmt.setTimestamp(5, novoHoraFim);
    	    		pstmt.setInt(6, novoIbope);
    	    		pstmt.setInt(7, idPr);
    	    		pstmt.setInt(8, idFu);
    	    		pstmt.setDate(9, dataExJo);
    	    		pstmt.setTimestamp(10, horaInicio);
    	    		
    	    		pstmt.executeUpdate();
    	    		
    	    	}
    	    	catch(SQLException sqlex){
    	    		/*
    	    		 * TODO
    	    		 * Detectar tipo de erro (dado invalido ou falha de conexao)
    	    		 * Criar alert dialog detalhando erro
    	    		 */
    	    		sqlex.printStackTrace();
    	    		throw sqlex;
    	    		
    	    	}
    	    	
    	    }
    
    //Consultas

}