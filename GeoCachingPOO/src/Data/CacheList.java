package Data;

import Exceptions.CacheNaoExisteException;
import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import Exceptions.PontosExtraInvalidosException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
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
        this.bugs = new TravelBugL();
    }

    /**
     *
     */
    public CacheList() {
        this.caches = new HashMap<>();
        this.bugs = new TravelBugL();
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

    /**
     *
     * @return
     */
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

    /**
     *
     * @param cache
     * @param desc
     * @return
     */
    public boolean setDescricaoCache(String cache, String desc) {
        Cache tempC;
        if (!caches.containsKey(cache)) {
            return false;
        }
        tempC = caches.get(cache);
        tempC.setDescricao(desc);
        return true;
    }



    /*
     * *****************************************
     * Operações com detalhes de Cache 
     * ***************************************
     */

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    
    public String getDetalhesCache(String cache) throws CacheNaoExisteException {

        if (caches.containsKey(cache)) {
            return "Tipo de Cache: " + caches.get(cache).getCacheType() + "\n" + caches.get(cache).toString();
        } else {

            throw new CacheNaoExisteException();

        }
    }

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
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

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    
    public HashSet<String> getListaAssinantes(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).listaAssinantes();
    }

    /**
     *
     * @param assinante
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    public boolean remAssinantes(String assinante, String cache) throws CacheNaoExisteException {
        return this.getCache(cache).remAssinante(assinante);
    }

    /**
     *
     * @param cache
     * @param user
     * @return
     * @throws Exception
     */
    public boolean assinarCache(String cache, String user) throws Exception {
        if (this.containsCache(cache)) {

            return caches.get(cache).addAssinante(user);
        } else {
            return false;
        }
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

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    public int getDificuldade(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getDificuldade();
    }

    /*
     * *****************************************
     * Operações com coordenadas 
     ****************************************
     */

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
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
     * @throws Exceptions.CacheNaoExisteException
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
     * @throws Exceptions.CacheNaoExisteException
     */
    public boolean isCriador(String cache, String nome) throws CacheNaoExisteException {
        return (this.getCache(cache).getCreator()).equals(nome);
    }

    /*
     * *****************************************
     * Operações com tesouros 
     ****************************************
     */

    /**
     *
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    
    public ArrayList<String> getListTesouros(String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).getListTesouros();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    /**
     *
     * @param tesouro
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public boolean addTesouro(String tesouro, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).putTesouro(tesouro);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    /**
     *
     * @param tesouro
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public boolean takeTesouro(String tesouro, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).takeTesouro(tesouro);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Tesouros");
        }
    }

    /**
     *
     * @param cache
     * @return
     */
    public boolean suportaTesouros(String cache) {
        return (caches.get(cache) instanceof TradCache);
    }
    /*
     * *****************************************
     * Operações com TravelBugs 
     * ****************************************
     */

    /**
     *
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public ArrayList<String> getListBugs(String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(tradCache) instanceof TradCache) {
            return ((TradCache) this.getCache(tradCache)).getListBugs();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("TravelBugs");
        }

    }

    /**
     *
     * @param bug
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public boolean addBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        TradCache c, e;
        System.out.println(bug);
        if (this.getCache(tradCache) instanceof TradCache) {
            c = (TradCache) this.caches.get(tradCache);
            if (this.bugs.containsBug(bug)) {
                if (!c.containsBug(bug)) {
                    try {
                        e = (TradCache) this.caches.get(bugs.getCurrCache(bug));
                        e.takeBug(bug);
                    } catch (Exception ex) { }                      
                    this.bugs.addCacheRec(bug, tradCache);
                    return (c.putBug(bug));
                } else {
                    return false;
                }
            } else {
                if (!c.containsBug(bug)) {
                    try {
                        e = (TradCache) this.caches.get(bugs.getCurrCache(bug));
                        e.takeBug(bug);
                    } catch (Exception ex) { }
                    this.bugs.newTB(bug);
                    this.bugs.addCacheRec(bug, tradCache);

                    return (c.putBug(bug));
                } else {
                    return false;
                }
            }
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("TravelBugs");
        }
    }

    /**
     *
     * @param bug
     * @param tradCache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public boolean takeBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        TradCache c;
        if (this.getCache(tradCache) instanceof TradCache) {
            c = (TradCache) this.caches.get(tradCache);
            if (this.bugs.containsBug(bug)) {
                if (c.containsBug(bug)) {
                    this.bugs.takeFromCache(bug, tradCache);
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

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    
    public HashMap<Integer, Coords> getPontosIntermedios(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof MultiCache) {
            return ((MultiCache) this.getCache(cache)).getPontosIntermedios();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Pontos Intermedios");
        }
    }

    /**
     *
     * @param cache
     * @param coords
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
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

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    
    public int getPontosExtra(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getPontosExtra();
    }

    /**
     *
     * @param p
     * @param cache
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     * @throws DificuldadeInvalidaException
     */
    public void setPontosExtra(int p, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException, DificuldadeInvalidaException {
        this.getCache(cache).setPontosExtra(p);
    }

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    public int getPontos(String cache) throws CacheNaoExisteException {
        return this.getCache(cache).getPoints();
    }

    /*
     * *****************************************
     * Operações com organizadores 
     * ***************************************
     */

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    
    public ArrayList<String> getListaOrg(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {

        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).getListaOrg();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    /**
     *
     * @param user
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public boolean addOrganizador(String user, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).addOrganizador(user);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    /**
     *
     * @param user
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
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

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    
    public GregorianCalendar getDataEvento(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            return ((CacheEvento) this.getCache(cache)).getDataEvento();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    /**
     *
     * @param data
     * @param cache
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    public void putDataEvento(GregorianCalendar data, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheEvento) {
            ((CacheEvento) this.getCache(cache)).setDataEvento(data);
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Eventos");
        }
    }

    /**
     *
     * @param cache
     * @return
     */
    public boolean suportaEventos(String cache) {
        return (caches.get(cache) instanceof CacheEvento);
    }

    /*
     * *****************************************
     * Operações com descrição de puzzles
     * ******************************************
     */

    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
    
    public String getPuzzle(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        if (this.getCache(cache) instanceof CacheMisterio) {
            return ((CacheMisterio) this.getCache(cache)).getDescPuzzle();
        } else {
            throw new CacheNaoSuportaFuncionalidadeException("Puzzles");
        }
    }

    /**
     *
     * @param cache
     * @param puzzle
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws CacheNaoExisteException
     */
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

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
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

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addTradCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        TradCache nCache = new TradCache(ref, coords, creator, descricao, dificuldade);
        if (!caches.containsKey(ref)) {
            caches.put(ref, nCache);
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param ref
     * @param tesouros
     * @param bugs
     * @param coords
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addTradCache(String ref, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException {
        TradCache nCache = new TradCache(ref, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade);
        if (!caches.containsKey(ref)) {
            caches.put(ref, nCache);
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param t
     * @return
     */
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

    /**
     *
     * @param ref
     * @param organizadores
     * @param dataEvento
     * @param pontosExtra
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    
    public boolean addCacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addCacheEvento(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, coords, creator, assinantes, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addCacheEvento(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheEvento(ref, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param c
     * @return
     * @throws DificuldadeInvalidaException
     */
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

    /**
     *
     * @param ref
     * @param DescPuzzle
     * @param pontosExtra
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new CacheMisterio(ref, DescPuzzle, pontosExtra, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param c
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addCacheMisterio(CacheMisterio c) throws DificuldadeInvalidaException {
        if (!caches.containsKey(c.getRef())) {
            caches.put(c.getRef(), c);
        } else {
            return false;
        }
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
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
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

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    
    public boolean addMicroCache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MicroCache(ref, coords, creator, assinantes, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     */
    public boolean addMicroCache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MicroCache(ref, coords, creator, descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param m
     * @return
     * @throws DificuldadeInvalidaException
     */
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

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param pontosIntermedios
     * @param dificuldade
     * @param pontosExtra
     * @return
     * @throws DificuldadeInvalidaException
     */
    
    public boolean MultiCache(String ref, Coords coords, String creator, String descricao, HashMap<Integer, Coords> pontosIntermedios, int dificuldade, int pontosExtra) throws DificuldadeInvalidaException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MultiCache(ref, coords, creator, descricao, pontosIntermedios, dificuldade, pontosExtra));
        } else {
            return false;
        }
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
     * @param creator
     * @param Assinantes
     * @param Descricao
     * @param dificuldade
     * @return
     * @throws DificuldadeInvalidaException
     * @throws PontosExtraInvalidosException
     */
    public boolean addMultiCache(String ref, int pontosExtra, HashMap<Integer, Coords> pontosIntermedios, HashSet<String> tesouros, HashSet<String> bugs, Coords coords, String creator, HashSet<String> Assinantes, String Descricao, int dificuldade) throws DificuldadeInvalidaException, PontosExtraInvalidosException {
        if (!caches.containsKey(ref)) {
            caches.put(ref, new MultiCache(ref, pontosExtra, pontosIntermedios, tesouros, bugs, coords, creator, Assinantes, Descricao, dificuldade));
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param c
     * @return
     */
    public boolean addMultiCache(MultiCache c) {
        if (!caches.containsKey(c.getRef())) {
            caches.put(c.getRef(), c);
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @param t
     * @return
     */
    public boolean remCache(String t) {
        if (caches.containsKey(t)) {
            caches.remove(t);
        } else {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getFreeBugs() {
        return bugs.getFreeBugs();
    }

    /**
     *
     * @param bug
     * @return
     */
    public String getBugDetails(String bug) {
        return bugs.getBugDetails(bug);
    }

    /**
     *
     * @param bug
     * @return
     */
    public boolean containsBug(String bug) {
        return bugs.containsBug(bug);
    }
    
    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    public int report(String cache) throws CacheNaoExisteException {
        return (this.getCache(cache)).report();
    }
    
    /**
     *
     * @param cache
     * @return
     * @throws CacheNaoExisteException
     */
    public int nReports(String cache) throws CacheNaoExisteException {
        return (this.getCache(cache)).nReport();
    }

    /**
     *
     * @return
     */
    public Map<GregorianCalendar, String> getEventos() {
       
        Map ret = new TreeMap(Collections.reverseOrder());
        for(Cache c : caches.values()) {
           if (c instanceof CacheEvento) {
               ret.put(((CacheEvento) c).getDataEvento(), c.getRef());
           }
       }
        
       return ret;
    }

}
