/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.util.Objects;

/**
 *
 * @author Nuno Oliveira
 */
public class Clima {
    private String desc;
    private int pontosExt;

    public Clima(String desc, int pontosExt) {
        this.desc = desc;
        this.pontosExt = pontosExt;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the pontosExt
     */
    public int getPontosExt() {
        return pontosExt;
    }

    /**
     * @param pontosExt the pontosExt to set
     */
    public void setPontosExt(int pontosExt) {
        this.pontosExt = pontosExt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.desc);
        hash = 37 * hash + this.pontosExt;
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
        final Clima other = (Clima) obj;
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        if (this.pontosExt != other.pontosExt) {
            return false;
        }
        return true;
    }
    
    
    
}
