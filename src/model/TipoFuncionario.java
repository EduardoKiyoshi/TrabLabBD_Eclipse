package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TipoFuncionario {
	private IntegerProperty idTipoFu;
	private IntegerProperty salarioBaseTipoFu;
	private StringProperty descricaoTipoFu;
	
	public TipoFuncionario(){		
		this.idTipoFu = new SimpleIntegerProperty();
		this.salarioBaseTipoFu = new SimpleIntegerProperty();
		this.descricaoTipoFu = new SimpleStringProperty();
	}
	
	public int getIdTipoFu() {
		return idTipoFu.get();
	}
	public IntegerProperty idTipoFuProperty() {
		return idTipoFu;
	}
	public void setIdTipoFu(int idTipoFu) {
		this.idTipoFu.set(idTipoFu);		
	}
	
	public int getSalarioBaseTipoFu() {
		return salarioBaseTipoFu.get();
	}
	public IntegerProperty salarioBaseTipoFuProperty() {
		return salarioBaseTipoFu;
	}
	public void setSalarioBaseTipoFu(int salarioBaseTipoFu) {
		this.salarioBaseTipoFu.set(salarioBaseTipoFu);		
	}
	
	public String getDescricaoTipoFu() {
		return descricaoTipoFu.get();
	}
	public StringProperty descricaoTipoFuProperty() {
		return descricaoTipoFu;
	}
	public void setDescricaoTipoFu(String descricaoTipoFu) {		
		this.descricaoTipoFu.set(descricaoTipoFu);
	}
}
