
package Data;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Cache {

    private Coords coords;
    private HashMap<String, User> assinantes;
    private String descricao;
    private int dificuldade;

    public Cache(Coords coords, HashMap Assinantes, String Descricao, int Dificuldade) {
        this.coords = coords;
        this.assinantes = Assinantes;
        this.descricao = Descricao;
        this.dificuldade = Dificuldade;
    }

    public int getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(int dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public HashMap getAssinantes() {
        return assinantes;
    }

    public void setAssinantes(HashMap Assinantes) {
        this.assinantes = Assinantes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String Descricao) {
        this.descricao = Descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.coords);
        hash = 79 * hash + Objects.hashCode(this.assinantes);
        hash = 79 * hash + Objects.hashCode(this.descricao);
        hash = 79 * hash + this.dificuldade;
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
