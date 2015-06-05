package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public abstract class Cache implements Serializable {

    private final String ref;
    private final Coords coords;
    private final String creator;
    private final HashSet<String> assinantes;
    private String descricao;
    private int report;
    private int dificuldade;

    public Cache(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {

        if (dificuldade > 5 || dificuldade < 1) {
            throw new DificuldadeInvalidaException();
        }
        this.ref = ref;
        this.coords = coords;
        this.creator = creator;
        this.assinantes = assinantes;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.report = 0;
    }

    public Cache(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        if (dificuldade > 5 || dificuldade < 1) {
            throw new DificuldadeInvalidaException();
        }
        this.ref = ref;
        this.coords = coords;
        this.creator = creator;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.assinantes = new HashSet<>();
        this.report = 0;
    }

    /**
     * Devolve referencia da cache.
     *
     * @return referencia da cache
     */
    public String getRef() {
        return ref;
    }

    /**
     * Devolve uma cópia das coordenadas da cache
     *
     * @return Coordenadas da cache
     */
    public Coords getCoords() {
        return coords.clone();
    }

    /**
     * Devolve uma lista com todos os assinantes da cache.
     *
     * @return lista com todos os assinantes da cache
     */
    public HashSet<String> listaAssinantes() {
        return new HashSet<>(assinantes);
    }

    /**
     * Adiciona um assinante à cache.
     *
     * @param nome Identificador do utilizador a adicionar à cache.
     * @return
     */
    public boolean addAssinante(String nome) {
        return this.assinantes.add(nome);
    }

    /**
     * Remove um assinante da cache.
     *
     * @param nome Identificador do utilizador a remover da cache.
     *
     * @return Retorna true se removeu o assinante ou false se nao foi possivel
     * remover.
     */
    public boolean remAssinante(String nome) {
        return this.assinantes.remove(nome);
    }

    /**
     * Devolve a descrição da cache.
     *
     * @return Descricao da cahce.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Altera a descrição da cache.
     *
     * @param descricao Nova descricao da cahce
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Devolve a dificuldade da cache.
     *
     * @return dificuldade
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * Altera a dificuldade da cache. Este valor tem de estar compreendido entre
     * 1 e 5.
     *
     * @param dificuldade nova dificuldade da cache
     * @throws Exceptions.DificuldadeInvalidaException Exceção atirada se
     * dificuldade não estiver entre 1 e 5;
     */
    public void setDificuldade(int dificuldade) throws DificuldadeInvalidaException {
        if (dificuldade > 5) {
            throw new DificuldadeInvalidaException();
        }
        this.dificuldade = dificuldade;
    }

    /**
     * Devolve a pontuação base da cache (sem ter em conta as condições
     * climatericas).
     *
     * @return
     */
    public int getPoints() {
        return dificuldade;
    }

    /**
     * Devolve os pontos extra da classe.
     *
     * @return pontos extra
     */
    public abstract int getPontosExtra();

    /**
     * Altera os pontos extra da classe.
     *
     * @param i novo valor de pontuação extra.
     * @throws CacheNaoSuportaFuncionalidadeException atira exceção se a cache
     * nao suportar a alteração de dificuldade extra.
     * @throws Exceptions.DificuldadeInvalidaException
     */
    public abstract void setPontosExtra(int i) throws CacheNaoSuportaFuncionalidadeException, DificuldadeInvalidaException;

    /**
     * Devolve Código do tipo de cache.
     *
     * @return codigo da cache
     */
    public abstract int getCacheCode();

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.coords);
        hash = 23 * hash + Objects.hashCode(this.assinantes);
        hash = 23 * hash + Objects.hashCode(this.descricao);
        hash = 23 * hash + this.dificuldade;

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
        final Cache other = (Cache) obj;
        if (!this.coords.equals(other.getCoords())) {
            return false;
        }
        if (!this.creator.equals(other.getCreator())) {
            return false;
        }
        if (!Objects.equals(this.assinantes, other.assinantes)) {
            return false;
        }
        if (!this.descricao.equals(other.getDescricao())) {
            return false;
        }
        if (this.dificuldade != other.dificuldade) {
            return false;
        }
        return true;
    }

    @Override
    public abstract Cache clone();

    @Override
    public String toString() {
        String ret;
        ret = "Referencia: " + ref + "\n";
        ret = ret.concat("Coordenadas:\n   Latitude: " + coords.getLatitude() + "\n   Longitude: " + coords.getLongitude() + "\n");
        ret = ret.concat("Descrição: " + descricao + "\n");
        ret = ret.concat("Dificuldade: " + dificuldade + "\n");
        ret = ret.concat("Criador da Cache: " + creator + "\n");
        ret = ret.concat("Numero de Assinantes: " + assinantes.size() + "\n");

        return ret;
    }

    /**
     * Devolve string correspondente ao tipo de cache.
     *
     * @return
     */
    public abstract String getCacheType();

    /**
     * Devolve criador da cache;
     * 
     * @return 
     */
    public String getCreator() {
        return creator;
    }
    
    /**
     * Reporta cache como impropria
     * 
     * @return numero de reports da cache 
     */
    public int report() {
        return this.report +=1;
    }
    
    /**
     * Devolve numero de reports da cache
     * 
     * @return  numero de reports da cache
     */
    public int nReport() {
        return this.report;
    }

}
