/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultas_parte2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBconnection;

/**
 *
 * @author lenovo
 */
public class TrabalhoTodosDepartamentosDAO {
    private Connection conn;

    public TrabalhoTodosDepartamentosDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList<Row> findAll() throws SQLException {
    	ObservableList<Row> lista = FXCollections.observableArrayList();
        
        String selectQuery = "SELECT FUNC.nomeCompletoFu AS \"Nome do funcionário\","
        		+"TRAB.DATAINICIOTR AS \"Data de ínicio\","
        		+"NVL(TO_CHAR(TRAB.DATAFIMTR), 'Ainda trabalha') AS \"Data de término\","
        		+"DPTO.NOMEDE AS \"Departamento\""
        		+" FROM FUNCIONARIO FUNC JOIN TRABALHO TRAB"
        		+" ON FUNC.IDFU = TRAB.IDFU"
        		+" JOIN DEPARTAMENTO DPTO"
        		+" ON TRAB.IDDE = DPTO.IDDE"
        		+" WHERE NOT EXISTS("
        		+"    (SELECT idDe FROM departamento)"
        		+"    MINUS"
        		+"    (SELECT idDe FROM TRABALHO WHERE FUNC.idFu = TRABALHO.idFu )"
        		+")"
        		+" ORDER BY FUNC.nomeCompletoFu, TRAB.DATAINICIOTR";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
               lista.add(new Row(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return lista;
    }
    public class Row{
        private StringProperty nomeFunc;
        private StringProperty dataInicio;
        private StringProperty dataTermino;
        private StringProperty nomeDe;
        public Row(String nomeFunc, String dataInicio, String dataTermino, String nomeDe){
            this.nomeFunc = new SimpleStringProperty(nomeFunc);
            this.dataInicio = new SimpleStringProperty(dataInicio);
            this.dataTermino = new SimpleStringProperty(dataTermino);
            this.nomeDe = new SimpleStringProperty(nomeDe);
        }
        public String getNomeFunc (){
        	return nomeFunc.get();
        }
        public StringProperty nomeFuncProperty() {
        	return nomeFunc;
        }
        public void setNomeFunc (String nomeFunc){
        	this.nomeFunc.set(nomeFunc); 
        }
        public StringProperty dataInicioProperty() {
        	return dataInicio;
        }
        public StringProperty dataTerminoProperty() {
        	return dataTermino;
        }
        public StringProperty nomeDeProperty() {
        	return nomeDe;
        }
	}
    public static void main(String[] args){
    	try{
	    	Connection con = DBconnection.getConexao();
	    	TrabalhoTodosDepartamentosDAO dao = new TrabalhoTodosDepartamentosDAO(con);
	    	List<Row> lista = dao.findAll();
	    	//LocalDate data = DateUtil.parse("12/12/2013");
                for(Row el : lista ){
                    System.out.println(el.getNomeFunc());
                }
	    	
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    }
}
