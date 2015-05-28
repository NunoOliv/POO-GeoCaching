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
public class PedidoInvalidoException extends Exception {

    public PedidoInvalidoException() {
        super("Pedido de amizade invalido");
    }

    public PedidoInvalidoException(String message) {
        super(message);
    }

}
