/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emissoralabbd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.ComercialDAO;
import util.DBconnection;

/**
 *
 * @author lenovo
 */
public class StoredProcedureTest {
    private    static void callProcedure(String cpf) {
        Connection con = DBconnection.getConexao();
        CallableStatement cs = null;
        
        try {
            cs = con.prepareCall("{call gerarRelatorioFunc(?)}");
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.setString(1, cpf);
            con.prepareCall( "begin dbms_output.enable(:1); end;" ); 
            cs.execute();
            
            /*ResultSet rs = cs.getResultSet();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }*/
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
        }
        finally {
            if (cs != null) {
                try {
                    cs.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("SQLException: " + e.getMessage());
                }
            }
        }
    }
    public static void main(String[] args){
        callProcedure("514.106.873-04");        
    }
}
