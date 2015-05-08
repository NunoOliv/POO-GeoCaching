/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Oliveira
 */
public class CacheMisterio extends TradCache {

    private String descPuzzle;
    private int pontosExtra;

    public CacheMisterio(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.descPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        this.descPuzzle = new String();
        this.pontosExtra = 0;
    }

    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        this.descPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.descPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    public CacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getTesouros(), c.getBugs(), c.getCoords(), c.getCreator(), c.getAssinantes(), c.getDescricao(), c.getDificuldade());
        this.descPuzzle = c.getDescPuzzle();
        this.pontosExtra = c.getPontosExtra();
    }

    /**
     * @return the descPuzzle
     */
    public String getDescPuzzle() {
        return new String(descPuzzle);
    }

    /**
     * @param DescPuzzle the descPuzzle to set
     */
    public void setDescPuzzle(String DescPuzzle) {
        this.descPuzzle = DescPuzzle;
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
    public void setPontosExtra(int pontosExtra) {
        this.pontosExtra = pontosExtra;
    }

    /**
     * Devolve pontos da Cache Misterio
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
            return new CacheMisterio(this);
        } catch (DificuldadeInvalidaException ex) {
            Logger.getLogger(CacheMisterio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public String toString() {
        String ret = super.toString();

        ret = ret.concat("Descição do Puzzle: " + descPuzzle + "\n");
        ret = ret.concat("Pontos Extra: " + pontosExtra + "\n");

        return ret;
    }

    @Override
    public String getCacheType() {
        return "Cache-Mistério";
    }

}
