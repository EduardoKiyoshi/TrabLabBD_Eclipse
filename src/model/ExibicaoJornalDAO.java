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
    	Statement st = null;
		String insertQuery = "DELETE FROM EXIBICAOJORNAL "
				+ "WHERE IDPR = "+jor.getIdPr()+" "
				+ "AND IDFU = "+jor.getIdFu()+" "
				+ "AND DATAEXJO = TO_DATE('"+jor.getDataExJo()+"')"
				+ "AND HORAINICIOEXJO = TO_TIMESTAMP('"+jor.getHoraInicioExJo()+"','HH24:MI:SS');";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
    }
    
    public boolean insertExibicaoJornal(ExibicaoJornal jor) throws SQLException {
  		Statement pstmt = null;
  		String insertQuery = "INSERT INTO exibicaoJornal (idPr, idFu, dataExJo, horaInicioExJo, horaFimExJo, ibopeExJo)"
  				+ "VALUES("+jor.getIdPr()+", "
  				+ ""+jor.getIdFu()+", "
  				+ "TO_DATE('"+jor.getDataExJo()+"','DD/MM/YYYY'), "
  				+ "TO_TIMESTAMP('"+jor.getHoraInicioExJo()+"','HH24:MI:SS'), "
  				+ "TO_TIMESTAMP('"+jor.getHoraFimExJo()+"','HH24:MI:SS'), "
  				+ ""+jor.getIbopeExJo()+");";

  		try{
  			pstmt = conn.createStatement();
  			pstmt.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}		
  	}
    
      public boolean updateExibicaoJornal(ExibicaoJornal jor, ExibicaoJornal jorNovo) throws SQLException {
  		Statement st = null;
  		String insertQuery = "UPDATE EXIBICAOJORNAL SET idPr = "+jorNovo.getIdPr()+""
  				+ "SET idFu = "+jorNovo.getIdFu()+", "
  				+ "dataExJo = TO_DATE('"+jorNovo.getDataExJo()+"','DD/MM/YYYY'), "
  				+ "horaInicioExJo = TO_TIMESTAMP('"+jorNovo.getHoraInicioExJo()+"','HH24:MI:SS'), "
  				+ "horaFimExJo = TO_TIMESTAMP('"+jorNovo.getHoraFimExJo()+"','HH24:MI:SS'), "
  				+ "ibopeExJo = "+jorNovo.getIbopeExJo()+""
  				+ "WHERE IDPR = "+jor.getIdPr()+" AND IDFU = "+jor.getIdFu()+" "
  				+ "AND DATAEXJO = TO_DATE('"+jor.getDataExJo()+"','DD/MM/YYYY')"
				+ "AND HORAINICIOEXJO = TO_TIMESTAMP('"+jor.getHoraInicioExJo()+"','HH24:MI:SS');";

  		try{
  			st = conn.createStatement();
  			st.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}
     }
}
