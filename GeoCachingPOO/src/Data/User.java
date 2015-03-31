package Data;

import java.time.LocalDate;
import java.util.HashMap;

import Exceptions.*;
import java.util.HashSet;
/**
 *
 * @author Rafael
 */
public class User {

    private String mail;
    private String pw;
    private String nome;
    private String genero;
    private String morada;
    private LocalDate dn;
    private HashMap<String, User> amigos;
    private HashMap<String, User> pedidosAmigo; //users q querem ser amigos deste.
    private HashSet<Actividade> ativs;

    protected User(String mail, String pw, String nome, String genero, String morada, LocalDate dn) {
        this.mail=mail;
        this.pw=pw;
        this.nome=nome;
        this.genero=genero;
        this.morada=morada;
        this.dn=dn;
        amigos=new HashMap<>();
        pedidosAmigo=new HashMap<>();
        ativs=new HashSet<>();
    }

    /**
     * @return the mail
     */
    protected String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    protected void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the pw
     */
    protected String getPw() {
        return pw;
    }

    /**
     * @param pw the pw to set
     */
    protected void setPw(String pw) {
        this.pw = pw;
    }

    /**
     * @return the nome
     */
    protected String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    protected void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the genero
     */
    protected String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    protected void setGenero(String genero) {
        this.genero = genero;
    }

    /**
     * @return the morada
     */
    protected String getMorada() {
        return morada;
    }

    /**
     * @param morada the morada to set
     */
    protected void setMorada(String morada) {
        this.morada = morada;
    }

    /**
     * @return the dn
     */
    protected LocalDate getDn() {
        return dn;
    }

    /**
     * @param dn the dn to set
     */
    protected void setDn(LocalDate dn) {
        this.dn = dn;
    }

    protected boolean existeAmigo(String mail) {
        if (amigos.keySet().contains(mail)) {
            return true;
        }
        return false;
    }
    
    protected void addAmigo(User amigo) throws UserNaoExisteException{
        if (amigo==null) throw new UserNaoExisteException();
        String mail=amigo.getMail();
        amigos.put(mail, amigo);
    }
    
    protected void removeAmigo(String mail) throws AmigoNaoExisteException{
        if(!amigos.containsKey(mail)) throw new AmigoNaoExisteException();
        amigos.remove(mail);
    }
    
    protected void removeAmigo(User amigo) throws AmigoNaoExisteException, UserNaoExisteException{
        if(amigo==null)throw new UserNaoExisteException();
        if(!amigos.containsValue(amigo)) throw new AmigoNaoExisteException();
        amigos.remove(mail);
    }

    protected void addPedido(User u) throws UserNaoExisteException, JaEAmigoException{
        if(u==null)throw new UserNaoExisteException();
        if(amigos.containsValue(u))throw new JaEAmigoException();
        String mail=u.getMail();
        pedidosAmigo.put(mail, u);
    }
    
    protected void aceitaPedido(String mail) throws PedidoNaoExisteException{
        if(!pedidosAmigo.containsKey(mail)) throw new PedidoNaoExisteException();
        User amigo=pedidosAmigo.get(mail);
        pedidosAmigo.remove(mail);
        amigos.put(mail, amigo);
    }
    
    protected void aceitaPedido(User u) throws PedidoNaoExisteException{
        if(!pedidosAmigo.containsValue(u)) throw new PedidoNaoExisteException();
        pedidosAmigo.remove(u.getMail());
        amigos.put(u.getMail(), u);
    }
    
    protected void rejeitaPedido(String mail) throws PedidoNaoExisteException{
        if(!pedidosAmigo.containsKey(mail)) throw new PedidoNaoExisteException();
        pedidosAmigo.remove(mail);
    }
    
    protected void rejeitaPedido(User u) throws PedidoNaoExisteException{
        if(!pedidosAmigo.containsValue(u)) throw new PedidoNaoExisteException();
        pedidosAmigo.remove(u.getMail());
    }
    
}
