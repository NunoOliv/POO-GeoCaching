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

    public CacheMisterio(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade, Clima clima) throws DificuldadeInvalidaException {
        super(coords, assinantes, descricao, dificuldade, clima);
        this.DescPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade, Clima clima) throws DificuldadeInvalidaException {
        super(tesouros, bugs, coords, Assinantes, Descricao, dificuldade, clima);
        this.DescPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade, Clima clima) throws DificuldadeInvalidaException {
        super(tesouros, bugs, coords, Assinantes, Descricao, dificuldade, clima);
        this.DescPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
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
