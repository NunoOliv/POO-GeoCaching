package Business;

import Data.AtivList;
import Data.CacheList;
import Data.Coords;
import Data.User;
import Data.UserList;
import Exceptions.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Core implements Serializable {

    private UserList userL;
    private User sessao;
    private CacheList cacheL;
    private AtivList ativL;

    public Core() {
        userL = new UserList();
        sessao = null;
        cacheL = new CacheList();
        ativL = new AtivList();
    }
    
    public void erase() {
        userL = new UserList();
        sessao = null;
        cacheL = new CacheList();
        ativL = new AtivList();
        
    }
            

    public void login(String mail, String pass) throws EmailInvalidoException, CamposInvalidosException, UserNaoExisteException, PasswordMissmatchException {
        userL.existeUser(mail);
        if (userL.checkPass(mail, pass)) {
            sessao = userL.getUser(mail);
        } else {
            throw new PasswordMissmatchException();
        }
    }

    public void registar(String mail, String pass, String nome, String genero, String morada, int dia, int mes, int ano) throws CamposInvalidosException, EmailJaExisteException, GeneroInvalidoException, DataInvalidaException, EmailInvalidoException {
        checkMail(mail);
        LocalDate dn = buildDate(dia, mes, ano);
        if (genero.equals("M") || genero.equals("m")) {
            genero = "Masculino";
        }
        if (genero.equals("F") || genero.equals("f")) {
            genero = "Feminino";
        }
        if (!genero.equals("Masculino") && !genero.equals("Feminino")) {
            throw new GeneroInvalidoException();
        }
        userL.addUser(mail, pass, nome, genero, morada, dn);
    }

    public boolean existeUser(String user) throws EmailInvalidoException {
        return userL.existeUser(user);
    }

    /**
     * Verifica se o email dado está na forma correta.
     *
     * @param mail Email a verificar.
     * @throws EmailInvalidoException
     */
    public void checkMail(String mail) throws EmailInvalidoException {
        if (mail == null) {
            throw new EmailInvalidoException();
        }
        if (mail.equals("")) {
            throw new EmailInvalidoException();
        }
        if (!mail.matches("[\\w\\d\\._]+@[\\w\\d\\.]*[\\w\\d]\\.[\\w]+")) {
            throw new EmailInvalidoException();
        }
    }

    /**
     * Dado um dia, mes e ano constroí o respetivo LocalDate.
     *
     * @param dia De 1 a 31
     * @param mes De 1 a 12
     * @param ano Menor do que o ano atual.
     * @return LocalDate respetivo.
     * @throws CamposInvalidosException
     */
    public LocalDate buildDate(int dia, int mes, int ano) throws CamposInvalidosException {
        if (dia > 31 || dia < 1) {
            throw new CamposInvalidosException();
        }
        if (mes > 12 || mes < 1) {
            throw new CamposInvalidosException();
        }
        if (ano > LocalDate.now().getYear()) {
            throw new CamposInvalidosException();
        }
        return LocalDate.of(ano, mes, dia);
    }

    /**
     * Altera do nome do utilizador com a sessão iniciada.
     *
     * @param name Novo nome a utilizar.
     * @throws NomeInvalidoException
     */
    public void updateName(String name) throws NomeInvalidoException {
        if (name == null) {
            throw new NomeInvalidoException();
        }
        if (name.equals("")) {
            throw new NomeInvalidoException();
        }
        sessao.setNome(name);
    }

    /**
     * Altera a morada do utilizador com sessão iniciar.
     *
     * @param x Nova morada a registar.
     * @throws MoradaInvalidaException
     */
    public void updateMorada(String x) throws MoradaInvalidaException {
        if (x == null) {
            throw new MoradaInvalidaException();
        }
        if (x.equals("")) {
            throw new MoradaInvalidaException();
        }
        sessao.setMorada(x);
    }

    /**
     * Troca o género do utilizador com sessão inciada.
     */
    public void trocaGenero() {
        String g = sessao.getGenero();
        if (g.equals("Masculino")) {
            sessao.setGenero("Feminino");
        } else {
            sessao.setGenero("Masculino");
        }
    }

    /**
     * Altera a data de nascimento do utilizador com sessão iniciada.
     *
     * @param dia Novo dia a registar.
     * @param mes Novo mês a registar.
     * @param ano Novo ano a registar.
     * @throws CamposInvalidosException
     */
    public void updateDN(int dia, int mes, int ano) throws CamposInvalidosException {
        sessao.setDn(buildDate(dia, mes, ano));
    }

    /**
     * Verifica se a palavra-passe dada corresponde à palavra pass do utilizador
     * com sessão iniciada.
     *
     * @param x Palavra-passe dada.
     * @return True se a palavra-passe dada corresponde à palavra passe do
     * utilizador, False caso contrário.
     */
    public boolean checkPass(String x) {
        return sessao.checkPass(x);
    }

    /**
     * Altera a palavra passe do utilizador com sessão inciada.
     *
     * @param x Nova palavra-passe a registar.
     * @throws PasswordInvalidaException
     */
    public void updatePass(String x) throws PasswordInvalidaException {
        if (x == null) {
            throw new PasswordInvalidaException();
        }
        if (x.equals("")) {
            throw new PasswordInvalidaException();
        }
        sessao.setPw(x);
    }

    /**
     * Cria uma cópia do utilizador com sessão iniciada, menos a sua
     * palavra-passe.
     *
     * @return Devolve a cópia.
     */
    public User getInfo() {
        User u = new User(sessao);
        u.setPw("");
        return u;
    }

    /**
     * Permite pedir outro utilizador em amizade.
     *
     * @param m Email do utilizador a quem se pretende pedir.
     * @throws EmailInvalidoException
     * @throws UserNaoExisteException
     * @throws JaEAmigoException
     * @throws Exceptions.PedidoInvalidoException
     */
    public void pedeAmigo(String m) throws EmailInvalidoException, UserNaoExisteException, JaEAmigoException, PedidoInvalidoException {
        checkMail(m);
        if (sessao.getMail().equals(m)) {
            throw new PedidoInvalidoException("Não é possivel adicionar o próprio email como amigo");
            
        }
        User u = userL.getUser(m);
        u.addPedido(sessao);
    }

    /**
     * Aceita um pedido de amizade, remove-o da lista de pedidos.
     *
     * @param m Email do utilizador a quem se pretende aceitar.
     * @throws EmailInvalidoException
     * @throws UserNaoExisteException
     * @throws PedidoNaoExisteException
     * @throws Exceptions.JaEAmigoException
     */
    public void aceitaAmigo(String m) throws EmailInvalidoException, UserNaoExisteException, PedidoNaoExisteException, JaEAmigoException {
        GregorianCalendar date = new GregorianCalendar();
        checkMail(m);
        User u = userL.getUser(m);
        sessao.aceitaPedido(u);
        u.addAmigo(sessao);
        ativL.addFriend(u.getMail(), u.getNome(), sessao.getMail(), sessao.getNome(), date);
    }

    /**
     * Operações com Caches
     */
    /**
     * Devolve uma lista que contem todas as caches da cacheList
     *
     * @return ArrayList&lt;String&gt; correspondente a lista de caches
     */
    public ArrayList<String> getListaCaches() {
        ArrayList<String> ret = cacheL.getListaCacheNames();
        Collections.sort(ret);
        return ret;
    }

    /**
     * Devolve lista de detalhes de uma cache em formato String
     *
     * @param cache Identificador da cache
     * @return String com detalhes da cache
     */
    public String getDetalhesCache(String cache) {
        try {
            return cacheL.getDetalhesCache(cache);
        } catch (CacheNaoExisteException ex) {
            return ex.getMessage();
        }
    }

    /**
     * Retorna a String correspondente ao criador da cache.
     *
     * @param cache Identificador da cache
     * @return Identificador do criador da cache
     * @throws Exceptions.CacheNaoExisteException
     */
    public String getCriadorCache(String cache) throws CacheNaoExisteException {
        return cacheL.getCriador(cache);
    }

    /**
     * Verifica se o utilizador com sessão iniciada é o criador de uma cache.
     *
     * @param cache Identificador da cache
     * @return Verdadeiro se
     * @throws Exceptions.CacheNaoExisteException
     */
    public boolean isCriador(String cache) throws CacheNaoExisteException {
        return cacheL.isCriador(cache, this.sessao.getMail());
    }

    /**
     * Cria uma Cache tradicional atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean addTradCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException, CacheNaoExisteException, TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        boolean add = cacheL.addTradCache(ref, coords, sessao.getMail(), descricao, dificuldade);
        if (add) {
            ativL.addCache(sessao.getMail(), sessao.getNome(), 1, ref, cacheL.getPontos(ref), date);
        }
        return add;
    }

    /**
     * Cria uma Cache Evento atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param organizadores Organizadores do evento
     * @param dataEvento Data do Evento
     * @param pontosExtra Pontuação extra da cache
     * @param coords Coordenadas da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean addCacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException, CacheNaoExisteException, TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        boolean add = cacheL.addCacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, sessao.getMail(), descricao, dificuldade);
        if (add) {
            ativL.addCache(sessao.getMail(), sessao.getNome(), 5, ref, cacheL.getPontos(ref), date);

        }
        return add;
    }

    /**
     * Cria uma Cache Mistério atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param DescPuzzle Descrição do Puzzle
     * @param pontosExtra Pontuação extra da cache
     * @param coords Coordenadas da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException, CacheNaoExisteException, TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        boolean add = cacheL.addCacheMisterio(ref, DescPuzzle, pontosExtra, coords, sessao.getMail(), descricao, dificuldade);
        if (add) {
            ativL.addCache(sessao.getMail(), sessao.getNome(), 3, ref, cacheL.getPontos(ref), date);
        }
        return add;
    }

    /**
     * Cria uma Micro-Cache atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean addMicroCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException, CacheNaoExisteException, TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        boolean add = cacheL.addMicroCache(ref, coords, sessao.getMail(), descricao, dificuldade);
        if (add) {
            ativL.addCache(sessao.getMail(), sessao.getNome(), 2, ref, cacheL.getPontos(ref), date);
        }
        return add;
    }

    /**
     * Cria uma MultiCache atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param descricao Descrição da Cache
     * @param pontosIntermedios Coordenadas dos pontos intermedios da Cache
     * @param dificuldade Dificuldade da Cache
     * @param pontosExtra Pontuação extra da cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean addmultiCache(String ref, Coords coords, String descricao, HashMap<Integer, Coords> pontosIntermedios, int dificuldade, int pontosExtra) throws DificuldadeInvalidaException, CacheNaoExisteException, TipoDeCacheNaoExisteException {
        GregorianCalendar date = new GregorianCalendar();
        boolean add = cacheL.MultiCache(ref, coords, sessao.getMail(), descricao, pontosIntermedios, dificuldade, pontosExtra);
        if (add) {
            ativL.addCache(sessao.getMail(), sessao.getNome(), 4, ref, cacheL.getPontos(ref), date);
        }
        return add;
    }

    /**
     * Verifica se existe uma cache com uma dada referencia
     *
     * @param ref Referencia da cache
     * @return
     */
    public boolean containsCache(String ref) {
        return cacheL.containsCache(ref);
    }

    /**
     * Verifica se a cache suporta tesouros.
     *
     * @param cache Referencia da Cache a verificar.
     * @return
     */
    public boolean suportaTesouros(String cache) {
        return cacheL.suportaTesouros(cache);
    }

    /**
     * Verifica se a cache suporta eventos.
     *
     * @param cache Referencia da cache a verificar
     * @return
     */
    public boolean suportaEventos(String cache) {
        return cacheL.suportaEventos(cache);
    }

    /**
     * Altera a descrição de uma cache.
     *
     * @param cache Cache que tem a descrição alterada.
     * @param desc Nova descrição para a cache.
     * @return True se a descrição foi alterada ou False se não foi possivel
     * alterar a descrição
     */
    public boolean setDescricaoCache(String cache, String desc) {
        return cacheL.setDescricaoCache(cache, desc);
    }

    /**
     * Remove uma cache do sistema.
     *
     * @param cache Cache que vai ser removida.
     * @return True se a cache foi removida ou FALSE se não foi possivel remover
     * a cache.
     */
    public boolean remCache(String cache) {
        boolean rem = cacheL.remCache(cache);
        if (rem) {
            try {
                ativL.removeCache(sessao.getMail(), cacheL.getCacheType(cache), cache, sessao.getNome(), new GregorianCalendar());
            } catch (TipoDeCacheNaoExisteException | CacheNaoExisteException ex) {
                return false;
            }
        }

        return rem;
    }

    /**
     * Permite ao utilizador com sessão iniciada assinar a cache.
     *
     * @param cache Cache que vai ser assinada.
     * @return
     * @throws Exceptions.CacheNaoExisteException
     * @throws Exceptions.TipoDeCacheNaoExisteException
     */
    public boolean assinarCache(String cache) throws CacheNaoExisteException, TipoDeCacheNaoExisteException {
        boolean ass = cacheL.assinarCache(cache, sessao.getMail());
        int weather = this.calculateWeather();
        int pontos = cacheL.getPontos(cache) + weather;
        if (ass) {
            sessao.addPontos(pontos);
            ativL.assinouCache(sessao.getMail(), cacheL.getCacheType(cache), cache, sessao.getNome(), pontos, this.weatherToString(weather), new GregorianCalendar());
        }
        return ass;
    }

    /**
     * Devolve a lista dos assinantes de uma cache.
     *
     * @param cache Referencia da cache.
     * @return Lista de Assinantes.
     * @throws Exceptions.CacheNaoExisteException
     */
    public ArrayList<String> getListaAssinantes(String cache) throws CacheNaoExisteException {
        return new ArrayList<>(cacheL.getListaAssinantes(cache));
    }

    /**
     * Devolve a lista de tesouros de uma cache.
     *
     * @param cache Referencia da cache.
     * @return Lista de tesouros
     * @throws Exceptions.CacheNaoExisteException
     */
    public ArrayList<String> getListTesouros(String cache) throws CacheNaoExisteException {
        try {
            return cacheL.getListTesouros(cache);
        } catch (CacheNaoSuportaFuncionalidadeException ex) {
            ex.getMessage();
        }
        return null;
    }

    /**
     * Adiciona um tesouro a uma cache.
     *
     * @param tesouro Nome/Descrição do tesouro
     * @param cache Referencia da cache.
     * @return TRUE se adicionou o tesouro ou FALSE sde nao adicionou.
     * @throws CacheNaoSuportaFuncionalidadeException
     * @throws Exceptions.CacheNaoExisteException
     */
    public boolean addTesouro(String tesouro, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.addTesouro(tesouro, cache);
    }

    public boolean takeTesouro(String tesouro, String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.takeTesouro(tesouro, cache);
    }

    public ArrayList<String> getListBugs(String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.getListBugs(tradCache);
    }

    public boolean addBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.addBug(bug, tradCache);
    }

    public boolean takeBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.takeBug(bug, tradCache);
    }

    public ArrayList<String> getFreeBugs(){
        return cacheL.getFreeBugs();
    }
    public String getBugDetails(String bug){
        return cacheL.getBugDetails(bug);
    }
    
    public ArrayList<String> getListaOrg(String cache) throws CacheNaoSuportaFuncionalidadeException, CacheNaoExisteException {
        return cacheL.getListaOrg(cache);
    }

    public ArrayList<String> getAtividadesProprio() {
        ArrayList<String> a = new ArrayList<>();
        a.add(sessao.getMail());
        return ativL.getAtividades(a);
    }

    public ArrayList<String> getAtividadesAmigos() {
        ArrayList<String> a = new ArrayList<>();
        for (String s : sessao.listaIdentAmigos()) {
            a.add(s);
        }
        return ativL.getAtividades(a);
    }

    public ArrayList<String> getAtividadesAmigo(String amigo) throws EmailInvalidoException {
        if (userL.existeUser(amigo) && sessao.listaIdentAmigos().contains(amigo)) {
            ArrayList<String> a = new ArrayList<>();
            a.add(amigo);
            return ativL.getAtividades(a);
        } else {
            throw new EmailInvalidoException();
        }

    }

    private int calculateWeather() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(3);
    }

    private String weatherToString(int w) {
        switch (w) {
            case 0:
                return "ceu limpo";
            case 1:
                return "ceu nublado";
            case 2:
                return "chuva";
            default:
                return null;
        }
    }

    /**
     * Grava os dados em ficheiro.
     *
     * @param ficheiro Localização do ficheiro a gravar.
     */
    public void guardar(String ficheiro) {
        System.out.println("A gravar dados...");
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(ficheiro);
            try (ObjectOutputStream outF = new ObjectOutputStream(fileOut)) {
                outF.writeObject(this);
            }
            fileOut.close();
            System.out.println("Dados guardados em: " + ficheiro);
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    }

    public boolean containsBug(String bug) {
        return cacheL.containsBug(bug);
    }

    
}
