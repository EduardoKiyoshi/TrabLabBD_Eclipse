package application;
	
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import model.ComercialDAO;
import model.Dependente;
import model.DependenteDAO;
import model.Funcionario;
import model.FuncionarioDAO;
import model.Gerencia;
import model.GerenciaDAO;
import model.Trabalho;
import model.TrabalhoDAO;
import util.DBconnection;
import util.DateUtil;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try{
	    	Connection con = DBconnection.getConexao();
	    	TrabalhoDAO daoTr = new TrabalhoDAO(con);
	    	FuncionarioDAO daoFu = new FuncionarioDAO(con);
	    	DependenteDAO daoDep = new DependenteDAO(con);
	    	GerenciaDAO daoGe = new GerenciaDAO(con);
	    	Funcionario func = new Funcionario();
	    	
	    	Dependente dep = new Dependente();
	    	dep.setIdFu(3);
	    	dep.setNomeCompletoDe("pedro");
	    	dep.setDataNascimentoDe(DateUtil.parse("12/12/1212"));
	    	dep.setSexoDe("F");
	    	//daoDep.updateDependente(dep.getIdFu(), dep.getNomeCompletoDe(), dep);
	    	//Funcionario func = new Funcionario();
	    	func.setIdFu(109);
	    	func.setCpfFu("333.333.333-12");
	    	String data = new String("12/12/2015");
	    	func.setDataNascimentoFu(DateUtil.parse(data));
	    	func.setIdTipoFu(1);
	    	func.setNomeCompletoFu("Ana Barbosa");
	    	func.setSalarioFu("1200");
	    	//daoFu.updateFuncionario(func.getIdFu(), func);
	    	
	    	Gerencia ger = new Gerencia();
	    	ger.setIdDe(1);
	    	ger.setIdFu(1);
	    	ger.setDataInicioGe(DateUtil.parse("28/05/2011"));
	    	//Checar se é maior q a data de comeco
	    	//ger.setDataFimGe(DateUtil.parse("12/12/2015"));	    	
	    	//daoTr.insertTrabalho(trab);
	    	daoGe.updateGerencia(ger, DateUtil.parse("28/05/2020"));
	    	/*
	    	Dependente dep = new Dependente();
	    	daoDep.deleteDependente(2, "pedro");*/
	    	/*
	    	TrabalhoDAO  = new TrabalhoDAO(con);
	    	
	    	ObservableList trabalhoList = null;	    	
	    	trabalhoList = daoTr.find(func);
	    	if(trabalhoList.isEmpty() == true){	    		
	    		System.out.println("Lista Vazia");
	    	}else{
	    		System.out.println("Lista com elementos");	    		
	    	}
	    	/*FuncionarioDAO dao = new FuncionarioDAO(con);
	    	Funcionario func = new Funcionario();
	    	func.setIdFu(109);
	    	func.setCpfFu("333.333.333-12");
	    	String data = new String("12/12/2015");
	    	func.setDataNascimentoFu(DateUtil.parse(data));
	    	func.setIdTipoFu(1);
	    	func.setNomeCompletoFu("Pedro Barbosa");
	    	func.setSalarioFu("1200");
	    	dao.deleteFuncionario(33);
	    	
	    	
	    	daoTr.deleteTrabalho(35, 4, DateUtil.parse("25/01/2014"));
	    	*/
	    	//DependenteDAO dao = new DependenteDAO(con);
	    	//Dependente func = new Dependente();
	    	//dao.deleteDependente(1, nomeCompletoDe);
	    	//ObservableList lista = FXCollections.observableArrayList();
	    	//lista = dao.findAll();
	    	//LocalDate data = DateUtil.parse("12/12/2013");
	    	//System.out.println(lista);
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
