package Data;

import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class MultiCache extends TradCache implements Serializable {

    private HashMap<Integer, Coords> pontosIntermedios;
    private int pontosExtra;

    /**
     * Construtor basico
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param pontosIntermedios
     * @param dificuldade
     * @param pontosExtra
     *
     * @throws Exceptions.DificuldadeInvalidaException
     */
    public MultiCache(String ref, Coords coords, String creator, String descricao, HashMap<Integer, Coords> pontosIntermedios, int dificuldade, int pontosExtra) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.setTesouros(new HashSet<>());
        this.setBugs(new HashSet<>());
        this.pontosIntermedios = pontosIntermedios;
        this.pontosExtra = pontosExtra;
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
     * @throws Exceptions.DificuldadeInvalidaException
     */
    public MultiCache(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
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
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @throws Exceptions.DificuldadeInvalidaException
     * @throws Exceptions.PontosExtraInvalidosException
     */
    public MultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {

        super(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        if (pontosExtra < 1 || pontosExtra > 5) {
            throw new PontosExtraInvalidosException();
        }
        this.pontosIntermedios = pontosIntermedios;
        this.pontosExtra = pontosExtra;
    }

    /**
     *
     * @param c
     * @throws DificuldadeInvalidaException
     */
    public MultiCache(MultiCache c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getTesouros(), c.getBugs(), c.getCoords(), c.getCreator(), c.listaAssinantes(), c.getDescricao(), c.getDificuldade());
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

        }
        return null;
    }

    @Override
    public String toString() {
        String ret = super.toString();

        ret = ret.concat("Pontos intermédios:\n");

        for (Integer i : pontosIntermedios.keySet()) {
            ret = ret.concat("Ponto " + i + 1 + ": Latitude = " + pontosIntermedios.get(i).getLatitude() + "  Longitude = " + pontosIntermedios.get(i).getLongitude() + "\n");
        }

        ret = ret.concat("Pontos Extra: " + pontosExtra + "\n");

        return ret;
    }

    @Override
    public String getCacheType() {
        return "Multi-Cache";
    }

    @Override
    public int getCacheCode() {
        return 4;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.pontosIntermedios);
        hash = 83 * hash + this.pontosExtra;
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
        final MultiCache other = (MultiCache) obj;
        if (!this.pontosIntermedios.equals(other.getPontosIntermedios())) {
            return false;
        }
        if (this.pontosExtra != other.getPontosExtra()) {
            return false;
        }
        return true;
    }
    
    

}
