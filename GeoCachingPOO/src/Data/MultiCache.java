/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Nuno Oliveira
 */
public class MultiCache extends TradCache {
    private HashMap<Integer,Coords> pontosIntermedios;
    private int pontosExtra;
    
    /**
     * Construtor basico
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @param clima 
     */
    public MultiCache(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(coords, assinantes, descricao, dificuldade);
        this.setTesouros(new HashSet<String>());
        this.setBugs(new HashSet<TravelBug>());
        this.pontosIntermedios= new HashMap<>();
        this.pontosExtra=1;
    }
    /**
     * 
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @param clima 
     */
    public MultiCache(HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        this.pontosIntermedios= new HashMap<>();
        this.pontosExtra = 1;
    }

    /**
     * 
     * @param pontosIntermedios
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @param clima 
     */
    public MultiCache(int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {
        
        super(tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        if (pontosExtra<1 || pontosExtra>5) {throw new PontosExtraInvalidosException();}
        this.pontosIntermedios = pontosIntermedios;
        this.pontosExtra = pontosExtra;
    }

    /**
     * @return the pontosIntermedios
     */
    public HashMap<Integer,Coords> getPontosIntermedios() {
        return pontosIntermedios;
    }

    /**
     * @param pontosIntermedios the pontosIntermedios to set
     */
    public void setPontosIntermedios(HashMap<Integer,Coords> pontosIntermedios) {
        this.pontosIntermedios = pontosIntermedios;
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
     * Devolve a pontuação do multi-cache
     * @return 
     */
    @Override
    public int getPoints() {
        return super.getPoints()+ pontosExtra;
    }

    
    

    
    
}
