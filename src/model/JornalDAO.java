package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DateUtil;

public class JornalDAO {
	private Connection conn;

    public JornalDAO(Connection conn){		
            this.conn = conn;
    }

   
    public ObservableList findAll() throws SQLException {
        ObservableList jornalList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, tituloPr, descricaoPr, duracaoJo, abrangenciaJo, idDe FROM JORNAL";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   jornalList.add(createJornal(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return jornalList;
    }
    
    
    private Jornal createJornal(ResultSet resultSet) throws SQLException {
        Jornal con = null;
        ExibicaoJornalDAO exibicaoDAO = null;
        ObservableList exibicaoList = FXCollections.observableArrayList();
        try{			
                con = new Jornal();
                con.setIdPr(resultSet.getInt("idPr"));
                con.setTituloPr(resultSet.getString("tituloPr"));	
                con.setDescricaoPr(resultSet.getString("descricaoPr"));		
                con.setIdDe(resultSet.getInt("idDe"));
                con.setDuracaoJo(resultSet.getInt("duracaoJo"));
                con.setAbrangenciaJo(resultSet.getString("abrangenciaJo"));
                try{
                    exibicaoDAO = new ExibicaoJornalDAO(conn);
                    exibicaoList = exibicaoDAO.findExibicaoJornal(con);
                    con.setExibicaoComercial(exibicaoList);
                }catch (SQLException sqlex) {
                    System.out.println("SQL Error" + sqlex);
                }
        }catch (SQLException sqlex) {
                System.out.println("SQL Error" + sqlex);
            throw sqlex;
        }
        return con;
    }
    
    public boolean deleteJornal(int idPr) throws SQLException{
    	Statement st = null;
		String insertQuery = "DELETE FROM JORNAL WHERE IDPR = "+Integer.toString(idPr)+";";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
    }
    
    public boolean insertJornal(Jornal jor) throws SQLException {
  		PreparedStatement pstmt = null;
  		String insertQuery = "INSERT INTO JORNAL (idPr, tituloPr, descricaoPr, idDe, duracaoJo, abrangenciaJo)"
  				+ "VALUES(SEQ_idPr.NEXTVAL, ?, ?, ?, ?, ? );";
  		try{
  			pstmt = conn.prepareStatement(insertQuery);
  			pstmt.setString(1, jor.getTituloPr());
  			pstmt.setString(2, jor.getDescricaoPr());
  			pstmt.setInt(3, jor.getIdDe());
  			pstmt.setInt(4, jor.getDuracaoJo());
  			pstmt.setString(5, jor.getAbrangenciaJo());
  			pstmt.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}		
  	}
    
      public boolean updateJornal(Jornal jor) throws SQLException {
  		PreparedStatement st = null;
  		String insertQuery = "UPDATE JORNAL SET tituloPr = ?, "
  				+ "descricaoPr = ?, "
  				+ "idDe = ?, "
  				+ "duracaoJo = ?, "
  				+ "abrangenciaJo = ?"
  				+ "WHERE IDPR = ?;";

  		try{
  			st = conn.prepareStatement(insertQuery);
  			st.setString(1, jor.getTituloPr());
  			st.setString(2, jor.getDescricaoPr());
  			st.setInt(3, jor.getIdDe());
  			st.setInt(4, jor.getDuracaoJo());
  			st.setString(5, jor.getAbrangenciaJo());
  			st.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}
     }
}
