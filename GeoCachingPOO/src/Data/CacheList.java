/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno
 */
public class CacheList {
    private HashSet<Cache> caches;

    public CacheList(HashSet<Cache> caches) {
        this.caches = caches;
    }

    /**
     * @return the caches
     */
    public HashSet<Cache> getCaches() {
        return caches;
    }

    /**
     * @param caches the caches to set
     */
    public void setCaches(HashSet<Cache> caches) {
        this.caches = caches;
    }

    /**
     * 
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache( coords, assinantes, descricao, dificuldade);
        return caches.add(nCache);
    }
    
    /**
     * 
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        return caches.add(nCache);
    }
    
    /**
     * 
     * @param t
     * @return 
     */
    public boolean addTradCache(TradCache t){
        return caches.add(t);
    }
    
    /**
     * 
     * @param t
     * @return 
     */
    public boolean remTradCache(TradCache t) {
        return caches.remove(t);
    }
    
    /**
     * 
     * @param organizadores
     * @param dataEvento
     * @param pontosExtra
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(HashSet<User> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento( organizadores, dataEvento, pontosExtra, coords, assinantes, descricao, dificuldade));
    }
    
    public boolean addCacheEvento(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(coords, assinantes, descricao, dificuldade));
    }
}
