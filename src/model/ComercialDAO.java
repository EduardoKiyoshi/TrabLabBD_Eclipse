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
    
    //Insere Comercial
    public void insereComercial(String titulo, String descricao, int departamento)
    	    throws SQLException{
    	    	String query = "INSERT INTO comercial (idPr, tituloPr, descricaoPr, idDe)"
    	    			+ "VALUES (SEQ_idPr.NEXTVAL, ?, ?, ?);";
    	    					   
    	    					   
    	    	try{
    	    		PreparedStatement pstmt = conn.prepareStatement(query);
    	    		pstmt.setString(1, titulo);
    	    		pstmt.setString(2, descricao);
    	    		pstmt.setInt(3, departamento);
    	    		
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
    
    //Deleta comercial
    public void deletaComercial(int idpr) throws SQLException{
    	String query = "DELETE FROM COMERCIAL WHERE idPr = ?;";
    	try{
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setInt(1, idpr);
    		
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
    
    //Atualiza comercial de id
    public void atualizaComercial(int idpr, String titulo, String descricao, int departamento) 
    		throws SQLException{
    	String query = "UPDATE JORNAL "+
    				   "SET tituloPr = ?, "+
    				   "SET descricaoPr = ?, "+
    				   "SET idDe = ?, "+
    				   "WHERE idPr = ?;";
    					   
    					   
    	try{
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1, titulo);
    		pstmt.setString(2, descricao);
    		pstmt.setInt(3, departamento);
    		pstmt.setInt(4, idpr);
    		
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
}