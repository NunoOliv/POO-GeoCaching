/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.CacheNaoSuportaBugsException;
import Exceptions.CacheNaoSuportaTesourosException;
import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.util.ArrayList;
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
    
    public boolean addAssinantes(String assinante, String cache) {
        return this.getCache(cache).addAssinante(assinante);
    }
    
    public boolean remAssinantes(String assinante, String cache) {
        return this.getCache(cache).remAssinante(assinante);
    }
    
    public void setDificuldade(int dif, String cache) throws DificuldadeInvalidaException  {
        this.getCache(cache).setDificuldade(dif);
    }
    
    public HashSet<String> getListaAssinantes(String cache) {
        return this.getCache(cache).listaAssinantes();
    }
    
    public boolean addAssinante(String nome, String refCache) {
        return caches.get(refCache).addAssinante(nome);
    }
    
    public Coords getCoords(String cache) {
             return this.getCache(cache).getCoords();
    }
    
    public void setCoords(String cache, Coords coords) {
             this.getCache(cache).setCoords(coords);
    }
    
    public ArrayList<String> getListTesouros(String tradCache) throws CacheNaoSuportaTesourosException {
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).getListTesouros();
        }
        else throw new CacheNaoSuportaTesourosException();
    }
    
    public boolean addTesouro(String tesouro, String tradCache) throws CacheNaoSuportaTesourosException {
        
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).putTesouro(tesouro);
        } else throw new CacheNaoSuportaTesourosException();
    }
    
    public boolean takeTesouro(String tesouro, String tradCache) throws CacheNaoSuportaTesourosException {
        
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).takeTesouro(tesouro);
        } else throw new CacheNaoSuportaTesourosException();
    }
    
    
    public ArrayList<TravelBug> getListBugs(String tradCache) throws CacheNaoSuportaBugsException {
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).getListBugs();
        }
        
        else throw new CacheNaoSuportaBugsException();
        
    }
    
    public boolean addBug(TravelBug bug, String tradCache) throws CacheNaoSuportaBugsException {
        
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).putBug(bug);
        } else throw new CacheNaoSuportaBugsException();
    }
    
    /**
     * 
     * @param bug
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaBugsException 
     */
    public TravelBug takeBug(TravelBug bug, String tradCache) throws CacheNaoSuportaBugsException {
        
        if ( this.getCache(tradCache)instanceof TradCache) {
            return ((TradCache)this.getCache(tradCache)).takeBug(bug);
        } else throw new CacheNaoSuportaBugsException();
    }
    
    
    /************************************************************
     *                       Cache Tradicional                  *
     ************************************************************/
    
    /**
     * 
     * @param ref
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(String ref, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, coords, assinantes, descricao, dificuldade);
        if (!caches.containsKey(ref)) caches.put(ref, nCache); else return false;
        return true;
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
    public boolean addTradCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, coords, descricao, dificuldade);
        if (!caches.containsKey(ref)) caches.put(ref, nCache); else return false;
        return true;
    }
    
    /**
     * 
     * @param ref
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addTradCache(String ref, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException{
        TradCache nCache = new TradCache(ref, tesouros, bugs, coords, Assinantes, Descricao, dificuldade);
        if (!caches.containsKey(ref)) caches.put(ref, nCache); else return false;
        return true;
    }
    
    /**, 
     * 
     * @param t
     * @return 
     */
    public boolean addTradCache(TradCache t){
       if (!caches.containsKey(t.getRef())) caches.put(t.getRef(), t); else return false;
        return true;
    }
    
    /**
     * 
     * @param t
     * @return 
     */
    public boolean remTradCache(String t) {
        if (!caches.containsKey(t)) caches.remove(t); else return false;
        return true;
    }
    
    
    
    /************************************************************
     *                       Cache Evento                       *
     ************************************************************/
    
    
    /**
     * 
     * @param ref
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
    public boolean addCacheEvento(String ref, HashSet<User> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new CacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, assinantes, descricao, dificuldade)); else return false;
        return true;
    }
    
    /**
     * 
     * @param ref
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(String ref, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new CacheEvento(ref, coords, assinantes, descricao, dificuldade)); else return false;
        return true;
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
        if (!caches.containsKey(ref))caches.put(ref, new CacheEvento(ref, coords, descricao, dificuldade)); else return false;
        return true;
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c.getRef())) caches.put(c.getRef(), c); else return false;
        return true;
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean remCacheEvento(String c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c)) caches.remove(c); else return false;
        return true;
    }
    
    /************************************************************
     *                       Cache Mistério                     *
     ************************************************************/
    
    /**
     * 
     * @param ref
     * @param DescPuzzle
     * @param pontosExtra
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return 
     * @throws Exceptions.DificuldadeInvalidaException 
     */
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new CacheMisterio(ref, DescPuzzle,  pontosExtra,  coords,  descricao,  dificuldade)); else return false;
        return true;
    }
    
    /**
     * 
     * @param c
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addCacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c.getRef())) caches.put(c.getRef(), c); else return false;
        return true;
    }
    /**
     * 
     * @param ref
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
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new CacheMisterio(ref, DescPuzzle, pontosExtra, tesouros, bugs, coords, Assinantes, Descricao, dificuldade)); else return false;
        return true;
    }
    /**
     * 
     * @param c
     * @return 
     */
    public boolean remCacheMisterio (String c) {
        if (!caches.containsKey(c)) caches.remove(c); else return false;
        return true;
    }
    
    /************************************************************
     *                       Micro-Cache                        *
     ************************************************************/
    
    /**
     * 
     * @param ref
     * @param coords
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMicroCache(String ref, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new MicroCache(ref, coords, assinantes, descricao, dificuldade)); else return false;
        return true;
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
    public boolean addMicroCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new MicroCache(ref, coords, descricao, dificuldade)); else return false;
        return true;
    }
    
    /**
     * 
     * @param m
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMicroCache(MicroCache m) throws DificuldadeInvalidaException {
        if (!caches.containsKey(m.getRef())) caches.put(m.getRef(), m); else return false;
        return true;
    }
    
    /**
     * 
     * @param m
     * @return 
     */
    public boolean remMicroCache(String m) {
        if (!caches.containsKey(m)) caches.remove(m); else return false;
        return true;
    }
    
    
     /************************************************************
     *                       Multi Cache                         *
     ************************************************************/
    /**
     * 
     * @param ref
     * @param coords
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException 
     */
    public boolean addMultiCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) caches.put(ref, new MultiCache(ref, coords, descricao, dificuldade)); else return false;
        return true;
    }
    
    /**
     * 
     * @param ref
     * @param pontosExtra
     * @param pontosIntermedios
     * @param tesouros
     * @param bugs
     * @param coords
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     * @throws PontosExtraInvalidosException 
     */
    public boolean addMultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<TravelBug> bugs, Coords coords, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {
        if (!caches.containsKey(ref)) caches.put(ref, new MultiCache(ref, pontosExtra, pontosIntermedios,tesouros, bugs,  coords,  Assinantes,  Descricao,  dificuldade)); else return false;
        return true;
    }
    /**
     * 
     * @param c
     * @return 
     */
    public boolean addMultiCache(MultiCache c){
        if (!caches.containsKey(c.getRef())) caches.put(c.getRef(), c); else return false;
        return true;
    }
    
    /**
     * 
     * @param c
     * @return 
     */
    public boolean remMultiCache(String c) {
        if (!caches.containsKey(c)) caches.remove(c); else return false;
        return true;
    }
}
