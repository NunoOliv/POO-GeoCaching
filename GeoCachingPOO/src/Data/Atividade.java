package Data;

import Exceptions.TipoDeCacheNaoExisteException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
class Atividade implements Serializable, Comparable<Atividade> {

    private GregorianCalendar date;
    private String user;
    private String atividade;
    

    public Atividade(String user) {
        this.user=user;
        atividade = null;
    }

    public void setDate(GregorianCalendar date) {
        this.date = (GregorianCalendar) date.clone();
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }
    
    

    public void setAtividade(String atividade) {
        this.atividade = atividade;
    }

    
    
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
        ret += ", com a referencia " + ref + " e ganhou " + pontos + "pontos.";
        atividade = ret;
        this.date = (GregorianCalendar) date.clone();
        return ret;
    }

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

    public String addFriend(String sender, String friend, GregorianCalendar date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYY");
        String dateS = formatter.format(date.getTime());
        this.date = (GregorianCalendar) date.clone();
        return "(" + dateS + ") - " +sender + " adicionou " + friend + " como amigo.";
    }
    
    

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
        Atividade c = new Atividade(user);
        c.setAtividade(atividade);
        c.setDate((GregorianCalendar) date.clone());
        return c;
    }

    
    
}
