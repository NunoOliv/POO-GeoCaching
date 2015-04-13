/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno
 */
public class CacheList {
    private HashMap<String, Cache> caches;

    /**
     *
     * @param caches
     */
    public CacheList(HashMap<String, Cache> caches) {
        this.caches = caches;
    }

    /**
     * @return the caches
     */
    public HashMap<String, Cache> getCaches() {
        return caches;
    }

    /**
     * @param caches the caches to set
     */
    public void setCaches(HashMap<String, Cache> caches) {
        this.caches = caches;
    }
    
    public Cache getCache(String Ref) {
        return caches.get(Ref);
    }

    /************************************************************
     *                       Cache Tradicional                  *
     ************************************************************/
    
    /**
     * 
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(String ref, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, coords, assinantes, descricao, dificuldade);
        if (!caches.containsKey(ref)) caches.put(ref, nCache); else return false;
        return true;
    }
    
    /**
     * 
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, coords, descricao, dificuldade);
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
    public boolean addTradCache(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        return caches.add(nCache);
    }
    
    /**, 
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
    
    
    
    /************************************************************
     *                       Cache Evento                       *
     ************************************************************/
    
    
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
    public boolean addCacheEvento(String ref, HashSet<User> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, assinantes, descricao, dificuldade));
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
    public boolean addCacheEvento(String ref, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(ref, coords, assinantes, descricao, dificuldade));
    }
    
    /**
     * 
     * @param ref
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheEvento(ref, coords, descricao, dificuldade));
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
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheMisterio(ref, DescPuzzle,  pontosExtra,  coords,  descricao,  dificuldade));
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
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new CacheMisterio(ref, DescPuzzle, pontosExtra, tesouros, bugs, coords, Assinantes, Descricao, dificuldade));
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
    
    /**
     * 
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMicroCache(String ref, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new MicroCache(ref, coords, assinantes, descricao, dificuldade));
    }
    
    /**
     * 
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMicroCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new MicroCache(ref, coords, descricao, dificuldade));
    }
    
    /**
     * 
     * @param m
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMicroCache(MicroCache m) throws DificuldadeInvalidaException {
        return caches.add(m);
    }
    
    /**
     * 
     * @param m
     * @return 
     */
    public boolean remMicroCache(MicroCache m) {
        return caches.remove(m);
    }
    
    
     /************************************************************
     *                       Multi Cache                         *
     ************************************************************/
    
    public boolean addMultiCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return caches.add(new MultiCache(ref, coords, descricao, dificuldade));
    }
    
    public boolean addMultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashMap Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {
        return caches.add(new MultiCache(ref, pontosExtra, pontosIntermedios,tesouros, bugs,  coords,  Assinantes,  Descricao,  dificuldade));
    }
    
    public boolean addMultiCache(MultiCache c){
        return caches.add(c);
    }
    
    public boolean remMultiCache(MultiCache c) {
        return caches.remove(c);
    }
}
