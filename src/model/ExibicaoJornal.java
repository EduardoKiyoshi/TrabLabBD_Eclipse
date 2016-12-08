/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import util.DateUtil;
import util.TimeUtil;
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
public class ExibicaoJornal {
    private IntegerProperty idPr;
    private IntegerProperty idFu;
    private StringProperty dataExJo;
    private StringProperty  horaInicioExJo;
    private StringProperty  horaFimExJo;
    private IntegerProperty ibopeExJo;
    private Funcionario apresentador;
	
	public ExibicaoJornal(){
		this.idPr = new SimpleIntegerProperty();
		this.idFu = new SimpleIntegerProperty();
    	this.dataExJo = new SimpleStringProperty();
    	this.horaInicioExJo = new SimpleStringProperty();
    	this.horaFimExJo = new SimpleStringProperty();
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
    public StringProperty dataExJoProperty() {
            return dataExJo;
    }
    public void setDataExJo(LocalDate dataExJo) {
            this.dataExJo.set(DateUtil.format(dataExJo));
    }
    
    public String getHoraInicioExJo() {
            return horaInicioExJo.get();
    }
    public StringProperty horaInicioExJoProperty() {
            return horaInicioExJo;
    }
    public void setHoraInicioExJo(LocalTime horaInicioExJo) {
    		this.horaInicioExJo.set(TimeUtil.format(horaInicioExJo));
    }
    
    public String getHoraFimExJo() {
            return horaFimExJo.get();
    }
    public StringProperty horaFimExJoProperty() {
            return horaFimExJo;
    }
    public void setHoraFimExJo(LocalTime horaFimExJo) {
            this.horaFimExJo.set(TimeUtil.format(horaFimExJo));
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
