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
public class Trabalho {
    private IntegerProperty idDe;
    private IntegerProperty idFu;
    private Departamento departamento;
    private Funcionario funcionario;
    private SimpleStringProperty dataInicioTr;
    private SimpleStringProperty dataFimTr;
    
    public Trabalho(){
    	this.idDe = new SimpleIntegerProperty();
    	this.idFu = new SimpleIntegerProperty();
    	departamento = new Departamento();
    	funcionario = new Funcionario();
    	this.dataInicioTr = new SimpleStringProperty();
    	this.dataFimTr = new SimpleStringProperty();
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
     
     public String getDataInicioTr() {
		return dataInicioTr.get();
	}
	public StringProperty dataInicioTrProperty() {
		return dataInicioTr;
	}
	public void setDataInicioTr(LocalDate dataInicioTr) {
		this.dataInicioTr.set(DateUtil.format(dataInicioTr));
	}
        
    public String getDataFimTr() {
		return dataFimTr.get();
	}
	public StringProperty dataFimTrProperty() {
		return dataFimTr;
	}
	public void setDataFimTr(LocalDate dataFimTr) {
		this.dataFimTr.set(DateUtil.format(dataFimTr));
	}
}
