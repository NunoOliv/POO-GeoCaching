/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.CacheNaoExisteException;
import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno
 */
public class CacheList implements Serializable {

    private HashMap<String, Cache> caches;
    private TravelBugL bugs;

    /**
     *
     * @param caches
     */
    public CacheList(HashMap<String, Cache> caches) {
        this.caches = caches;
    }

    public CacheList() {
        this.caches = new HashMap<>();
    }

    /**
     * @return the caches
     */
    public HashMap<String, Cache> getCaches() {
        return new HashMap<>(caches);
    }

    /**
     * @param caches the caches to set
     */
    public void setCaches(HashMap<String, Cache> caches) {
        this.caches = new HashMap<>(caches);
    }

    private Cache getCache(String ref) throws CacheNaoExisteException {
        if (caches.containsKey(ref)) {
            return caches.get(ref);
        } else {
            throw new CacheNaoExisteException();
        }

    }

    public ArrayList<String> getListaCacheNames() {
        ArrayList<String> ret = new ArrayList<>();
        for (String s : caches.keySet()) {
            ret.add(s);
        }

        return ret;
    }

    /**
     * Verifica se existe uma cache com uma dada referencia
     *
     * @param ref Referencia da cache
     * @return
     */
    public boolean containsCache(String ref) {
        return caches.containsKey(ref);
    }

    public boolean setDescricaoCache(String cache, String desc) {
        Cache tempC;
        if (!caches.containsKey(cache)) {
            return false;
        }
        tempC = caches.get(cache);
        tempC.setDescricao(desc);
        return true;
    }

    public boolean assinarCache(String cache, String user) {
        if (this.containsCache(cache)) {
            return caches.get(cache).addAssinante(user);
        } else {
            return false;
        }
    }

    /*
     * *****************************************
     * Operações com detalhes de Cache 
     * ***************************************
     */
    public String getDetalhesCache(String cache) throws CacheNaoExisteException {

        if (caches.containsKey(cache)) {
            return "Tipo de Cache: " + caches.get(cache).getCacheType() + "\n" + caches.get(cache).toString();
        } else {

            throw new CacheNaoExisteException();

        }
    }

    public int getCacheType(String cache) throws CacheNaoExisteException {
        if (caches.containsKey(cache)) {
            return caches.get(cache).getPontosExtra();
        } else {
            throw new CacheNaoExisteException();

        }
    }

