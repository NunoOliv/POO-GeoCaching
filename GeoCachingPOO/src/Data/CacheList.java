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
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache( coords, descricao, dificuldade);
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
    
    /**
     * 
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(coords, assinantes, descricao, dificuldade));
    }
    
    /**
     * 
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(coords, descricao, dificuldade));
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        return caches.add(c);
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean remCacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        return caches.remove(c);
    }
    
    /************************************************************
     *                       Cache Mistério                     *
     ************************************************************/
    
    /**
     * 
     * @param DescPuzzle
     * @param pontosExtra
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return 
     */
    public boolean addCacheMisterio(String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheMisterio( DescPuzzle,  pontosExtra,  coords,  descricao,  dificuldade));
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        return caches.add(c);
    }
    /**
     * 
     * @param DescPuzzle
     * @param pontosExtra
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheMisterio(String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheMisterio(DescPuzzle, pontosExtra, tesouros, bugs, coords, Assinantes, Descricao, dificuldade));
    }
    /**
     * 
     * @param c
     * @return 
     */
    public boolean remCacheMisterio (CacheMisterio c) {
        return caches.remove(c);
    }
    
    /************************************************************
     *                       Micro-Cache                        *
     ************************************************************/
    
    public boolean addMicroCache(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new MicroCache(coords, assinantes, descricao, dificuldade));
    }
}
