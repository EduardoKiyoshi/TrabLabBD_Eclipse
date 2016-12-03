package model;

import java.time.LocalDate;

import util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Funcionario {
	private IntegerProperty idFu;
	private StringProperty nomeCompletoFu;
	private ObjectProperty<LocalDate> dataNascimentoFu;
	private StringProperty cpfFu;
	private StringProperty salarioFu;
	private IntegerProperty idTipoFu;
	private StringProperty descricaoTipoFu;
	private ObservableList<Dependente> dependentes;
	
	public Funcionario(){		
		this(-1,"","","","",-1,"");
	}
	public Funcionario(int idFu, String nomeCompletoFu, String dataNascimentoFu,
			String cpfFu, String salarioFu, int idTipoFu, String descricaoTipoFu) {
		super();
		this.idFu = new SimpleIntegerProperty(idFu);
		this.nomeCompletoFu = new SimpleStringProperty(nomeCompletoFu);
		this.dataNascimentoFu = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dataNascimentoFu));
		this.cpfFu = new SimpleStringProperty(cpfFu);
		this.salarioFu = new SimpleStringProperty(salarioFu);
		this.idTipoFu = new SimpleIntegerProperty(idTipoFu);
		this.descricaoTipoFu = new SimpleStringProperty(descricaoTipoFu);
		
		this.dependentes = FXCollections.observableArrayList();
	}
	
	public ObservableList getDependentes() {
            return dependentes;
	}
	public void setDependentes(ObservableList dependentes) {
	        this.dependentes = dependentes;
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
	
	public String getNomeCompletoFu() {
		return nomeCompletoFu.get();
	}
	public StringProperty nomeCompletoFuProperty() {
		return nomeCompletoFu;
	}
	public void setNomeCompletoFu(String nomeCompletoFu) {		
		this.nomeCompletoFu.set(nomeCompletoFu);
	}
	
	//TODO : Verificar se data de nascimento est� correta
	public String getDataNascimentoFu() {
		return DateUtil.format(dataNascimentoFu.get());
	}
	public ObjectProperty<LocalDate> dataNascimentoFuProperty() {
		return dataNascimentoFu;
	}
	public void setDataNascimentoFu(LocalDate dataNascimentoFu) {
		this.dataNascimentoFu.set(dataNascimentoFu);
	}
	
	public String getCpfFu() {
		return cpfFu.get();
	}	
	public StringProperty cpfFuProperty() {
		return cpfFu;
	}
	public void setCpfFu(String cpfFu) {
		this.cpfFu.set(cpfFu);
	}
	
	public String getSalarioFu() {
		return salarioFu.get();
	}	
	public StringProperty salarioFuProperty() {
		return salarioFu;
	}
	public void setSalarioFu(String salarioFu) {
		this.salarioFu.set(salarioFu);
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
