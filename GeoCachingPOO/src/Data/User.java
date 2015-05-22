package Data;

import java.time.LocalDate;
import java.util.HashMap;

import Exceptions.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class User implements Serializable{

    private String mail;
    private String pw;
    private String nome;
    private String genero;
    private String morada;
    private LocalDate dn;
    private int totalPontos = 0;
    private final HashMap<String, User> amigos;
    private final HashMap<String, User> pedidosAmigo; //users q querem ser amigos deste.
    //private final HashSet<Atividade> ativs;

    public User(String mail, String pw, String nome, String genero, String morada, LocalDate dn) {
        this.mail = mail;
        this.pw = pw;
        this.nome = nome;
        this.genero = genero;
        this.morada = morada;
        this.dn = dn;
        this.amigos = new HashMap<>();
        this.pedidosAmigo = new HashMap<>();
        //this.ativs = new HashSet<>();
    }

    public User(User u) {
        this.mail = u.getMail();
        this.pw = u.getPw();
        this.nome = u.getNome();
        this.genero = u.getGenero();
        this.morada = u.getMorada();
        this.dn = u.getDn();
        this.amigos = u.cloneAmigos();
        this.pedidosAmigo = u.clonePedidos();
        //this.ativs = u.cloneAtividades();
    }
    /**
     * 
     * @return total de Pontos
     */
    public int getTotalPontos() {
        return totalPontos;
    }
    /**
     * 
     * @param TotalPontos 
     */
    public void setTotalPontos(int TotalPontos) {
        this.totalPontos = TotalPontos;
    }
    
    /**
     * Adiciona um valor à pontuação total.
     * @param pontos Pontos a adicionar.
     */
    public void addPontos(int pontos) {
        totalPontos+= pontos;
    }
    
    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the pw
     */
    private String getPw() {
        return pw;
    }

    /**
     * Verifica se as passwords são iguais.
     *
     * @param pass Password.
     * @return True se são iguais, False caso contrário.
     */
    public boolean checkPass(String pass) {
        return this.pw.equals(pass);
    }

    /**
     * @param pw the pw to set
     */
    public void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the morada
     */
    public String getMorada() {
        return morada;
    }

    /**
     * @param morada the morada to set
     */
    public void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * @return the dn
     */
    public LocalDate getDn() {
        return dn;
    }

    /**
     * @param dn the dn to set
     */
    public void setDn(LocalDate dn) {
        this.dn = dn;
    }

    /**
     * Clona a lista de atividades e devolve-a.
     *
     * @return Clone da lista de atividades
     */
    public HashSet getAtividades() {
        return cloneAtividades();
    }

    /**
     * Verifica se o dado email faz parte da lista de amigos.
     *
     * @param mail Email a verificar.
     * @return True se é amigo, false caso contrário.
     */
    public boolean existeAmigo(String mail) {
        return amigos.keySet().contains(mail);
    }

    /**
     * Adiciona um utilizador à lista de amigos.
     *
     * @param amigo Amigo a ser adicionado à lista de amigos.
     * @throws UserNaoExisteException
     */
    public void addAmigo(User amigo) throws UserNaoExisteException {
        if (amigo == null) {
            throw new UserNaoExisteException();
        }
        String email = amigo.getMail();
        amigos.put(email, amigo);
    }

    /**
     * Remove um utilizador da lista de amigos.
     *
     * @param mail Email do amigo a ser removido da lista.
     * @throws AmigoNaoExisteException
     */
    public void removeAmigo(String mail) throws AmigoNaoExisteException {
        if (!amigos.containsKey(mail)) {
            throw new AmigoNaoExisteException();
        }
        amigos.remove(mail);
    }

    /**
     * Remove um utilizador da lista de amigos.
     *
     * @param amigo Amigo a ser removido da lista.
     * @throws AmigoNaoExisteException
     * @throws UserNaoExisteException
     */
    public void removeAmigo(User amigo) throws AmigoNaoExisteException, UserNaoExisteException {
        if (amigo == null) {
            throw new UserNaoExisteException();
        }
        if (!amigos.containsValue(amigo)) {
            throw new AmigoNaoExisteException();
        }
        amigos.remove(mail);
    }

    /**
     * Adiciona um utilizador à lista de pedidos de amizade.
     *
     * @param u Utilizador a adicionar à lista de pedidos de amizade.
     * @throws UserNaoExisteException
     * @throws JaEAmigoException
     */
    public void addPedido(User u) throws UserNaoExisteException, JaEAmigoException {
        if (u == null) {
            throw new UserNaoExisteException();
        }
        if (amigos.containsValue(u)) {
            throw new JaEAmigoException();
        }
        String email = u.getMail();
        pedidosAmigo.put(email, u);
    }

    /**
     * Remove um utilizador da lista de pedidos de amizade e adiciona-o à lista
     * de amigos.
     *
     * @param mail Email do utilizador a mover para a lista de amigos.
     * @throws PedidoNaoExisteException
     */
    public void aceitaPedido(String mail) throws PedidoNaoExisteException, JaEAmigoException {
        if (!pedidosAmigo.containsKey(mail)) {
            throw new PedidoNaoExisteException();
        }
        if (amigos.containsKey(mail)) {
            throw new JaEAmigoException();
        }
        User amigo = pedidosAmigo.get(mail);
        pedidosAmigo.remove(mail);
        amigos.put(mail, amigo);
        try {
            amigo.addAmigo(this);
        } catch (UserNaoExisteException ex) {
            //nunca acontece...
            System.out.println("FATAL ERROR: User.java -> aceitaPedido");
        }
    }
     


    /**
     * Remove um utilizador da lista de pedidos de amizade e adiciona-o à lista
     * de amigos.
     *
     * @param u Utilizador a mover para a lista de amigos.
     * @throws PedidoNaoExisteException
     * @throws JaEAmigoException
     */
    public void aceitaPedido(User u) throws PedidoNaoExisteException, JaEAmigoException {
        if (!pedidosAmigo.containsValue(u)) {
            throw new PedidoNaoExisteException();
        }
        if (amigos.containsValue(u)) {
            throw new JaEAmigoException();
        }
        pedidosAmigo.remove(u.getMail());
        amigos.put(u.getMail(), u);
        try {
            u.addAmigo(this);
        } catch (UserNaoExisteException ex) {
            //nunca acontece...
            System.out.println("FATAL ERROR: User.java -> aceitaPedido");
        }
    }

    /**
     * Remove um utilizador da lista de pedidos de amizade.
     *
     * @param mail E-mail do utilizador a remover da lista de pedidos de
     * amizade.
     * @throws PedidoNaoExisteException
     */
    public void rejeitaPedido(String mail) throws PedidoNaoExisteException {
        if (!pedidosAmigo.containsKey(mail)) {
            throw new PedidoNaoExisteException();
        }
        pedidosAmigo.remove(mail);
    }

    /**
     * Remove um utilizador da lista de pedidos de amizade.
     *
     * @param u User a remover da lista de pedidos de amizade.
     * @throws PedidoNaoExisteException
     */
    public void rejeitaPedido(User u) throws PedidoNaoExisteException {
        if (!pedidosAmigo.containsValue(u)) {
            throw new PedidoNaoExisteException();
        }
        pedidosAmigo.remove(u.getMail());
    }

    /**
     * Gera um HashMap que é um clone do HashMap que guarda a informação dos
     * amigos.
     *
     * @return HashMap clonado.
     */
    public HashMap verAmigos() {
        return cloneAmigos();
    }

    /**
     * Gera um HashMap que é um clone do HashMap que guarda a informação dos
     * pedidos de amizade.
     *
     * @return HashMap clonado.
     */
    public HashMap verPedidosAmizade() {
        return clonePedidos();
    }
   

    /**
     * Testa de um objecto dado, é igual a uma instância de User.
     *
     * @param obj Objeto a comparar com a instância de User.
     * @return true if equals, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof User) {
            User u = (User) obj;
            if (!u.getDn().equals(this.dn)) {
                return false;
            }
            if (!u.getGenero().equals(this.genero)) {
                return false;
            }
            if (!u.getMail().equals(this.mail)) {
                return false;
            }
            if (!u.getMorada().equals(this.morada)) {
                return false;
            }
            if (!u.getNome().equals(this.nome)) {
                return false;
            }
            if (!u.getPw().equals(this.pw)) {
                return false;
            }
            if (!u.verAmigos().equals(this.amigos.keySet())) {
                return false;
            }
            if (!u.verPedidosAmizade().equals(this.pedidosAmigo.keySet())) {
                return false;
            }
            
        } else {
            return false;
        }
        return true;
    }

    /**
     * Clona a lista de amigos.
     *
     * @return Lista de amigos clonada.
     */
    public HashMap cloneAmigos() {
        HashMap<String, User> r = new HashMap<>();
        for (String e : amigos.keySet()) {
            r.put(e, amigos.get(e));
        }
        return r;
    }

    /**
     * Clona a lista de pedidos de amizade.
     *
     * @return Lista de pedidos de amizade clonados.
     */
    public HashMap clonePedidos() {
        HashMap<String, User> r = new HashMap<>();
        for (String e : pedidosAmigo.keySet()) {
            r.put(e, pedidosAmigo.get(e));
        }
        return r;
    }

    /**
     * Clona a lista de atividades.
     *
     * @return Lista de atividades clonadas.
     */
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.mail);
        hash = 89 * hash + Objects.hashCode(this.pw);
        hash = 89 * hash + Objects.hashCode(this.nome);
        hash = 89 * hash + Objects.hashCode(this.genero);
        hash = 89 * hash + Objects.hashCode(this.morada);
        hash = 89 * hash + Objects.hashCode(this.dn);
        hash = 89 * hash + Objects.hashCode(this.amigos);
        hash = 89 * hash + Objects.hashCode(this.pedidosAmigo);
        
        return hash;
    }

    @Override
    public String toString() {
        return "E-Mail: "+this.mail+
                "\nNome: "+this.nome+
                "\nGenero: "+this.genero+
                "\nMorada: "+this.morada+
                "\nData de Nascimento: "+this.dn.toString()+
                "\nAmigos: "+this.amigos.keySet().toString();
                
    }

    @Override
    public User clone() {
        return new User(this);
    }

}
