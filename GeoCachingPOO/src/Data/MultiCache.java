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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Oliveira
 */
public class MultiCache extends TradCache {

    private HashMap<Integer, Coords> pontosIntermedios;
    private int pontosExtra;

    /**
     * Construtor basico
     *
     * @param ref
     * @param coords
     * @param descricao
     * @param dificuldade
     * @throws Exceptions.DificuldadeInvalidaException
     */
    public MultiCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.setTesouros(new HashSet<>());
        this.setBugs(new HashSet<>());
        this.pontosIntermedios = new HashMap<>();
        this.pontosExtra = 1;
    }

    /**
     *
     * @param ref
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @throws Exceptions.DificuldadeInvalidaException
     */
    public MultiCache(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        this.pontosIntermedios = new HashMap<>();
        this.pontosExtra = 1;
    }

    /**
     *
     * @param ref
     * @param pontosExtra
     * @param pontosIntermedios
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @throws Exceptions.DificuldadeInvalidaException
     * @throws Exceptions.PontosExtraInvalidosException
     */
    public MultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {

        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        if (pontosExtra < 1 || pontosExtra > 5) {
            throw new PontosExtraInvalidosException();
        }
        this.pontosIntermedios = pontosIntermedios;
        this.pontosExtra = pontosExtra;
    }

    public MultiCache(MultiCache c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getTesouros(), c.getBugs(), c.getCoords(), c.getCreator(), c.getAssinantes(), c.getDescricao(), c.getDificuldade());
        this.pontosIntermedios = c.getPontosIntermedios();
        this.pontosExtra = c.getPontosExtra();
    }

    /**
     * @return the pontosIntermedios
     */
    public HashMap<Integer, Coords> getPontosIntermedios() {
        int i = 0;
        HashMap<Integer, Coords> ret = new HashMap<>();
        while (pontosIntermedios.get(i) != null) {
            ret.put(i, pontosIntermedios.get(i));
        }
        return ret;
    }

    /**
     * @param pontosIntermedios the pontosIntermedios to set
     */
    public void setPontosIntermedios(HashMap<Integer, Coords> pontosIntermedios) {
        this.pontosIntermedios = pontosIntermedios;
    }

    /**
     * @return the pontosExtra
     */
    @Override
    public int getPontosExtra() {
        return pontosExtra;
    }

    /**
     * @param pontosExtra the pontosExtra to set
     */
    @Override
    public void setPontosExtra(int pontosExtra) {
        this.pontosExtra = pontosExtra;
    }

    /**
     * Devolve a pontuação do multi-cache
     *
     * @return
     */
    @Override
    public int getPoints() {
        return super.getPoints() + pontosExtra;
    }

    @Override
    public Cache clone() {
        try {
            return new MultiCache(this);
        } catch (DificuldadeInvalidaException ex) {
            Logger.getLogger(MultiCache.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        String ret = super.toString();

        ret = ret.concat("Pontos intermédios:\n");

        for (Integer i : pontosIntermedios.keySet()) {
            ret = ret.concat("Ponto " + i + ": Latitude = " + pontosIntermedios.get(i).getLatitude() + "  Longitude = " + pontosIntermedios.get(i).getLongitude() + "\n");
        }

        ret = ret.concat("Pontos Extra: " + pontosExtra + "\n");

        return ret;
    }

    @Override
    public String getCacheType() {
        return "Multi-Cache";
    }

}
