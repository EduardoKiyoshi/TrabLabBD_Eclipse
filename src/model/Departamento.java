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
public class Departamento {
    private IntegerProperty idDe;
    private StringProperty nomeDe;
    private StringProperty telefoneDe;
    private IntegerProperty nroFuncionariosDe;
    private StringProperty cnpjEm;
    
    public Departamento(){
    	this.idDe = new SimpleIntegerProperty();
    	this.nomeDe = new SimpleStringProperty();
    	this.telefoneDe = new SimpleStringProperty();
    	this.nroFuncionariosDe = new SimpleIntegerProperty();
    	this.cnpjEm = new SimpleStringProperty();
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
    
    public String getNomeDe() {
	return nomeDe.get();
    }
    public StringProperty nomeDeProperty() {
        return nomeDe;
    }
    public void setNomeDe(String nomeDe) {		
        this.nomeDe.set(nomeDe);
    }
    
    public String getTelefoneDe() {
        return telefoneDe.get();
    }
    public StringProperty telefoneDeProperty() {
        return telefoneDe;
    }
    public void setTelefoneDe(String telefoneDe) {		
        this.telefoneDe.set(telefoneDe);
    }
    
    public int getNroFuncionariosDe() {
            return nroFuncionariosDe.get();
    }
    public IntegerProperty nroFuncionariosDeProperty() {
            return nroFuncionariosDe;
    }
    public void setNroFuncionariosDe(int nroFuncionariosDe) {
            this.nroFuncionariosDe.set(nroFuncionariosDe);		
    }
    
    public String getCnpjEm() {
            return cnpjEm.get();
    }
    public StringProperty cnpjEmProperty() {
            return cnpjEm;
    }
    public void setCnpjEm(String cnpjEm) {		
            this.cnpjEm.set(cnpjEm);
    }
}
