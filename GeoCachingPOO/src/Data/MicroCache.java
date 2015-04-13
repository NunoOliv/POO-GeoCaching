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
    
    public MicroCache(String ref, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, assinantes, descricao, dificuldade);
    }

    public MicroCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException  {
        super(ref, coords, descricao, dificuldade);
    }
    
    public MicroCache(MicroCache m) throws DificuldadeInvalidaException {
        super(m.getRef(), m.getCoords(), m.getAssinantes(), m.getDescricao(), m.getDificuldade());
    }
    
    

    @Override
    public int getPoints() {
        return this.getDificuldade()+DifExtra;
    }
    
}
