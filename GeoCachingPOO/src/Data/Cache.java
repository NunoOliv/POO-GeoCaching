
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.Date;
import java.util.HashMap;
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
    private HashSet<String> assinantes;
    private String descricao;
    private int dificuldade;
    
    

    public Cache(String ref, Coords coords, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        
        if(dificuldade>5 || dificuldade <1) throw new DificuldadeInvalidaException();
        this.ref = ref;
        this.coords = coords;
        this.assinantes = assinantes;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
    }

    public Cache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
         if(dificuldade>5 || dificuldade <1) throw new DificuldadeInvalidaException();
        this.ref = ref;
        this.coords = coords;
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
        return (HashSet<String>)assinantes.clone();
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
        if (dificuldade > 5) throw new DificuldadeInvalidaException();
        this.dificuldade = dificuldade;
    }

   
    
    public int getPoints() {
        return dificuldade;
    }

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
        if (!Objects.equals(this.coords, other.coords)) {
            return false;
        }
        if (!Objects.equals(this.assinantes, other.assinantes)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (this.dificuldade != other.dificuldade) {
            return false;
        }
        return true;
    }

    

 
}
