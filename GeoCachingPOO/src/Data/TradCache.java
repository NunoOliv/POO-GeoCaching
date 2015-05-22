package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TradCache extends Cache {

    private HashSet<String> tesouros;
    private HashSet<String> bugs;

// Construtores 
    public TradCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }

    public TradCache(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.tesouros = tesouros;
        this.bugs = bugs;
    }

    public TradCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }

    public TradCache(TradCache t) throws DificuldadeInvalidaException {
        super(t.getRef(), t.getCoords(), t.getCreator(), t.listaAssinantes(), t.getDescricao(), t.getDificuldade());
        this.tesouros = t.getTesouros();
        this.bugs = t.getBugs();

    }

    //Getters e Setters
    public HashSet getTesouros() {
        return new HashSet<>(tesouros);
    }

    public void setTesouros(HashSet tesouros) {
        this.tesouros = tesouros;
    }

    public ArrayList<String> getListTesouros() {
        ArrayList<String> ret = new ArrayList<>();
        for (String t : tesouros) {
            ret.add(t);
        }
        return ret;
    }

    public HashSet<String> getBugs() {
        return new HashSet<> (bugs);
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

        if (bugs.contains(bug)) {
            bugs.add(bug);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean containsBug(String bug) {
        return bugs.contains(bug);
    }

    @Override
    public int getPoints() {
        return getDificuldade();
    }

    @Override
    public int getPontosExtra() {
        return 0;
    }

    @Override
    public void setPontosExtra(int i) throws CacheNaoSuportaFuncionalidadeException {
        throw new CacheNaoSuportaFuncionalidadeException("Alterar Pontos de Dificuldade"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cache clone() {
        try {
            return new TradCache(this);
        } catch (DificuldadeInvalidaException ex) {
            Logger.getLogger(TradCache.class.getName()).log(Level.SEVERE, null, ex);
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

}
