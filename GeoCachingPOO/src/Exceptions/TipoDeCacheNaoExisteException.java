/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Nuno
 */
public class TipoDeCacheNaoExisteException extends Exception {
    @Override
    public String getMessage() {
        return "Número de Tipo de Cache Inválido!";
    }
}
 
 
