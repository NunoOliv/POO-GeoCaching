/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class UserNaoExisteException extends Exception {

    @Override
    public String getMessage() {
        return "Email não corresponde a nenhum utilizador!";
    }

}
