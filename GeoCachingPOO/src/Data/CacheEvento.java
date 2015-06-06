/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;

/**
 *
 * @author Nuno Oliveira
 */
public class CacheEvento extends Cache implements Serializable {

    private HashSet<String> organizadores;
    private GregorianCalendar dataEvento;
    private int pontosExtra;

    public CacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.organizadores = organizadores;
        this.dataEvento = dataEvento;
        this.pontosExtra = pontosExtra;
    }

    public CacheEvento(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.organizadores = new HashSet<>();
        this.dataEvento = new GregorianCalendar();
        this.pontosExtra = 0;
    }

    public CacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getCoords(), c.getCreator(), c.listaAssinantes(), c.getDescricao(), c.getDificuldade());
        this.setDataEvento(c.getDataEvento());
        this.setOrganizadores(c.getOrganizadores());
        this.setPontosExtra(this.getPontosExtra());
    }

    public CacheEvento(String ref, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.organizadores = new HashSet<>();
        this.dataEvento = new GregorianCalendar();
        this.pontosExtra = 0;
    }

    /**
     * @return the organizadores
     */
    public HashSet<String> getOrganizadores() {
        return organizadores;
    }

    /**
     * @param organizadores the organizadores to set
     */
    public void setOrganizadores(HashSet<String> organizadores) {
        this.organizadores = organizadores;
    }

    /**
     * @return the dataEvento
     */
    public GregorianCalendar getDataEvento() {
        return (GregorianCalendar) dataEvento.clone();
    }

    /**
     * @param dataEvento the dataEvento to set
     */
    public void setDataEvento(GregorianCalendar dataEvento) {
        this.dataEvento = dataEvento;
    }

    public void setDataEvento(int dia, int mes, int ano) {
        this.dataEvento = new GregorianCalendar(ano, mes, dia);
    }

    /**
     * @return the pontosExtra
     */
    @Override
    public int getPontosExtra() {
        return pontosExtra;
    }

    /**
     * @param pontosExtra the pontosExtra to set
     */
    @Override
    public void setPontosExtra(int pontosExtra) {
        this.pontosExtra = pontosExtra;
    }

    public boolean addOrganizador(String user) {
        return this.organizadores.add(user);
    }

    public boolean removeOrganizador(String user) {
        return this.organizadores.remove(user);
    }

    /**
     * Devolve pontos da Cache Evento
     *
     * @return
     */
    @Override
    public int getPoints() {
        return super.getPoints() + getPontosExtra();
    }

    public ArrayList<String> getListaOrg() {
        ArrayList<String> ret = new ArrayList<>();
        for (String t : organizadores) {
            ret.add(t);
        }
        return ret;
    }

    @Override
    public boolean addAssinante(String nome) throws Exception {
        if ((new GregorianCalendar().compareTo(dataEvento)) <0 ) 
        {
            throw new  Exception("Evento já Expirou! Não e possivel assinar este Evento");
        }
        return super.addAssinante(nome); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public Cache clone() {
        try {
            return new CacheEvento(this);
        } catch (DificuldadeInvalidaException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        String ret = super.toString();
        String org = "";

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = formatter.format(dataEvento.getTime());

        ret = ret.concat("Data do Evento: " + formattedDate + "\n");

        int i = 1;
        for (String s : organizadores) {
            org = org.concat(s);
            if (i != organizadores.size() - 1) {
                org = org.concat(", ");
            }
        }

        ret = ret.concat("Organizadores: " + org + "\n");
        ret = ret.concat("Pontos Extra: " + pontosExtra + "\n");

        return ret;
    }

    @Override
    public String getCacheType() {
        return "Cache Evento";
    }

    @Override
    public int getCacheCode() {
        return 5;
    }

}
