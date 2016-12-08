/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

/**
 *
 * @author lenovo
 */
public class ExibicaoComercial {
    private IntegerProperty idPr;
    private StringProperty dataExCo;
    private StringProperty  horaInicioExCo;
    private StringProperty  horaFimExCo;
    private IntegerProperty precoExCo;
    private StringProperty cnpjCl;
    
    public ExibicaoComercial(){
    	this.idPr = new SimpleIntegerProperty();
    	this.dataExCo = new SimpleStringProperty();
    	this.horaInicioExCo = new SimpleStringProperty();
    	this.horaFimExCo = new SimpleStringProperty();
    	this.precoExCo = new SimpleIntegerProperty();
    	this.cnpjCl = new SimpleStringProperty();
        
    	
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
    
    public String getDataExCo() {
            return dataExCo.get();
    }
    public StringProperty dataExCoProperty() {
            return dataExCo;
    }
    public void setDataExCo(LocalDate dataExCo) {
    		this.dataExCo.set(DateUtil.format(dataExCo));
    }
    
    public String getHoraInicioExCo() {
            return horaInicioExCo.toString();
    }
    public StringProperty horaInicioExCoProperty() {
            return horaInicioExCo;
    }
    public void setHoraInicioExCo(LocalTime horaInicioExCo) {
            this.horaInicioExCo.set(TimeUtil.format(horaInicioExCo));
    }
    
    public String getHoraFimExCo() {
            return horaFimExCo.toString();
    }
    public StringProperty horaFimExCoProperty() {
            return horaFimExCo;
    }
    public void setHoraFimExCo(LocalTime horaFimExCo) {
            this.horaFimExCo.set(TimeUtil.format(horaFimExCo));
    }
    
    public int getPrecoExCo() {
            return precoExCo.get();
    }
    public IntegerProperty precoExCoProperty() {
            return precoExCo;
    }
    public void setPrecoExCo(int precoExCo) {
            this.precoExCo.set(precoExCo);		
    }
    
    public String getCnpjCl() {
		return cnpjCl.get();
	}
	public StringProperty cnpjClProperty() {
		return cnpjCl;
	}
	public void setCnpjCl(String cnpjCl) {		
		this.cnpjCl.set(cnpjCl);
	}
}
