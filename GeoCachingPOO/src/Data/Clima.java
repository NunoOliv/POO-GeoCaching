/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Nuno Oliveira
 */
public class Clima implements Serializable {

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
        if (!this.desc.equals(other.getDesc())) {
            return false;
        }
        if (this.pontosExt != other.getPontosExt()) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Descrição: " + this.desc + "\nPontos: " + this.pontosExt;
    }

    @Override
    public Clima clone() {
        return new Clima(this.desc, this.pontosExt);
    }

}
