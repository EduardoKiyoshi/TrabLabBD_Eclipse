package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    
    public boolean deleteExibicaoJornal(ExibicaoJornal jor) throws SQLException{
    	PreparedStatement st = null;
		String insertQuery = "DELETE FROM EXIBICAOJORNAL WHERE IDPR = ? AND IDFU = ? AND DATAEXJO = ?"
				+ "AND HORAINICIOEXJO = ? AND HORAFIMEXJO = ?;";
		try{
			st = conn.prepareStatement(insertQuery);
			st.setInt(1, jor.getIdPr());
			st.setInt(2, jor.getIdFu());
			st.setString(3, jor.getDataExJo());
			st.setTimestamp(4, Timestamp.valueOf(jor.getHoraInicioExJo().toString()));
			st.setTimestamp(5, Timestamp.valueOf(jor.getHoraFimExJo().toString()));
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
    }
    
    public boolean insertExibicaoJornal(ExibicaoJornal jor) throws SQLException {
  		PreparedStatement pstmt = null;
  		String insertQuery = "INSERT INTO exibicaoJornal (idPr, idFu, dataExJo, horaInicioExJo, horaFimExJo, ibopeExJo)"
  				+ "VALUES(?, ?, ?, ?, ?, ?);";
  		try{
  			pstmt = conn.prepareStatement(insertQuery);
  			pstmt.setInt(1, jor.getIdPr());
  			pstmt.setInt(2, jor.getIdFu());
  			pstmt.setString(3, jor.getDataExJo());
  			pstmt.setTimestamp(4, Timestamp.valueOf(jor.getHoraInicioExJo().toString()));
  			pstmt.setTimestamp(5, Timestamp.valueOf(jor.getHoraFimExJo().toString()));
  			pstmt.setInt(6, jor.getIbopeExJo());
  			pstmt.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}		
  	}
    
      public boolean updateExibicaoJornal(ExibicaoJornal jor, ExibicaoJornal jorNovo) throws SQLException {
  		PreparedStatement st = null;
  		String insertQuery = "UPDATE EXIBICAOJORNAL SET idPr = ?"
  				+ "SET idFu = ?, "
  				+ "dataExJo = ?, "
  				+ "horaInicioExJo = ?, "
  				+ "horaFimExJo = ?, "
  				+ "ibopeExJo = ?"
  				+ "WHERE IDPR = ? AND IDFU = ? AND DATAEXJO = ?"
				+ "AND HORAINICIOEXJO = ? AND HORAFIMEXJO = ?;";

  		try{
  			st = conn.prepareStatement(insertQuery);
  			st.setInt(1, jorNovo.getIdPr());
  			st.setInt(2, jorNovo.getIdFu());
  			st.setString(3, jorNovo.getDataExJo());
  			st.setTimestamp(4, Timestamp.valueOf(jorNovo.getHoraInicioExJo().toString()));
  			st.setTimestamp(5, Timestamp.valueOf(jorNovo.getHoraFimExJo().toString()));
  			st.setInt(6, jorNovo.getIbopeExJo());
  			st.setInt(7, jor.getIdPr());
  			st.setInt(8, jor.getIdFu());
  			st.setString(9, jor.getDataExJo());
  			st.setTimestamp(10, Timestamp.valueOf(jor.getHoraInicioExJo().toString()));
  			st.setTimestamp(11, Timestamp.valueOf(jor.getHoraFimExJo().toString()));
  			st.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}
     }
}
