/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Exceptions.TipoDeCacheNaoExisteException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

/**
 *
 * @author naso
 */
public class AtivList implements Serializable{

    private final ArrayList<Atividade> atividades;

    public AtivList() {
        this.atividades = new ArrayList<>();
    }

    public String addCache(String mail, String user, int cache, String ref, int pontos, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        //GregorianCalendar date = new GregorianCalendar();
        Atividade ativ = new Atividade(mail);

        ativ.addCache(user, cache, ref, pontos, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    public String removeCache(String mail, int cache, String ref, String user, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        //GregorianCalendar date = new GregorianCalendar();
        Atividade ativ = new Atividade(mail);
        ativ.removeCache(cache, ref, user, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    public String assinouCache(String mail, int cache, String ref, String user, int pontos, String clima, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        Atividade ativ = new Atividade(mail);
        ativ.assinouCache(cache, ref, user, pontos, clima, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    public String addFriend(String mails, String sender,String mailf, String friend, GregorianCalendar date) {
        Atividade ativ = new Atividade(mails);
        Atividade ativ2 = new Atividade(mailf);
        ativ.addFriend(sender, friend, date);
        ativ2.addFriend(friend, sender, date);
        atividades.add(ativ);
        atividades.add(ativ2);
        return ativ.toString();
    }

    public ArrayList<String> getAtividades(ArrayList<String> amigos) {
        ArrayList<Atividade> aux = new ArrayList<>();
        ArrayList<String> ret = new ArrayList<>();

        for (Atividade a : atividades) {
            for (String amigo : amigos) {
                if (a.getUser().equals(amigo)) {
                    aux.add(a);
                    break;
                }
            }
        }

        Collections.sort(aux);
        for (Atividade a : aux) {
            ret.add(a.toString());
        }

        return ret;
    }
}
