package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TradCache extends Cache implements Serializable {

    private HashSet<String> tesouros;
    private HashSet<String> bugs;

// Construtores 

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
        public TradCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }

    /**
     *
     * @param ref
     * @param tesouros
     * @param bugs
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public TradCache(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.tesouros = tesouros;
        this.bugs = bugs;
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
    public TradCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }

    /**
     *
     * @param t
     * @throws DificuldadeInvalidaException
     */
    public TradCache(TradCache t) throws DificuldadeInvalidaException {
        super(t.getRef(), t.getCoords(), t.getCreator(), t.listaAssinantes(), t.getDescricao(), t.getDificuldade());
        this.tesouros = t.getTesouros();
        this.bugs = t.getBugs();

    }

    //Getters e Setters

    /**
     *
     * @return
     */
        public HashSet getTesouros() {
        return new HashSet<>(tesouros);
    }

    /**
     *
     * @param tesouros
     */
    public void setTesouros(HashSet tesouros) {
        this.tesouros = tesouros;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getListTesouros() {
        ArrayList<String> ret = new ArrayList<>();
        for (String t : tesouros) {
            ret.add(t);
        }
        return ret;
    }

    /**
     *
     * @return
     */
    public HashSet<String> getBugs() {
        return new HashSet<>(bugs);
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getListBugs() {
        ArrayList<String> ret = new ArrayList<>();
        for (String t : bugs) {
            ret.add(t);
        }
        return ret;
    }

    /**
     *
     * @param bugs
     */
    public void setBugs(HashSet<String> bugs) {
        this.bugs = new HashSet<>(bugs);
    }

    /**
     * Remover um tesouro
     *
     * @param tesouro
     * @return true se retirou ou false se nao retirou
     */
    public Boolean takeTesouro(String tesouro) {

        if (tesouros.contains(tesouro)) {
            tesouros.remove(tesouro);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um tesouro
     *
     * @param tesouro
     * @return true se adicionu ou false se nao adicionou
     */
    public Boolean putTesouro(String tesouro) {
        if (!tesouros.contains(tesouro)) {
            this.tesouros.add(tesouro);
            return true;
        }
        return false;
    }

    /**
     * Remove um TravelBug
     *
     * @param bug
     * @return
     */
    public boolean takeBug(String bug) {
        if (bugs.contains(bug)) {
            bugs.remove(bug);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adiciona um TravelBug
     *
     * @param bug
     * @return
     */
    public boolean putBug(String bug) {

        if (!bugs.contains(bug)) {
            bugs.add(bug);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param bug
     * @return
     */
    public boolean containsBug(String bug) {
        if(bugs.contains(bug)== true) return true; else return false;
    }

    @Override
    public int getPoints() {
        return getDificuldade();
    }

    /**
     * DevolvePontos extra da cache.
     *
     * @return pontos extra.
     */
    @Override
    public int getPontosExtra() {
        return 0;
    }

    @Override
    public void setPontosExtra(int i) throws CacheNaoSuportaFuncionalidadeException, DificuldadeInvalidaException {
        throw new CacheNaoSuportaFuncionalidadeException("Alterar Pontos de Dificuldade"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cache clone() {
        try {
            return new TradCache(this);
        } catch (DificuldadeInvalidaException ex) {

        }
        return null;
    }

    @Override
    public String toString() {
        String ret = super.toString();

        ret = ret.concat("Numero de Tesouros: " + tesouros.size() + "\n");
        ret = ret.concat("Numero de TravelBugs: " + bugs.size() + "\n");

        return ret;
    }

    @Override
    public String getCacheType() {
        return "Cache Tradicional";
    }

    @Override
    public int getCacheCode() {
        return 1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.tesouros);
        hash = 53 * hash + Objects.hashCode(this.bugs);
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
        final TradCache other = (TradCache) obj;
        if (!Objects.equals(this.tesouros, other.getTesouros())) {
            return false;
        }
        if (!Objects.equals(this.bugs, other.getBugs())) {
            return false;
        }
        return true;
    }

    
}
