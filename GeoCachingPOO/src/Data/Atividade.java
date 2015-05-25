package Data;

import Exceptions.TipoDeCacheNaoExisteException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
class Atividade implements Serializable, Comparable<Atividade> {

    private GregorianCalendar date;
    private final String user;
    private String atividade;
    

    public Atividade(String user) {
        this.user=user;
        atividade = null;
    }

    
    public Atividade(GregorianCalendar date, String user, String atividade) {
        this.date = date;
        this.user = user;
        this.atividade = atividade;
    }
    
    

    /**
     * Altera a data da cache.
     * 
     * @param date Nova data da cache.
     */
    public void setDate(GregorianCalendar date) {
        this.date = (GregorianCalendar) date.clone();
    }

    /**
     * Devolve o utilizador que realizou a actividade.
     * 
     * @return referencia do utilizador.
     */
    public String getUser() {
        return user;
    }

    
    /**
     * Adiciona a descrição da subscrição de uma cache à actividade 
     * 
     * @param cache Codigo do tipo de cache assinada
     * @param ref Referencia da cache assinada
     * @param user Nome do utilizador que assinou a cache
     * @param pontos Nomero de pontos que o utilizador obteve com a subscrição da cache.
     * @param clima Codigo do clima no momento em que a cache foi assinada.
     * @param date Data da subscrição da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException 
     */
    public String assinouCache(int cache, String ref, String user, int pontos, String clima, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        String ret;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        String dateS = formatter.format(date.getTime());
        ret = "(" + dateS + ") - " +user + " assinmou uma ";
        switch (cache) {
            case 1:
                ret += "Cache Tradicional";
                break;
            case 2:
                ret += "Micro-cache";
                break;
            case 3:
                ret += "Cache Mistério";
                break;
            case 4:
                ret += "Multi-Cache";
                break;
            case 5:
                ret += "Cache Evento";
                break;
            default:
                throw new TipoDeCacheNaoExisteException();

        }
        ret += ", com a referencia " + ref + " num dia de " + clima + ", e ganhou " + pontos + " pontos.";
        atividade = ret;
        this.date = (GregorianCalendar) date.clone();
        return ret;
    }

    /**
     * Adiciona a descrição da remoção de uma cache  à actividade 
     * 
     * @param cache Codigo do tipo de cache removida
     * @param ref Referencia da cache removida
     * @param user Nome do utilizador que removeu a cache
     * @param date Data da remoção da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException Exceção atirada quando o codigo do tipo de cache nao é valido.
     */
    public String removeCache(int cache, String ref, String user, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        String ret;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        String dateS = formatter.format(date.getTime());
        ret = user + " removeu uma ";
        switch (cache) {
            case 1:
                ret += "Cache Tradicional";
                break;
            case 2:
                ret += "Micro-cache";
                break;
            case 3:
                ret += "Cache Mistério";
                break;
            case 4:
                ret += "Multi-Cache";
                break;
            case 5:
                ret += "Cache Evento";
                break;
            default:
                throw new TipoDeCacheNaoExisteException();

        }
        ret += ", com a referencia " + ref + "no dia: " + dateS;
        atividade = ret;
        this.date = (GregorianCalendar) date.clone();
        return ret;
    }

    /**
     * Adiciona a descrição da criação de uma cache à atividades 
     * 
     * @param user Nome do utilizador que adicionou a cache
     * @param cache Codigo do tipo de cache criada
     * @param ref Referencia da cache criada
     * @param pontos Pontos que vale a cache
     * @param date Data da criação da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException Exceção atirada quando o codigo do tipo de cache nao é valido.
     */
    public String addCache(String user, int cache, String ref, int pontos, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        String ret;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        String dateS = formatter.format(date.getTime());
        ret = "(" + dateS + ") - " + user + " criou uma ";
        switch (cache) {
            case 1:
                ret += "Cache Tradicional";
                break;
            case 2:
                ret += "Micro-cache";
                break;
            case 3:
                ret += "Cache Mistério";
                break;
            case 4:
                ret += "Multi-Cache";
                break;
            case 5:
                ret += "Cache Evento";
                break;
            default:
                throw new TipoDeCacheNaoExisteException();

        }
        ret += ", com a referencia " + ref + " que vale " + pontos + "pontos.";
        atividade = ret;
        this.date = (GregorianCalendar) date.clone();
        return ret;
    }

    /**
     * Adiciona a descrição da confirmação do pedido de amizade à actividade
     * 
     * @param sender nome do utilizador que enviou o pedido de amizade.
     * @param friend nomedo utilizador que aceitou o pedido de amizade.
     * @param date Data em que o pedido de amizade foi aceite.
     * @return  String com a descrição da actividade
     */
    public String addFriend(String sender, String friend, GregorianCalendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        String dateS = formatter.format(date.getTime());
        this.date = (GregorianCalendar) date.clone();
        return "(" + dateS + ") - " +sender + " adicionou " + friend + " como amigo.";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.date);
        hash = 11 * hash + Objects.hashCode(this.user);
        hash = 11 * hash + Objects.hashCode(this.atividade);
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
        final Atividade other = (Atividade) obj;
        if (!this.date.equals(other.getDate()) ) {
            return false;
        }
        if (!this.user.equals(other.getUser())) {
            return false;
        }
        if (!this.atividade.equals(other.toString())) {
            return false;
        }
        return true;
    }
    
    
    
    /**
     * Devolve uma copia da data da realização da atividade.
     * 
     * @return Data da atividade
     */
    public GregorianCalendar getDate() {
        return (GregorianCalendar) date.clone();
    }

    @Override
    public String toString() {
        return atividade;
    }

    @Override
    public int compareTo(Atividade o) {
        GregorianCalendar d1, d2;
        Atividade a1 = this, a2 = o;
        d1 = a1.getDate();
        d2 = a2.getDate();
        return d1.compareTo(d2);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Atividade c = new Atividade((GregorianCalendar) date.clone(),user, atividade);
        return c;
    } 
}
