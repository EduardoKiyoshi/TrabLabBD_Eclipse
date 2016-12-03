/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import util.DBconnection;
import util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class Jornal {
    private IntegerProperty idPr;
    private StringProperty tituloPr;
    private StringProperty descricaoPr;
    private IntegerProperty  duracaoJo;
    private StringProperty abrangenciaJo;
    private ObservableList<ExibicaoJornal> exibicaoJornal;
    private IntegerProperty idDe;
    
	
	public Jornal(){
		this.idPr = new SimpleIntegerProperty();
		this.tituloPr = new SimpleStringProperty();
		this.descricaoPr = new SimpleStringProperty();
		this.duracaoJo = new SimpleIntegerProperty();
		this.abrangenciaJo = new SimpleStringProperty();
		this.exibicaoJornal = FXCollections.observableArrayList();
		this.idDe = new SimpleIntegerProperty();
	}
    
    public int getIdPr() {
            return idPr.get();
    }
    public IntegerProperty idPrProperty() {
            return idPr;
    }
    public void setIdPr(int idPr) {
            this.idPr.set(idPr);		
    }
    
    public int getIdDe() {
        return idDe.get();
	}
	public IntegerProperty idDeProperty() {
	        return idDe;
	}
	public void setIdDe(int idDe) {
	        this.idDe.set(idDe);		
	}

    public String getTituloPr() {
            return tituloPr.get();
    }
    public StringProperty tituloPrProperty() {
            return tituloPr;
    }
    public void setTituloPr(String tituloPr) {		
            this.tituloPr.set(tituloPr);
    }
    
    
    public String getDescricaoPr() {
            return descricaoPr.get();
    }
    public StringProperty descricaoPrProperty() {
            return descricaoPr;
    }
    public void setDescricaoPr(String descricaoPr) {		
            this.descricaoPr.set(descricaoPr);
    }
    
    public int getDuracaoJo() {
            return duracaoJo.get();
    }
    public IntegerProperty duracaoJoProperty() {
            return duracaoJo;
    }
    public void setDuracaoJo(int duracaoJo) {
            this.duracaoJo.set(duracaoJo);
    }
    
    public String getAbrangenciaJo() {
            return abrangenciaJo.get();
    }
    public StringProperty abrangenciaJoProperty() {
            return abrangenciaJo;
    }
    public void setAbrangenciaJo(String abrangenciaJo) {		
            this.abrangenciaJo.set(abrangenciaJo);
    }
    

    public ObservableList getExibicaoComercial() {
            return exibicaoJornal;
    }
    /*public IntegerProperty idDeProperty() {
            return idDe;
    }*/
    public void setExibicaoComercial(ObservableList exibicaoJornal) {
            this.exibicaoJornal = exibicaoJornal;
    }
    

    public static void main(String[] args){
    	try{
	    	Connection con = DBconnection.getConexao();
	    	JornalDAO dao = new JornalDAO(con);
	    	ObservableList lista = FXCollections.observableArrayList();
	    	lista = dao.findAll();
	    	//LocalDate data = DateUtil.parse("12/12/2013");
	    	System.out.println(lista);
    	}catch (SQLException e) {
			// TODO: handle exception
    		System.out.println(e);
		}
    }
    
}
