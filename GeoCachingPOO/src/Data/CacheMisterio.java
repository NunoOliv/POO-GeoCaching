package Data;

import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class CacheMisterio extends TradCache implements Serializable {

    private String descPuzzle;
    private int pontosExtra;

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
    public CacheMisterio(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.descPuzzle = new String();
        this.pontosExtra = 0;
    }

    /**
     *
     * @param ref
     * @param tesouros
     * @param bugs
     * @param coords
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public CacheMisterio(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        this.descPuzzle = new String();
        this.pontosExtra = 0;
    }

    /**
     *
     * @param ref
     * @param DescPuzzle
     * @param pontosExtra
     * @param tesouros
     * @param bugs
     * @param coords
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        this.descPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    /**
     *
     * @param ref
     * @param DescPuzzle
     * @param pontosExtra
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public CacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.descPuzzle = DescPuzzle;
        this.pontosExtra = pontosExtra;
    }

    /**
     *
     * @param c
     * @throws DificuldadeInvalidaException
     */
    public CacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getTesouros(), c.getBugs(), c.getCoords(), c.getCreator(), c.listaAssinantes(), c.getDescricao(), c.getDificuldade());
        this.descPuzzle = c.getDescPuzzle();
        this.pontosExtra = c.getPontosExtra();
    }

    /**
     * @return the descPuzzle
     */
    public String getDescPuzzle() {
        return descPuzzle;
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
            return null;
        }
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

    @Override
    public int getCacheCode() {
        return 3; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.descPuzzle);
        hash = 89 * hash + this.pontosExtra;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CacheMisterio other = (CacheMisterio) obj;
        if (!Objects.equals(this.descPuzzle, other.getDescPuzzle())) {
            return false;
        }
        if (this.pontosExtra != other.getPontosExtra()) {
            return false;
        }
        return true;
    }

    
}
