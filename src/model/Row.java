/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author lenovo
 */
public class Row{
            private StringProperty nomeFunc;
            private StringProperty salario;
            private StringProperty cargo;
            private StringProperty nomeDep;
            private StringProperty idadeDep;
            public Row(String nomeFunc, String salario, String cargo, String nomeDep, String idadeDep){
                this.nomeFunc = new SimpleStringProperty(nomeFunc);
                this.salario = new SimpleStringProperty(salario);
                this.cargo = new SimpleStringProperty(cargo);
                this.nomeDep = new SimpleStringProperty(nomeDep);
                this.idadeDep = new SimpleStringProperty(idadeDep);
            }
            public String getNomeFunc (){
            	return nomeFunc.get();
            }
            public StringProperty nomeFuncProperty() {
            	return nomeFunc;
            }
            public void setNomeFunc (String nomeFunc){
            	this.nomeFunc.set(nomeFunc); 
            }
            public StringProperty salarioProperty() {
		return salario;
            }
            public StringProperty cargoProperty() {
		return cargo;
            }
            public StringProperty nomeDepProperty() {
		return nomeDep;
            }
            public StringProperty idadeDepProperty() {
		return idadeDep;
            }
    }
