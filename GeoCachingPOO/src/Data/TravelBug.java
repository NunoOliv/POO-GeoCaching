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
    private String descricao;

    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HashSet getCaches() {
        return caches;
    }

    public void setCaches(HashSet caches) {
        this.caches = caches;
    }
    
    public HashSet<CacheRegBug> cloneCaches() {
       
       HashSet<CacheRegBug> ret = new HashSet<>();
       for(CacheRegBug t : caches) {
           ret.add(t);
       }
       return ret;
    
    }
    
    //cosntrutores
    
    public TravelBug(HashSet caches, String descrição) {
        this.caches = caches;
        this.descricao = descrição;
    }

    public TravelBug(String descrição) {
        this.descricao = descrição;
        caches = new HashSet<CacheRegBug>();
    }
    
    public TravelBug(TravelBug tb) {
        this.caches = tb.cloneCaches();
        this.descricao = tb.getDescricao();
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

    @Override
    protected Object clone() {
        return new TravelBug(this); //To change body of generated methods, choose Tools | Templates.
    }
    
}
