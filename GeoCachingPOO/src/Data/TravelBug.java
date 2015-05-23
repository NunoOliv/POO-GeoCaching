/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class TravelBug implements Serializable {
    private String descricao;
    private HashSet<CacheRegBug> caches;
    private String currentCache; 

    /**
     * 
     * @return 
     */
    public String getCurrentCache() {
        return currentCache;
    }
    
    /**
     * 
     * @param currentCache 
     */
    public void setCurrentCache(String currentCache) {
        this.currentCache = currentCache;
    }
      
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public HashSet getCaches() {
        return new HashSet<> (caches);
    }

    public void setCaches(HashSet caches) {
        
        this.caches = new HashSet<> (caches);
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
        caches = new HashSet<>();
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
    public void addCache(String cache) {
        GregorianCalendar date = new GregorianCalendar();
        caches.add(new CacheRegBug(cache, date));
    }

    @Override
    protected Object clone() {
        return new TravelBug(this); //To change body of generated methods, choose Tools | Templates.
    }
    
}
