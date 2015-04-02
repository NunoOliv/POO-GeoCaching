
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
    
    private HashSet tesouros;
    

    public TradCache(Coords coords, HashMap assinantes, String descricao, int dificuldade) {
        super(coords, assinantes, descricao, dificuldade);
        tesouros= new HashSet<String>();
    }

    public TradCache(HashSet tesouros, Coords coords, HashMap assinantes, String descricao, int dificuldade) {
        super(coords, assinantes, descricao, dificuldade);
        this.tesouros = tesouros;
    }

    public HashSet getTesouros() {
        return tesouros;
    }

    public void setTesouros(HashSet tesouros) {
        this.tesouros = tesouros;
    }
    
    /**
     *
     * @param tesouro
     * @return
     */
    public Boolean takeTesouro(String tesouro) {
        
        if (tesouros.contains(tesouro)) {
            tesouros.remove(tesouro);
            return true;
        }
        return false;
    }

    public Boolean putTesouros(String tesouro) {
        if (!tesouros.contains(tesouro)) {
            this.tesouros.add(tesouro);
            return true;
        }
        return false;
    }
    
    
}
