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
               exibicaoList.add(createExibicaoComercial(resultSet));
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
}
