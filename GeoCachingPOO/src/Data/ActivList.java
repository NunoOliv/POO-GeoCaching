/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.TipoDeCacheNaoExisteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author naso
 */
public class ActivList {
    
    private final HashMap<String, Atividade> atividades;

    public ActivList(HashMap<String, Atividade> atividades) {
        this.atividades = new HashMap<>(atividades);
    }

    public ActivList() {
        this.atividades = new HashMap<>();
    }
    
    public String addActividade(int cache, String ref, String user, int pontos) throws TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        Atividade act = new Atividade();
        
        act.addCache(cache, ref, user, pontos, date);
        
        return act.toString();
    }
    
    public ArrayList<String> getAtividades() {
        ArrayList<Atividade> aux = new ArrayList<>(atividades.values());
        ArrayList<String> ret = new ArrayList<>();
        Collections.sort(aux, );
        return ret;
    }
}
