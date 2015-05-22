package Business;

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
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Core implements Serializable {

    private final UserList userL;
    private User sessao;
    private final CacheList cacheL;

    public Core() {
        userL = new UserList();
        sessao = null;
        cacheL = new CacheList();
    }

    public void inicialize() {
        User rafa, nuno, rui;
        LocalDate dn1 = LocalDate.of(1994, Month.OCTOBER, 27);
        LocalDate dn2 = LocalDate.of(1994, Month.JUNE, 10);
        LocalDate dn3 = LocalDate.of(1994, Month.MAY, 4);
        try {
            userL.addUser("rafa@mail.com", "123", "Rafael", "Masculino", "Rua da Pera", dn1);
            userL.addUser("nuno@mail.com", "123", "Nuno", "Masculino", "Rua da Laranja", dn2);
            userL.addUser("rui@mail.com", "123", "Rui", "Masculino", "Rua da Maçâ", dn3);
            try {//Adicionar amigos!
                rafa = userL.getUser("rafa@mail.com");
                nuno = userL.getUser("nuno@mail.com");
                rui = userL.getUser("rui@mail.com");

                rafa.addPedido(nuno);
                rafa.aceitaPedido(nuno);
                rafa.addPedido(rui);
                rafa.aceitaPedido(rui);
            } catch (EmailInvalidoException | UserNaoExisteException | JaEAmigoException | PedidoNaoExisteException ex) {
                System.out.println("ERRO na inicialização!");
                Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
            }
            cacheL.addMicroCache("cache1", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache2", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache3", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache4", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache5", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache6", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache7", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache8", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache9", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache10", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache11", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache12", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache13", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache14", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache15", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache16", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache17", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache18", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache19", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache20", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache21", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache22", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache23", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);
            cacheL.addMicroCache("cache24", new Coords(3265, 54654), "nuno@mail.com", "Cache teste", 3);

        } catch (EmailJaExisteException | CamposInvalidosException | GeneroInvalidoException | DataInvalidaException | DificuldadeInvalidaException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (!mail.matches("[a-z]+@[a-z]+\\.[a-z]+")) {
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
     */
    public void pedeAmigo(String m) throws EmailInvalidoException, UserNaoExisteException, JaEAmigoException {
        checkMail(m);
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
     */
    public void aceitaAmigo(String m) throws EmailInvalidoException, UserNaoExisteException, PedidoNaoExisteException, JaEAmigoException {
        checkMail(m);
        User u = userL.getUser(m);
        sessao.aceitaPedido(u);
        u.addAmigo(sessao);
    }

    /**
     * Operações com Caches
     */
    /**
     * Devolve uma lista que contem todas as caches da cacheList
     *
     * @return ArrayList<Strings> correspondente a lista de caches
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
        return cacheL.getDetalhesCache(cache);
    }

    /**
     * Retorna a String correspondente ao criador da cache.
     *
     * @param cache Identificador da cache
     * @return Identificador do criador da cache
     */
    public String getCriadorCache(String cache) {
        return cacheL.getCriador(cache);
    }

    /**
     * Verifica se o utilizador com sessão iniciada é o criador de uma cache.
     *
     * @param cache Identificador da cache
     * @return Verdadeiro se
     */
    public boolean isCriador(String cache) {
        return cacheL.isCriador(cache, this.sessao.getMail());
    }

    /**
     * Cria uma Cache tradicional atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param creator Criador da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     */
    public boolean addTradCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        boolean add = cacheL.addTradCache(ref, coords, sessao.getMail(), descricao, dificuldade);
        if(add) {
            
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
     * @param creator Criador da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     */
    public boolean addCacheEvento(String ref, HashSet<String> organizadores, GregorianCalendar dataEvento, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return cacheL.addCacheEvento(ref, organizadores, dataEvento, pontosExtra, coords, sessao.getMail(), descricao, dificuldade);
    }

    /**
     * Cria uma Cache Mistério atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param DescPuzzle Descrição do Puzzle
     * @param pontosExtra Pontuação extra da cache
     * @param coords Coordenadas da Cache
     * @param creator Criador da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade Dificuldade da Cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     */
    public boolean addCacheMisterio(String ref, String DescPuzzle, int pontosExtra, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return cacheL.addCacheMisterio(ref, DescPuzzle, pontosExtra, coords, sessao.getMail(), descricao, dificuldade);
    }

    /**
     * Cria uma Micro-Cache atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param creator Criador da Cache
     * @param descricao Descrição da Cache
     * @param dificuldade
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     */
    public boolean addMicroCache(String ref, Coords coords, String descricao, int dificuldade) throws DificuldadeInvalidaException {
        return cacheL.addMicroCache(ref, coords, sessao.getMail(), descricao, dificuldade);
    }

    /**
     * Cria uma MultiCache atravez dos parametros fornecidos
     *
     * @param ref Referencia da Cache
     * @param coords Coordenadas da Cache
     * @param creator Criador da Cache
     * @param descricao Descrição da Cache
     * @param pontosIntermedios Coordenadas dos pontos intermedios da Cache
     * @param dificuldade Dificuldade da Cache
     * @param pontosExtra Pontuação extra da cache
     * @return True se a cache foi cridada e adicionada ou False se nao foi
     * possivel criar a Cache
     * @throws DificuldadeInvalidaException
     */
    public boolean addmultiCache(String ref, Coords coords, String descricao, HashMap<Integer, Coords> pontosIntermedios, int dificuldade, int pontosExtra) throws DificuldadeInvalidaException {
        return cacheL.MultiCache(ref, coords, sessao.getMail(), descricao, pontosIntermedios, dificuldade, pontosExtra);
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
     * @return True se a descrição foi alterada ou False se não foi possivel alterar a descrição
     */
    public boolean setDescricaoCache(String cache, String desc) {
        return cacheL.setDescricaoCache(cache, desc);
    }
    /**
     * Remove uma cache do sistema.
     * 
     * @param cache Cache que vai ser removida.
     * @return True se a cache foi removida ou FALSE se não foi possivel remover a cache.
     */
    public boolean remCache(String cache) {
        return cacheL.remCache(cache);
    }
    
    
    /**
     * Permite ao utilizador com sessão iniciada assinar a cache.
     * 
     * @param cache Cache que vai ser assinada.
     * @return 
     */
    public boolean assinarCache(String cache) {
        return cacheL.assinarCache(cache, sessao.getMail());
    }
    
    /**
     * Devolve a lista dos assinantes de uma cache.
     * 
     * @param cache Referencia da cache.
     * @return Lista de Assinantes.
     */
    public ArrayList<String> getListaAssinantes(String cache) {
        return new ArrayList<> (cacheL.getListaAssinantes(cache));
    }
    
    /**
     * Devolve a lista de tesouros de uma cache.
     * 
     * @param cache Referencia da cache.
     * @return Lista de tesouros
     */
    public ArrayList<String> getListTesouros(String cache) {
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
     */
    public boolean addTesouro(String tesouro, String cache) throws CacheNaoSuportaFuncionalidadeException {
        return cacheL.addTesouro(tesouro, cache);
    }
    
    public boolean takeTesouro(String tesouro, String cache) throws CacheNaoSuportaFuncionalidadeException {
        return cacheL.takeTesouro(tesouro, cache);
    }
    
    public ArrayList<String> getListBugs(String tradCache) throws CacheNaoSuportaFuncionalidadeException {
        return  cacheL.getListBugs(tradCache);
    }
    
    public boolean addBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException {
        return cacheL.addBug(bug, tradCache);
    }
    
    public boolean takeBug(String bug, String tradCache) throws CacheNaoSuportaFuncionalidadeException {
        return cacheL.takeBug(bug, tradCache);
    }
    
     public ArrayList<String> getListaOrg(String cache) throws CacheNaoSuportaFuncionalidadeException {
          return cacheL.getListaOrg(cache);
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
}
