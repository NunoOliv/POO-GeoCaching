/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// NÃO ESTA EM UTILIZAÇÃO
package Data;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Nuno Oliveira
 */
public class Tesouro implements Serializable{
    private String descrição;

    //getters e setters
    public String getDescrição() {
        return descrição;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }
    
    //Construtores

    public Tesouro(String descrição) {
        this.descrição = descrição;
    }
    
    //Equals

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.descrição);
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
        final Tesouro other = (Tesouro) obj;
        if (!Objects.equals(this.descrição, other.descrição)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tesouro{" + "descri\u00e7\u00e3o=" + descrição + '}';
    }
    
    
}
