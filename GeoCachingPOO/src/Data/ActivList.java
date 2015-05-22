/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 *
 * @author naso
 */
public class ActivList {
    
    private HashMap<String, Atividade> actividades;

    public ActivList(HashMap<String, Atividade> actividades) {
        this.actividades = actividades;
    }

    public ActivList() {
        this.actividades = new HashMap<>();
    }
    
    public String addActividade(int cache, String ref, String user, int pontos) {
        GregorianCalendar date = new GregorianCalendar();
        Atividade act = new Atividade();
        
        act.addCache(int cache, String ref, String user, int pontos, GregorianCalendar date);
        
        
    }
    
}
