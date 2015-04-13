/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno Oliveira
 */
public class CacheMisterio extends TradCache {
    
    private String DescPuzzle;
    private int pontosExtra;

    public CacheMisterio(String ref, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, assinantes, descricao, dificuldade);
        this.DescPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        this.DescPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        this.DescPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, descricao, dificuldade);
        this.DescPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    public CacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        super(c.getRef(),c.getTesouros(), c.getBugs(), c.getCoords(), c.getAssinantes(), c.getDescricao(), c.getDificuldade());
        this.DescPuzzle = c.getDescPuzzle();
        this.pontosExtra = c.getPontosExtra();
    }

    /**
     * @return the DescPuzzle
     */
    public String getDescPuzzle() {
        return DescPuzzle;
    }

    /**
     * @param DescPuzzle the DescPuzzle to set
     */
    public void setDescPuzzle(String DescPuzzle) {
        this.DescPuzzle = DescPuzzle;
    }

    /**
     * @return the pontosExtra
     */
    public int getPontosExtra() {
        return pontosExtra;
    }

    /**
     * @param pontosExtra the pontosExtra to set
     */
    public void setPontosExtra(int pontosExtra) {
        this.pontosExtra = pontosExtra;
    }

    /**
     * Devolve pontos da Cache Misterio
     * @return  
     */
    @Override
    public int getPoints() {
        return super.getPoints()+pontosExtra;
    }
    
    
    
    
}
