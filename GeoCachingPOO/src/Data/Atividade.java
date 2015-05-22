package Data;

import Exceptions.TipoDeCacheNaoExisteException;
import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
class Atividade implements Serializable, Comparable<Atividade> {
    
    GregorianCalendar date;

    private String atividade;

    public Atividade() {
        atividade = null;
    }

    public String addCache(int cache, String ref, String user, int pontos, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        String ret;
        ret = user + " criou uma ";
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
        ret += ", com a referencia " + ref + " e ganhou " + pontos + "pontos, no dia: "+ date;
        atividade = ret;
        this.date = date;
        return ret;
    }

    public String getActividade() {
        return atividade;
    }

    public GregorianCalendar getDate() {
        return date;
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
    
}
