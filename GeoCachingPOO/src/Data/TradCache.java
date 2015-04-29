
package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TradCache extends Cache {
    
    private HashSet<String> tesouros;
    private HashSet<TravelBug> bugs;
    
// Construtores 

    public TradCache(String ref, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, assinantes, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }
    
   

    public TradCache(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, Assinantes, Descricao, dificuldade);
        this.tesouros = tesouros;
        this.bugs = bugs;
    }

    public TradCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, descricao, dificuldade);
        tesouros = new HashSet<>();
        bugs = new HashSet<>();
    }

    public TradCache(TradCache t) throws DificuldadeInvalidaException {
        super(t.getRef(), t.getCoords(),t.getAssinantes(),t.getDescricao(),t.getDificuldade());
        this.tesouros = t.getTesouros();
        this.bugs = t.getBugs();
        this.setAssinantes(t.getAssinantes());
        this.setCoords(t.getCoords());
        this.setDescricao(t.getDescricao());
        this.setDificuldade(t.getDificuldade());
    }
  
    //Getters e Setters

    public HashSet getTesouros() {
        return tesouros;
    }

    public void setTesouros(HashSet tesouros) {
        this.tesouros = tesouros;
    }

    public ArrayList<String> getListTesouros() {
        ArrayList<String> ret = new ArrayList<>();
       for(String t : tesouros) {
           ret.add(t);
       }
       return ret;
    }
    
    public HashSet<TravelBug> getBugs() {
        return bugs;
    }
    
    public ArrayList<TravelBug> getListBugs() {
        ArrayList<TravelBug> ret = new ArrayList<>();
       for(TravelBug t : bugs) {
           ret.add((TravelBug)t.clone());
       }
       return ret;
    }

    public void setBugs(HashSet<TravelBug> bugs) {
        this.bugs = bugs;
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
    public boolean takeBug(TravelBug bug) {
        return bugs.remove(bug);
    }

    /**
     * Adiciona um TravelBug
     * 
     * @param bug 
     * @return  
     */
    public boolean putBug(TravelBug bug) {
        
        if (bugs.add(bug)){
            bug.addCache(this);
            return true;
        } else return false;
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
    
    
}
