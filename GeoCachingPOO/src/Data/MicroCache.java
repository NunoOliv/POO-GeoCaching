/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.util.HashSet;

/**
 *
 * @author Nuno Oliveira
 */
public class MicroCache extends Cache implements Serializable {

    private static final int difExtra = 2;

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public MicroCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public MicroCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
    }

    /**
     *
     * @param m
     * @throws DificuldadeInvalidaException
     */
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

    @Override
    public int getCacheCode() {
        return 2;
    }

}
