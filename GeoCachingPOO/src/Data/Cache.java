/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author Nuno Oliveira
 */
public abstract class Cache {
    
    private Coords coords;
    private HashMap Assinantes;
    private String Descricao;
    private int dificuldade;

    public Cache(Coords coords, HashMap Assinantes, String Descricao, int Dificuldade) {
        this.coords = coords;
        this.Assinantes = Assinantes;
        this.Descricao = Descricao;
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
        return Assinantes;
    }

    public void setAssinantes(HashMap Assinantes) {
        this.Assinantes = Assinantes;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.coords);
        hash = 79 * hash + Objects.hashCode(this.Assinantes);
        hash = 79 * hash + Objects.hashCode(this.Descricao);
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
        if (!Objects.equals(this.Assinantes, other.Assinantes)) {
            return false;
        }
        if (!Objects.equals(this.Descricao, other.Descricao)) {
            return false;
        }
        if (this.dificuldade != other.dificuldade) {
            return false;
        }
        return true;
    }

    
    
    
}
