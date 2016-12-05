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
    private ObjectProperty<LocalDate> dataInicioTr;
    private ObjectProperty<LocalDate> dataFimTr;
    
    public Trabalho(){
    	this.idDe = new SimpleIntegerProperty();
    	this.idFu = new SimpleIntegerProperty();
    	departamento = new Departamento();
    	funcionario = new Funcionario();
    	this.dataInicioTr = new SimpleObjectProperty<LocalDate>();
    	this.dataFimTr = new SimpleObjectProperty<LocalDate>();
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
		return DateUtil.format(dataInicioTr.get());
	}
	public ObjectProperty<LocalDate> dataInicioTrProperty() {
		return dataInicioTr;
	}
	public void setDataInicioTr(LocalDate dataInicioTr) {
		this.dataInicioTr.set(dataInicioTr);
	}
        
    public String getDataFimTr() {
		return DateUtil.format(dataFimTr.get());
	}
	public ObjectProperty<LocalDate> dataFimTrProperty() {
		return dataFimTr;
	}
	public void setDataFimTr(LocalDate dataFimTr) {
		this.dataFimTr.set(dataFimTr);
	}
}
