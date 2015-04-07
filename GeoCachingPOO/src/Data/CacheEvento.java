/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.DificuldadeInvalidaException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author Nuno Oliveira
 */
public class CacheEvento extends Cache{
    
    private HashSet<User> organizadores;
    private GregorianCalendar dataEvento;
    private int pontosExtra;

    public CacheEvento(HashSet<User> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(coords, assinantes, descricao, dificuldade);
        this.organizadores = organizadores;
        this.dataEvento = dataEvento;
        this.pontosExtra = pontosExtra;
    }

    public CacheEvento(Coords coords, HashMap<String, User> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(coords, assinantes, descricao, dificuldade);
        this.organizadores = new HashSet<>();
        this.dataEvento = new GregorianCalendar();
        this.pontosExtra = 0;
    }

    public CacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        super(c.getCoords(), c.getAssinantes(), c.getDescricao(), c.getDificuldade());
        this.setDataEvento(c.getDataEvento());
        this.setOrganizadores(c.getOrganizadores());
        this.setPontosExtra(this.getPontosExtra());
    }

    
    

    /**
     * @return the organizadores
     */
    public HashSet<User> getOrganizadores() {
        return organizadores;
    }

    /**
     * @param organizadores the organizadores to set
     */
    public void setOrganizadores(HashSet<User> organizadores) {
        this.organizadores = organizadores;
    }

    /**
     * @return the dataEvento
     */
    public GregorianCalendar getDataEvento() {
        return dataEvento;
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
    public int getPontosExtra() {
        return pontosExtra;
    }

    /**
     * @param pontosExtra the pontosExtra to set
     */
    public void setPontosExtra(int pontosExtra) {
        this.pontosExtra = pontosExtra;
    }
      
    
    
    public boolean addOrganizador(User user) {
        return this.organizadores.add(user);
    }
    
    public boolean removeOrganizador(User user) {
        return this.organizadores.remove(user);
    }

    /**
     * Devolve pontos da Cache Evento
     * @return 
     */
    @Override
    public int getPoints() {
        return super.getPoints()+getPontosExtra();
    }

         
    
    

    
}
