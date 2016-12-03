package consultas_parte2;

import java.sql.Connection;
import java.sql.SQLException;

import emissoralabbd.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import util.DBconnection;

public class AtorDependenteOverviewController {
private MainApp mainApp;
    
    @FXML
    private TableView<AtorDependentesDAO.Row> todosTable = new TableView<AtorDependentesDAO.Row>(); 
    @FXML
    private TableColumn<AtorDependentesDAO.Row, String> nomeFuncColumn;
    @FXML
    private void initialize() {
        nomeFuncColumn.setCellValueFactory(
               cellData -> cellData.getValue().nomeFuncProperty());   
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        // Add observable list data to the table
        Connection conn = DBconnection.getConexao();
        AtorDependentesDAO dao = new AtorDependentesDAO(conn);
        ObservableList<AtorDependentesDAO.Row> lista = FXCollections.observableArrayList();
        try{
            lista = dao.findAll();
            for(AtorDependentesDAO.Row e : lista){
                System.out.println(e.getNomeFunc());
            }
            //System.out.println(dao.findAll());
            todosTable.setItems(lista);        	
        }catch (SQLException sqlex) {
			System.out.println("SQL Error" + sqlex);		    
		}
       
    }
}
