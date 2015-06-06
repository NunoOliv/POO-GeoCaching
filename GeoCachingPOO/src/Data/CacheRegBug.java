/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class CacheRegBug implements Serializable {

    private String cache;
    private GregorianCalendar date;

    //getters e setters

    /**
     *
     * @return
     */
        public String getCache() {
        return cache;
    }

    /**
     *
     * @param cache
     */
    public void setCache(String cache) {
        this.cache = cache;
    }

    /**
     *
     * @return
     */
    public GregorianCalendar getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    //construtores

    /**
     *
     * @param cache
     * @param date
     */
        public CacheRegBug(String cache, GregorianCalendar date) {
        this.cache = cache;
        this.date = date;
    }

    @Override
    public String toString() {
        DateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        return "Cache: "+cache+"     Data: "+ formatter.format(date.getTime())+"\n";
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
