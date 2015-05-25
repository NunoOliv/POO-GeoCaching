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

    /**
     * Adiciona a descrição da criação de uma cache à lista de actividades 
     * 
     * @param mail endereço de email do utilizador que criou a cache.
     * @param user Nome do utilizador que adicionou a cache
     * @param cache Codigo do tipo de cache criada
     * @param ref Referencia da cache criada
     * @param pontos Pontos que vale a cache
     * @param date Data da criação da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException Exceção atirada quando o codigo do tipo de cache nao é valido.
     */
    public String addCache(String mail, String user, int cache, String ref, int pontos, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        //GregorianCalendar date = new GregorianCalendar();
        Atividade ativ = new Atividade(mail);

        ativ.addCache(user, cache, ref, pontos, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    
    /**
     * Adiciona a descrição da remoção de uma cache à lista de actividades 
     * 
     * @param mail endereço de email do utilizador que removeu a cache.
     * @param cache Codigo do tipo de cache removida
     * @param ref Referencia da cache removida
     * @param user Nome do utilizador que removeu a cache
     * @param date Data da remoção da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException Exceção atirada quando o codigo do tipo de cache nao é valido.
     */
    public String removeCache(String mail, int cache, String ref, String user, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        //GregorianCalendar date = new GregorianCalendar();
        Atividade ativ = new Atividade(mail);
        ativ.removeCache(cache, ref, user, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    /**
     * Adiciona a descrição da subscrição de uma cache à lista de actividades 
     * 
     * @param mail endereço de email do utilizador que assinou a cache.
     * @param cache Codigo do tipo de cache assinada
     * @param ref Referencia da cache assinada
     * @param user Nome do utilizador que assinou a cache
     * @param pontos Nomero de pontos que o utilizador obteve com a subscrição da cache.
     * @param clima Codigo do clima no momento em que a cache foi assinada.
     * @param date Data da Subscrição da cache.
     * @return String com a descrição da actividade
     * @throws TipoDeCacheNaoExisteException 
     */
    public String assinouCache(String mail, int cache, String ref, String user, int pontos, String clima, GregorianCalendar date) throws TipoDeCacheNaoExisteException {
        Atividade ativ = new Atividade(mail);
        ativ.assinouCache(cache, ref, user, pontos, clima, date);
        atividades.add(ativ);
        return ativ.toString();
    }

    /**
     * Adiciona a descrição da confirmação do pedido de amizade à lista de actividades 
     * 
     * @param mails endereço de email do utilizador que enviou o pedido de amizade.
     * @param sender nome do utilizador que enviou o pedido de amizade.
     * @param mailf endereço de email do utilizador que aceitou o pedido de amizade.
     * @param friend nomedo utilizador que aceitou o pedido de amizade.
     * @param date Data em que o pedido de amizade foi aceite.
     * @return  String com a descrição da actividade
     */
    public String addFriend(String mails, String sender, String mailf, String friend, GregorianCalendar date) {
        Atividade ativ = new Atividade(mails);
        Atividade ativ2 = new Atividade(mailf);
        ativ.addFriend(sender, friend, date);
        ativ2.addFriend(friend, sender, date);
        atividades.add(ativ);
        atividades.add(ativ2);
        return ativ.toString();
    }

    /**
     * Devolve uma lista de actividades realizadas por um grupo de utilizadores ordenada pela data, do mais rececente para o mais antigo.
     * 
     * @param amigos lista de utilizadores.
     * @return lista de actividades
     */
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
