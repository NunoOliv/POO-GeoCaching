/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno Oliveira
 */
public class TradCache extends Cache {
    
    private HashSet tesouros;
    

    public TradCache(Coords coords, HashMap Assinantes, String Descricao, int Dificuldade) {
        super(coords, Assinantes, Descricao, Dificuldade);
        tesouros= new HashSet<String>();
    }

    public TradCache(HashSet tesouros, Coords coords, HashMap Assinantes, String Descricao, int Dificuldade) {
        super(coords, Assinantes, Descricao, Dificuldade);
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
