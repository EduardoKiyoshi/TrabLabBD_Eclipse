package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
}
