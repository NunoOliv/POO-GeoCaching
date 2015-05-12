package Data;

import Exceptions.CacheNaoSuportaFuncionalidadeException;
import Exceptions.DificuldadeInvalidaException;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public abstract class Cache {

    private String ref;
    private Coords coords;
    private String creator;
    private HashSet<String> assinantes;
    private String descricao;
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
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    /**
     * @return the coords
     */
    public Coords getCoords() {
        return coords.clone();
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    /**
     * @return the assinantes
     */
    public HashSet<String> getAssinantes() {
        return assinantes;
    }

    public HashSet<String> listaAssinantes() {
        return (HashSet<String>) assinantes.clone();
    }

    /**
     * @param assinantes the assinantes to set
     */
    public void setAssinantes(HashSet<String> assinantes) {
        this.assinantes = assinantes;
    }

    public boolean addAssinante(String nome) {
        return this.assinantes.add(nome);
    }

    public boolean remAssinante(String nome) {
        return this.assinantes.remove(nome);
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the dificuldade
     */
    public int getDificuldade() {
        return dificuldade;
    }

    /**
     * @param dificuldade the dificuldade to set
     */
    public void setDificuldade(int dificuldade) throws DificuldadeInvalidaException {
        if (dificuldade > 5) {
            throw new DificuldadeInvalidaException();
        }
        this.dificuldade = dificuldade;
    }

    public int getPoints() {
        return dificuldade;
    }

    public abstract int getPontosExtra();

    public abstract void setPontosExtra(int i) throws CacheNaoSuportaFuncionalidadeException;

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
        if (!Objects.equals(this.coords, other.getCoords())) {
            return false;
        }
        if (!Objects.equals(this.assinantes, other.getAssinantes())) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.getDescricao())) {
            return false;
        }
        if (this.dificuldade != other.getDificuldade()) {
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

    public abstract String getCacheType();

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

}
