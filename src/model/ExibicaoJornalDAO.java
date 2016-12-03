package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
