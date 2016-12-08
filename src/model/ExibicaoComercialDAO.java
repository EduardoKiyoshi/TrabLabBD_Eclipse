/*
e  * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class ExibicaoComercialDAO {
    private Connection conn;

    public ExibicaoComercialDAO(Connection conn){		
            this.conn = conn;
    }
    public ObservableList findExibicaoComercial(Comercial con) throws SQLException {
        ObservableList exibicaoList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, dataExCo, horaInicioExCo, horaFimExCo, precoExCo, cnpjCl FROM exibicaoCOMERCIAL"
        		+ " WHERE idPr = "+ con.getIdPr() + "";

        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
        	   System.out.println(createExibicaoComercial(resultSet).getDataExCo());
               //exibicaoList.add(createExibicaoComercial(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return exibicaoList;
    }
    
    private ExibicaoComercial createExibicaoComercial(ResultSet resultSet) throws SQLException {
        ExibicaoComercial con = null;
        try{			
                con = new ExibicaoComercial();
                con.setIdPr(resultSet.getInt("idPr"));
                con.setDataExCo(resultSet.getDate("dataExCo").toLocalDate());	
                con.setHoraInicioExCo(resultSet.getTimestamp("horaInicioExCo").toLocalDateTime().toLocalTime());		
                con.setHoraFimExCo(resultSet.getTimestamp("horaFimExCo").toLocalDateTime().toLocalTime());
                con.setPrecoExCo(resultSet.getInt("precoExCo"));
                con.setCnpjCl(resultSet.getString("cnpjCl"));
        }catch (SQLException sqlex) {
                System.out.println("SQL Error" + sqlex);
            throw sqlex;
        }
        return con;
    }
    
    public boolean deleteExibicaoComercial(ExibicaoComercial comer) throws SQLException{
    	Statement st = null;
		String insertQuery = "DELETE FROM EXIBICAOCOMERCIAL "
				+ "WHERE IDPR = "+comer.getIdPr()+" "
				+ "AND DATAEXCO = "+comer.getDataExCo()+""
				+ "AND HORAINICIOEXCO = "+comer.getHoraInicioExCo()+";";
		try{
			st = conn.createStatement();
			st.executeUpdate(insertQuery);
			return true;
		}catch (SQLException sqlex) {
		      System.out.println("SQL Error" + sqlex);
		      throw sqlex;
		}		
    }
    
    public boolean insertExibicaoComercial(ExibicaoComercial comer) throws SQLException {
  		Statement pstmt = null;
  		String insertQuery = "INSERT INTO exibicaoComercial"
  				+ " (idPr, dataExCo, horaInicioExCo, horaFimExCo, precoExCo, cnpjCl)VALUES"
  				+ "(SEQ_idPr.CURRVAL, "
  				+ "TO_DATE('"+comer.getDataExCo()+"'), "
  				+ "TO_TIMESTAMP('"+comer.getHoraInicioExCo()+"','HH24:MI:SS'), "
  				+ "TO_TIMESTAMP('"+comer.getHoraFimExCo()+"','HH24:MI:SS'), "
  				+ ""+comer.getPrecoExCo()+", "
  				+ "'"+comer.getCnpjCl()+"');";
  		try{
  			pstmt = conn.createStatement();

  			pstmt.executeUpdate(insertQuery);
  			return true;
  		}catch (SQLException sqlex) {
  		      System.out.println("SQL Error" + sqlex);
  		      throw sqlex;
  		}		
  	}
    
    public boolean updateExibicaoComercial(ExibicaoComercial comer, ExibicaoComercial comerNovo) throws SQLException {
  		Statement st = null;
  		String insertQuery = "UPDATE EXIBICAOCOMERCIAL "
  				+ "SET idPr = "+comerNovo.getIdPr()+""
  				+ "dataExCo = TO_DATE('"+comerNovo.getDataExCo()+"'), "
  				+ "horaInicioExCo = TO_TIMESTAMP('"+comerNovo.getHoraInicioExCo()+"','HH24:MI:SS'), "
  				+ "horaFimExCo = TO_TIMESTAMP('"+comerNovo.getHoraFimExCo()+"','HH24:MI:SS'), "
  				+ "precoExCo = "+comerNovo.getPrecoExCo()+""
  				+ "WHERE IDPR = "+comer.getIdPr()+" "
  				+ "AND DATAEXCO = "+comer.getDataExCo()+""
  				+ "AND HORAINICIOEXCO = "+comer.getHoraInicioExCo()+";";

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
