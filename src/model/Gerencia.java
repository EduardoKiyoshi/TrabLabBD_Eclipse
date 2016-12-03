/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

import util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author 9778536
 */
public class Gerencia {
    private IntegerProperty idDe;
    private IntegerProperty idFu;
    private Departamento departamento;
    private Funcionario funcionario;
    private ObjectProperty<LocalDate> dataInicioGe;
    private ObjectProperty<LocalDate> dataFimGe;
    
    public Gerencia(){
    	this.idDe = new SimpleIntegerProperty();
    	this.idFu = new SimpleIntegerProperty();
    	departamento = new Departamento();
    	funcionario = new Funcionario();
    	this.dataInicioGe = new SimpleObjectProperty<LocalDate>();
    	this.dataFimGe = new SimpleObjectProperty<LocalDate>();
    }
    
    public Funcionario getFuncionario() {
    	return funcionario;
    }
    public void setFuncionario(Funcionario funcionario) {
    	this.funcionario = funcionario;		
    }

    public Departamento getDepartamento() {
    	return departamento;
    }
    public void setDepartamento(Departamento departamento) {
    	this.departamento = departamento;		
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
        
    public int getIdDe() {
             return idDe.get();
     }
     public IntegerProperty idDeProperty() {
             return idDe;
     }
     public void setIdDe(int idDe) {
             this.idDe.set(idDe);		
     }
     
     public String getDataInicioGe() {
		return DateUtil.format(dataInicioGe.get());
	}
	public ObjectProperty<LocalDate> dataInicioGeProperty() {
		return dataInicioGe;
	}
	public void setDataInicioGe(LocalDate dataInicioGe) {
		this.dataInicioGe.set(dataInicioGe);
	}
        
        public String getDataFimGe() {
		return DateUtil.format(dataFimGe.get());
	}
	public ObjectProperty<LocalDate> dataFimGeProperty() {
		return dataFimGe;
	}
	public void setDataFimGe(LocalDate dataFimGe) {
		this.dataFimGe.set(dataFimGe);
	}
}
