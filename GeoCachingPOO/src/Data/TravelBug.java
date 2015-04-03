/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TravelBug {
    private HashSet<CacheRegBug> caches;
    private String descrição;

    
    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public HashSet getCaches() {
        return caches;
    }

    public void setCaches(HashSet caches) {
        this.caches = caches;
    }
    
    //cosntrutores
    
    public TravelBug(HashSet caches, String descrição) {
        this.caches = caches;
        this.descrição = descrição;
    }

    public TravelBug(String descrição) {
        this.descrição = descrição;
        caches = new HashSet<CacheRegBug>();
    }
    
    
    /**
     * Adiciona registo de inserção de uma cache com data.
     * 
     * @param cache 
     */
    public void addCache(Cache cache) {
        Date date = new Date();
        date = Calendar.getInstance().getTime();
        caches.add(new CacheRegBug(cache, date));
    }
    
    
}
