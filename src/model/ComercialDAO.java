/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class ComercialDAO {
    private Connection conn;

    public ComercialDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList findAll() throws SQLException {
        ObservableList comercialList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, tituloPr, descricaoPr, idDe FROM COMERCIAL";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
               comercialList.add(createComercial(resultSet));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return comercialList;
    }
    
    
    private Comercial createComercial(ResultSet resultSet) throws SQLException {
        Comercial con = null;
        ExibicaoComercialDAO exibicaoDAO = null;
        ObservableList exibicaoList = FXCollections.observableArrayList();
        try{			
                con = new Comercial();
                con.setIdPr(resultSet.getInt("idPr"));
                con.setTituloPr(resultSet.getString("tituloPr"));	
                con.setDescricaoPr(resultSet.getString("descricaoPr"));		
                con.setIdDe(resultSet.getInt("idDe"));
                try{
                    exibicaoDAO = new ExibicaoComercialDAO(conn);
                    exibicaoList = exibicaoDAO.findExibicaoComercial(con);
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
