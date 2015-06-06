package Data;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;

/**
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

    /**
     *
     * @return
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     *
     * @param descricao
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     *
     * @return
     */
    public HashSet getCaches() {
        return new HashSet<>(caches);
    }

    /**
     *
     * @param caches
     */
    public void setCaches(HashSet caches) {

        this.caches = new HashSet<>(caches);
    }

    /**
     *
     * @return
     */
    public HashSet<CacheRegBug> cloneCaches() {

        HashSet<CacheRegBug> ret = new HashSet<>();
        for (CacheRegBug t : caches) {
            ret.add(t);
        }
        return ret;

    }

    //cosntrutores

    /**
     *
     * @param caches
     * @param descrição
     */
        public TravelBug(HashSet caches, String descrição) {
        this.caches = caches;
        this.descricao = descrição;
    }

    /**
     *
     * @param descrição
     */
    public TravelBug(String descrição) {
        this.descricao = descrição;
        caches = new HashSet<>();
    }

    /**
     *
     * @param tb
     */
    public TravelBug(TravelBug tb) {
        this.caches = tb.cloneCaches();
        this.descricao = tb.getDescricao();
        this.currentCache = tb.getCurrentCache();
    }

    @Override
    public String toString() {
        String ret = "Descrição: "+ descricao + "\n Cache actual:";
        if (currentCache == null) {
            ret += "TravelBug Livre\n";
        } else {
            ret += currentCache+"\n";
        }
        
        ret += "Caches: \n";
        for (CacheRegBug cgb : caches) {
            ret = ret +cgb.toString();
        }
        return ret;
        
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.descricao);
        hash = 29 * hash + Objects.hashCode(this.caches);
        hash = 29 * hash + Objects.hashCode(this.currentCache);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TravelBug other = (TravelBug) obj;
        if (!Objects.equals(this.descricao, other.getDescricao())) {
            return false;
        }
        if (!Objects.equals(this.caches, other.getCaches())) {
            return false;
        }
        if (!Objects.equals(this.currentCache, other.getCurrentCache())) {
            return false;
        }
        return true;
    }

    
}
