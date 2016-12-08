/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

import util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author lenovo
 */
public class ExibicaoJornal {
    private IntegerProperty idPr;
    private IntegerProperty idFu;
    private ObjectProperty<String> dataExJo;
    private ObjectProperty<String>  horaInicioExJo;
    private ObjectProperty<String>  horaFimExJo;
    private IntegerProperty ibopeExJo;
    private Funcionario apresentador;
	
	public ExibicaoJornal(){
		this.idPr = new SimpleIntegerProperty();
		this.idFu = new SimpleIntegerProperty();
    	this.dataExJo = new SimpleObjectProperty<String>();
    	this.horaInicioExJo = new SimpleObjectProperty<String>();
    	this.horaFimExJo = new SimpleObjectProperty<String>();
    	this.ibopeExJo = new SimpleIntegerProperty();
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
    
    public int getIdFu() {
            return idFu.get();
    }
    public IntegerProperty idFuProperty() {
            return idFu;
    }
    public void setIdFu(int idFu) {
            this.idFu.set(idFu);		
    }
    
    public String getDataExJo() {
            return dataExJo.get();
    }
    public ObjectProperty<String> dataExJoProperty() {
            return dataExJo;
    }
    public void setDataExJo(String dataExJo) {
            this.dataExJo.set(dataExJo);
    }
    
    public String getHoraInicioExJo() {
            return horaInicioExJo.get();
    }
    public ObjectProperty<String> horaInicioExJoProperty() {
            return horaInicioExJo;
    }
    public void setHoraInicioExJo(String horaInicioExJo) {
            this.horaInicioExJo.set(horaInicioExJo);
    }
    
    public String getHoraFimExJo() {
            return horaFimExJo.get();
    }
    public ObjectProperty<String> horaFimExJoProperty() {
            return horaFimExJo;
    }
    public void setHoraFimExJo(String horaFimExJo) {
            this.horaFimExJo.set(horaFimExJo);
    }
    
    public int getIbopeExJo() {
            return ibopeExJo.get();
    }
    public IntegerProperty ibopeExJoProperty() {
            return ibopeExJo;
    }
    public void setIbopeExJo(int ibopeExJo) {
            this.ibopeExJo.set(ibopeExJo);		
    }
    
    
	public Funcionario getApresentador() {
		return apresentador;
	}
	public void setApresentador(Funcionario apresentador) {
		this.apresentador = apresentador;
	}
    
}
