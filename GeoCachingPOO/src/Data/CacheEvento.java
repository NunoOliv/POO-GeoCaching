package Data;

import Exceptions.DificuldadeInvalidaException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class CacheEvento extends Cache implements Serializable {

    private HashSet<String> organizadores;
    private GregorianCalendar dataEvento;
    private int pontosExtra;

    /**
     *
     * @param ref
     * @param organizadores
     * @param dataEvento
     * @param pontosExtra
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public CacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String creator, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, descricao, dificuldade);
        this.organizadores = organizadores;
        this.dataEvento = dataEvento;
        this.pontosExtra = pontosExtra;
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param assinantes
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
    public CacheEvento(String ref, Coords coords, String creator, HashSet<String> assinantes, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        super(ref, coords, creator, assinantes, descricao, dificuldade);
        this.organizadores = new HashSet<>();
        this.dataEvento = new GregorianCalendar();
        this.pontosExtra = 0;
    }

    /**
     *
     * @param c
     * @throws DificuldadeInvalidaException
     */
    public CacheEvento(CacheEvento c) throws DificuldadeInvalidaException {
        super(c.getRef(), c.getCoords(), c.getCreator(), c.listaAssinantes(), c.getDescricao(), c.getDificuldade());
        this.setDataEvento(c.getDataEvento());
        this.setOrganizadores(c.getOrganizadores());
        this.setPontosExtra(this.getPontosExtra());
    }

    /**
     *
     * @param ref
     * @param coords
     * @param creator
     * @param descricao
     * @param dificuldade
     * @throws DificuldadeInvalidaException
     */
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

    /**
     *
     * @param dia
     * @param mes
     * @param ano
     */
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

    /**
     *
     * @param user
     * @return
     */
    public boolean addOrganizador(String user) {
        return this.organizadores.add(user);
    }

    /**
     *
     * @param user
     * @return
     */
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

    /**
     *
     * @return
     */
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.organizadores);
        hash = 23 * hash + Objects.hashCode(this.dataEvento);
        hash = 23 * hash + this.pontosExtra;
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
        final CacheEvento other = (CacheEvento) obj;
        if (!Objects.equals(this.organizadores, other.getOrganizadores())) {
            return false;
        }
        if (!Objects.equals(this.dataEvento, other.getDataEvento())) {
            return false;
        }
        if (this.pontosExtra != other.getPontosExtra()) {
            return false;
        }
        return true;
    }
    
    

}
