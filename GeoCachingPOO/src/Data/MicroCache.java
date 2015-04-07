/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.HashMap;



/**
 *
 * @author Nuno Oliveira
 */
public class MicroCache extends Cache {

    private static final int DifExtra = 2;
    
    public MicroCache(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade, Clima clima) throws DificuldadeInvalidaException {
        super(coords, assinantes, descricao, dificuldade, clima);
    }

    @Override
    public int getPoints() {
        return this.getDificuldade()+this.getClima().getPontosExt()+DifExtra;
    }
    
}
