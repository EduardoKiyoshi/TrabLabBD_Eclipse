package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
    
    /**
     * Insere nova tupla jornal na base.
     * @param titulo		titulo do jornal
     * @param descricao		descricao do jornal
     * @param departamento	numero de departamento do jornal
     * @param duracao		duracao em minutos do jornal
     * @param abrangencia	valor abrangencia do jornal, apenas 'NACIONAL' ou 'INTERNACIONAL'
     */
    
    /*
     * TODO
     * Verificar se abrangencia tem valor valido, se nao lança exceção (criar propria
     */
    public void insereJornal(String titulo, String descricao, int departamento, int duracao, String abrangencia)
    throws SQLException{
    	String query = "INSERT INTO jornal (idPr,btituloPr, descricaoPr,bidDe,bduracaoJo, abrangenciaJo)"
    					+ " values(SEQ_idPr.NEXTVAL, ?, ?, ?, ?, ?);";
    					   
    					   
    	try{
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		pstmt.setString(1, titulo);
    		pstmt.setString(2, descricao);
    		pstmt.setInt(3, departamento);
    		pstmt.setInt(4, duracao);
    		pstmt.setString(5, abrangencia);
    		
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
    
    /**
     * Remove da base a tupla jornal com o numero de id selecionado
     * @param id	numero de id do jornal
     */
    /*
     * TODO
     * Metodo deve lançar exceção quando id não é encontrado ou houve falha na conexao
     */
    public void deletaJornal(int idpr) throws SQLException{
    	String query = "DELETE FROM JORNAL WHERE idPr = ?;";
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
    
    /**
     * Metodo para atualizar valores da tupla
     * 
     * @param idpr			id do jornal
     * @param titulo		titulo do jornal
     * @param descricao		descricao do jornal
     * @param departamento	numero de departamento do jornal
     * @param duracao		duracao em minutos do jornal
     * @param abrangencia	valor abrangencia do jornal, apenas 'NACIONAL' ou 'INTERNACIONAL'
     */
    public void atualizaJornal(int idpr, String titulo, String descricao, int departamento, int duracao, 
    		String abrangencia) throws SQLException{
    	String query = "UPDATE JORNAL "+
    				   "SET tituloPr = ?, "+
    				   "SET descricaoPr = ?, "+
    				   "SET idDe = ?, "+
    				   "SET duracaoJo = ?, "+
    				   "SET abrangenciaJo = ?  "+
    				   "WHERE idPr = ?;";
    					   
    					   
    	try{
    		PreparedStatement pstmt = conn.prepareStatement(query);
    		
    		pstmt.setString(1, titulo);
    		pstmt.setString(2, descricao);
    		pstmt.setInt(3, departamento);
    		pstmt.setInt(4, duracao);
    		pstmt.setString(5, abrangencia);
    		pstmt.setInt(6, idpr);
    		
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
    
    // Consultas
    
    /**
     * Busca de jornal por titulo
     * @param titulo
     * @return	ObservableList com jornais encontrados
     * @throws SQLException
     */
    public ObservableList findJornalTitulo(String titulo) throws SQLException {
        ObservableList jornalList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, tituloPr, descricaoPr, duracaoJo, abrangenciaJo, idDe FROM JORNAL"+
        					 " WHERE tituloPr = ?;";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             pStatement.setString(1, titulo);
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
    
    public ObservableList findJornalAbranDura(String abrangencia, int duracaoMin) throws SQLException {
        ObservableList jornalList = FXCollections.observableArrayList();
        String selectQuery = "SELECT idPr, tituloPr, descricaoPr, duracaoJo, abrangenciaJo, idDe FROM JORNAL"+
        					 " WHERE abrangenciaJo = ? AND duracaoJo >= ?;";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             pStatement.setString(1, abrangencia);
             pStatement.setInt(2, duracaoMin);
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
    
}