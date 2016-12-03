/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalTime;

import util.DBconnection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class Comercial {
    private IntegerProperty idPr;
    private StringProperty tituloPr;
    private StringProperty descricaoPr;
    private IntegerProperty idDe;
    private ObservableList<ExibicaoComercial> exibicaoComercial;
    
    public Comercial(){
    	this.idPr = new SimpleIntegerProperty();
    	this.tituloPr = new SimpleStringProperty();
    	this.descricaoPr = new SimpleStringProperty();
    	this.idDe = new SimpleIntegerProperty();
    	this.exibicaoComercial = FXCollections.observableArrayList();
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
    
    public int getIdDe() {
            return idDe.get();
    }
    public IntegerProperty idDeProperty() {
            return idDe;
    }
    public void setIdDe(int idDe) {
            this.idDe.set(idDe);		
    }
    
    public ObservableList getExibicaoComercial() {
            return exibicaoComercial;
    }
    /*public IntegerProperty idDeProperty() {
            return idDe;
    }*/
    public void setExibicaoComercial(ObservableList exibicaoComercial) {
            this.exibicaoComercial = exibicaoComercial;
    }
    
    public static void main(String[] args){
    	try{
	    	Connection con = DBconnection.getConexao();
	    	ComercialDAO dao = new ComercialDAO(con);
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
