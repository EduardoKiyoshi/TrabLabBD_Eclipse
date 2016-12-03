package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
/**
 *
 * @author felipeaa
 */
public class DBconnection {
    private static Connection con = null;
    public DBconnection(){
    }
    
    /**
     * M�todo para criar um savepoint
     * 
     * @param con
     * @throws SQLException
     */
    public Savepoint setSavepoint() throws SQLException {
	  try {
		  con.setAutoCommit(false);  
	    return con.setSavepoint();
	  } catch (SQLException ex) {
	    throw ex;
	  }
    }
    
    /**
     * M�todo que efetua um rollback em um savepoint
     * 
     * @param con
     * @throws SQLException
     */
    public void rollbackToSavepoint(Savepoint savePoint) throws SQLException{
        con.rollback(savePoint);
    }
    /**
     * M�todo para fechar a conex�o com o banco de dados
     * 
     * @param con
     * @throws SQLException
     */
    public void disconect(Connection con) throws SQLException{
        con.close();
    }
    /**
     * Metodo para efetuar o commit no banco
     *
     *
     * @param con
     * @throws SQLException
     */
    public void commit(Connection con) throws SQLException{
        con.commit();
    }
    /**
     * M�todo para efetuar o rollback no banco
     *
     * @param con
     * @throws SQLException
     */
    public void rollback(Connection con) throws SQLException{
        con.rollback();
    }
    /**
     *
     * Efetua a conex�o com o banco de dados
     *
     * @return
     */
    public static Connection getConexao(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        }catch(Exception e){
                    System.out.println("Classe "+e);
        }
        try
        {
            Connection con = 
DriverManager.getConnection("jdbc:oracle:thin:@grad.icmc.usp.br:15215:orcl","a9778536","a9778536");
            
            return con;
        }catch(Exception e){
            System.out.println("conectar"+e);
            return null;
        }
    }
}