    /*
     * *****************************************
     * Operações com assinantes 
     * *****************************************S
     */
    public HashSet<String> getListaAssinantes(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).listaAssinantes();
    }

    public boolean addAssinantes(String assinante, String cache) throws CacheNaoExisteException {
        return this.getCache(cache).addAssinante(assinante);
    }

    public boolean remAssinantes(String assinante, String cache) throws CacheNaoExisteException {
        return this.getCache(cache).remAssinante(assinante);
    }

    public boolean addAssinante(String nome, String refCache) {
        return caches.get(refCache).addAssinante(nome);
    }

    /**
     * *****************************************
     * Operações com dificuldades *****************************************
     */
    /**
     *
     * @param dif
     * @param cache
     * @throws DificuldadeInvalidaException
     */
    public void setDificuldade(int dif, String cache) throws DificuldadeInvalidaException {
        this.caches.get(cache).setDificuldade(dif);
    }

    public int getDificuldade(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getDificuldade();
    }

    /*
     * *****************************************
     * Operações com coordenadas 
     ****************************************
     */
    public Coords getCoords(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getCoords();
    }


    /*
     * *****************************************
     * Operações com criadores
     * ******************************************
     */
    /**
     * Retorna a String correspondente ao criador da cache.
     *
     * @param cache Identificador da cache.
     * @return Identificador do criador da cache.
     */
    public String getCriador(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getCreator();
    }

    /**
     * Verifica se um utilizador é o criador de uma cache.
     *
     * @param cache Identificador da cache.
     * @param nome Identificador do utilizador.
     * @return Verdade se for o criador, false caso contrário.
     */
    public boolean isCriador(String cache, String nome) throws CacheNaoExisteException {
        return (this.getCache(cache).getCreator()).equals(nome);
    }

    /*
     * *****************************************
     * Operações com tesouros 
     ****************************************
     */
    public ArrayList<String> getListTesouros(String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).getListTesouros();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    public boolean addTesouro(String tesouro, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).putTesouro(tesouro);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    public boolean takeTesouro(String tesouro, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).takeTesouro(tesouro);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    public boolean suportaTesouros(String cache) {
        return (caches.get(cache) instanceof TradCache);
    }
    /*
     * *****************************************
     * Operações com TravelBugs 
     * ****************************************
     */

    public ArrayList<String> getListBugs(String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).getListBugs();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("TravelBugs");
        }

    }

    public boolean addBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        TradCache c;
        if (this.getCache(tradCache) instanceof TradCache) {
            c = (TradCache) this.caches.get(tradCache);
            if (bugs.containsBug(bug)) {
                if (!c.containsBug(bug)) {
                    bugs.addCacheRec(bug, tradCache);
                    return (c.putBug(bug));
                } else {
                    return false;
                }
            } else {
                if (!c.containsBug(bug)) {
                    bugs.newTB(bug);
                    bugs.addCacheRec(bug, tradCache);
                    return (c.putBug(bug));
                } else {
                    return false;
                }
            }
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("TravelBugs");
        }
    }

    public boolean takeBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        TradCache c;
        if (this.getCache(tradCache) instanceof TradCache) {
            c = (TradCache) this.caches.get(tradCache);
            if (bugs.containsBug(bug)) {
                if (c.containsBug(bug)) {
                    bugs.takeFromCache(bug, tradCache);
                    return (c.takeBug(bug));
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("TravelBugs");
        }

    }

    /*
     * ****************************************
     * Operações com pontos intermedios 
     * ***************************************
     */
    public HashMap<Integer, Coords> getPontosIntermedios(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof MultiCache) {
            return ((MultiCache) this.getCache(cache)).getPontosIntermedios();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Pontos Intermedios");
        }
    }

    public void setPontosIntermedios(String cache, HashMap<Integer, Coords> coords) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof MultiCache) {
            ((MultiCache) this.getCache(cache)).setPontosIntermedios(coords);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Pontos Intermedios");
        }
    }

    /*
     * *****************************************
     * Operações com pontos Extra 
     * ***************************************
     */
    public int getPontosExtra(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getPontosExtra();
    }

    public void setPontosExtra(int p, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException, DificuldadeInvalidaException {
        this.getCache(cache).setPontosExtra(p);
    }

    public int getPontos(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getPoints();
    }

    /*
     * *****************************************
     * Operações com organizadores 
     * ***************************************
     */
    public ArrayList<String> getListaOrg(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).getListaOrg();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    public boolean addOrganizador(String user, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).addOrganizador(user);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    public boolean remOrganizador(String user, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).removeOrganizador(user);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    /*
     * *****************************************
     * Operações com data eventos 
     * ***************************************
     */
    public GregorianCalendar getDataEvento(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).getDataEvento();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    public void putDataEvento(GregorianCalendar data, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            ((CacheEvento) this.getCache(cache)).setDataEvento(data);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    public boolean suportaEventos(String cache) {
        return (caches.get(cache) instanceof CacheEvento);
    }

    /*
     * *****************************************
     * Operações com descrição de puzzles
     * ******************************************
     */
    public String getPuzzle(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheMisterio) {
            return ((CacheMisterio) this.getCache(cache)).getDescPuzzle();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Puzzles");
        }
    }

    public void setPuzzle(String cache, String puzzle) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheMisterio) {
            ((CacheMisterio) this.getCache(cache)).setDescPuzzle(puzzle);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Puzzles");
        }
    }

    /*
     * **********************************************************
     * Cache Tradicional 
     * **********************************************************
     */
    public boolean addTradCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        TradCache nCache = new TradCache(ref, coords, creator, assinantes, descricao, dificuldade);
        if (!caches.containsKey(ref)) {
            caches.put(ref, nCache);
        } else {
            return false;
        }
        return true;
    }

    public boolean addTradCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        TradCache nCache = new TradCache(ref, coords, creator, descricao, dificuldade);
        if (!caches.containsKey(ref)) {
            caches.put(ref, nCache);
        } else {
            return false;
        }
        return true;
    }

    public boolean addTradCache(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        TradCache nCache = new TradCache(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        if (!caches.containsKey(ref)) {
            caches.put(ref, nCache);
        } else {
            return false;
        }
        return true;
    }

    public boolean addTradCache(TradCache t) {
        if (!caches.containsKey(t.getRef())) {
            caches.put(t.getRef(), t);
        } else {
            return false;
        }
        return true;
    }

    /*
     * **********************************************************
     * Cache Evento 
     * **********************************************************
     */
    public boolean addCacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addCacheEvento(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, coords, creator, assinantes, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addCacheEvento(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addCacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c.getRef())) {
            caches.put(c.getRef(), c);
        } else {
            return false;
        }
        return true;
    }

    /*
     * **********************************************************
     * Cache Mistério 
     * **********************************************************
     */
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheMisterio(ref, DescPuzzle, pontosExtra, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addCacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c.getRef())) {
            caches.put(c.getRef(), c);
        } else {
            return false;
        }
        return true;
    }

    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheMisterio(ref, DescPuzzle, pontosExtra, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }


    /*
     * **********************************************************
     * Micro-Cache 
     * **********************************************************
     */
    public boolean addMicroCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MicroCache(ref, coords, creator, assinantes, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addMicroCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MicroCache(ref, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addMicroCache(MicroCache m) throws DificuldadeInvalidaException {
        if (!caches.containsKey(m.getRef())) {
            caches.put(m.getRef(), m);
        } else {
            return false;
        }
        return true;
    }


    /*
     * **********************************************************
     * Multi Cache 
     * **********************************************************
     */
    public boolean MultiCache(String ref, Coords coords, String creator, String descricao, HashMap<Integer, Coords> pontosIntermedios, int dificuldade, int pontosExtra) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MultiCache(ref, coords, creator, descricao, pontosIntermedios, dificuldade, pontosExtra));
        } else {
            return false;
        }
        return true;
    }

    public boolean addMultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MultiCache(ref, pontosExtra, pontosIntermedios, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    public boolean addMultiCache(MultiCache c) {
        if (!caches.containsKey(c.getRef())) {
            caches.put(c.getRef(), c);
        } else {
            return false;
        }
        return true;
    }

    public boolean remCache(String t) {
        if (caches.containsKey(t)) {
            caches.remove(t);
        } else {
            return false;
        }
        return true;
    }
}
