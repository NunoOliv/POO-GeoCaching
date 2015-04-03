/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class CacheRegBug {
    private Cache cache;
    private Date date;
    
    //getters e setters

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    //construtores

    public CacheRegBug(Cache cache, Date date) {
        this.cache = cache;
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final CacheRegBug other = (CacheRegBug) obj;
        if (!Objects.equals(this.cache, other.cache)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }
    
    
    
}
