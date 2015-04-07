
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;



/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public abstract class Cache {

    private Coords coords;
    private HashMap<String, User> assinantes;
    private String descricao;
    private int dificuldade;
    
    

    public Cache(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        
        if(dificuldade>5 || dificuldade <1) throw new DificuldadeInvalidaException();
        
        this.coords = coords;
        this.assinantes = assinantes;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
    }

    public Cache(Coords coords, String descricao, int dificuldade) {
        this.coords = coords;
        this.descricao = descricao;
        this.dificuldade = dificuldade;
        this.assinantes = new HashMap<>();
    }
    
    

    /**
     * @return the coords
     */
    public Coords getCoords() {
        return coords;
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
    public HashMap<String, User> getAssinantes() {
        return assinantes;
    }

    /**
     * @param assinantes the assinantes to set
     */
    public void setAssinantes(HashMap<String, User> assinantes) {
        this.assinantes = assinantes;
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
    public void setDificuldade(int dificuldade) {
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
