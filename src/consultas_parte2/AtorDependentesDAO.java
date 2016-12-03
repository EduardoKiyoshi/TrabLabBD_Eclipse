package consultas_parte2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import consultas_parte2.TrabalhoTodosDepartamentosDAO.Row;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBconnection;

public class AtorDependentesDAO {
	private Connection conn;

    public AtorDependentesDAO(Connection conn){		
            this.conn = conn;
    }
    
    public ObservableList<Row> findAll() throws SQLException {
        ObservableList<Row> list = FXCollections.observableArrayList();
        Row row = null;
        String selectQuery = "SELECT FUNC.nomeCompletoFu AS \"Nome do Ator\" "
        		+ "FROM FUNCIONARIO FUNC JOIN tipoFuncionario tipoFUNC ON tipoFUNC.idTipoFu = FUNC.idTipoFu "
        		+ "WHERE tipoFUNC.DESCRICAOTIPOFU = 'ator' "
        		+ "INTERSECT "
        		+ "SELECT FUNC2.nomeCompletoFu "
        		+ "FROM FUNCIONARIO FUNC2 JOIN DEPENDENTE DEP ON FUNC2.idFu = DEP.idFu "
        		+ "WHERE EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DEP.DATANASCIMENTODE) < 18";
        
        try{ 
             PreparedStatement pStatement = conn.prepareStatement(selectQuery);
             ResultSet resultSet = pStatement.executeQuery();
           while (resultSet.next()) {
               row = new Row(resultSet.getString(1));
               list.add(row);
           }
        } catch (SQLException sqlex) {
           System.out.println("SQL Error" + sqlex);
           throw sqlex;
        }
        return list;
    }
    public class Row{
        private StringProperty nomeFunc;
        public Row(String nomeFunc){
            this.nomeFunc = new SimpleStringProperty(nomeFunc);
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
	}
    public static void main(String[] args){
    	try{
	    	Connection con = DBconnection.getConexao();
	    	AtorDependentesDAO dao = new AtorDependentesDAO(con);
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
