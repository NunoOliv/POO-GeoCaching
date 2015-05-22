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
class Atividade implements Serializable {

    private final String atividade;

    public Atividade(int code) {
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
        ret += ", com a referencia " + ref + " e ganhou " + pontos + "pontos, no dia: "+ date.;
        return ret;
    }

    public String getActividade() {
        return atividade;
    }
}
