package model;

import java.time.LocalDate;

import util.DateUtil;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dependente {
	private IntegerProperty idFu;
	private StringProperty nomeCompletoDe;
	private StringProperty dataNascimentoDe;
	private StringProperty sexoDe;
	
	public Dependente(){		
            this(-1,"","","");
	}
	public Dependente(int idFu, String nomeCompletoDe, String dataNascimentoDe,
			String sexoDe) {
		super();
		this.idFu = new SimpleIntegerProperty(idFu);
		this.nomeCompletoDe = new SimpleStringProperty(nomeCompletoDe);
		//this.dataNascimentoDe = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dataNascimentoDe));
                this.dataNascimentoDe = new SimpleStringProperty();
		this.sexoDe = new SimpleStringProperty(sexoDe);
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
	
	public String getNomeCompletoDe() {
		return nomeCompletoDe.get();
	}
	public StringProperty nomeCompletoDeProperty() {
		return nomeCompletoDe;
	}
	public void setNomeCompletoDe(String nomeCompletoDe) {		
		this.nomeCompletoDe.set(nomeCompletoDe);
	}
	
	//TODO : Verificar se data de nascimento est� correta
	public String getDataNascimentoDe() {
		return dataNascimentoDe.get();
	}
	public StringProperty dataNascimentoDeProperty() {
		return dataNascimentoDe;
	}
	public void setDataNascimentoDe(LocalDate dataNascimentoDe) {
		this.dataNascimentoDe.set(DateUtil.format(dataNascimentoDe));
	}
	
	public String getSexoDe() {
		return sexoDe.get();
	}	
	public StringProperty sexoDeProperty() {
		return sexoDe;
	}
	public void setSexoDe(String sexoDe) {
		this.sexoDe.set(sexoDe);
	}
	
}
