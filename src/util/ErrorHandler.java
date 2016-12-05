/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author lenovo
 */
public class ErrorHandler {
    public static String getMessage(int code){
        switch(code){
            case 1: 
                return "Já existe um item cadastrado com esses valores";
                //alert.setContentText("Dependente j� cadastrado para esse funcion�rio!");
            case 1858:    
            	return "Data invalida";
            case 20002:
                //Usando to_date1
                return "Data inválida";
                //alert.setContentText("Data de Nascimento em formato inv�lido");
            case 2290:
                //The values being inserted do not satisfy the named check
                return "Sexo inv�lido M - Masculino, F - Feminino";
                //alert.setContentText("Sexo inv�lido M - Masculino, F - Feminino");  
            case 1400:
                return "Campo obrigatorio vazio!";
                //alert.setContentText("Campo obrigat�rio Vazio!");
            case 2291:
                return "parent key not found";
        }
        return null;
    }
}
