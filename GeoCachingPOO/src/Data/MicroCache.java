/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Oliveira
 */
public class MicroCache extends Cache {

    private static final int difExtra = 2;

    public MicroCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords,creator, assinantes, descricao, dificuldade);
    }

    public MicroCache(String ref, Coords coords,String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
    }

    public MicroCache(MicroCache m) throws DificuldadeInvalidaException {
        super(m.getRef(), m.getCoords(), m.getCreator(), m.listaAssinantes(), m.getDescricao(), m.getDificuldade());
    }

    @Override
    public int getPoints() {
        return this.getDificuldade() + difExtra;
    }

    @Override
    public int getPontosExtra() {
        return difExtra;
    }

    @Override
    public void setPontosExtra(int i) throws CacheNaoSuportaFuncionalidadeException {
        throw new CacheNaoSuportaFuncionalidadeException("Alterar Pontos de Dificuldade");
    }

    @Override
    public Cache clone() {
        try {
            return new MicroCache(this);
        } catch (DificuldadeInvalidaException ex) {
            Logger.getLogger(MicroCache.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public String toString() {
        String ret = super.toString();
        
        ret = ret.concat("Pontos Extra: " + difExtra + "\n");
        
        return ret;
    }

    @Override
    public String getCacheType() {
        return "Micro-Cache";
    }
    
    

}
