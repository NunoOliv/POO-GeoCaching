
package Data;

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
    
    public TradCache(Coords coords, HashMap assinantes, String descricao, int dificuldade) {
        super(coords, assinantes, descricao, dificuldade);
        tesouros= new HashSet<>();
    }

    public TradCache(HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int Dificuldade) {
        super(coords, Assinantes, Descricao, Dificuldade);
        this.tesouros = tesouros;
        this.bugs = bugs;
    }

    
    
    //Getters e Setters

    public HashSet getTesouros() {
        return tesouros;
    }

    public void setTesouros(HashSet tesouros) {
        this.tesouros = tesouros;
    }


    public HashSet<TravelBug> getBugs() {
        return bugs;
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
    public Boolean putTesouros(String tesouro) {
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
     */
    public void takeBug(TravelBug bug) {
        bugs.remove(bug);
        
    }

    /**
     * Adiciona um TravelBug
     * 
     * @param bug 
     */
    public void putBug(TravelBug bug) {
        bugs.add(bug);
        bug.addCache(this);
    }
    
    
}
