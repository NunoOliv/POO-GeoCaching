package Data;

import java.time.LocalDate;
import java.util.HashMap;

import Exceptions.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class UserList implements Serializable {

    private HashMap<String, User> userL;

    /**
     *
     */
    public UserList() {
        userL = new HashMap<>();
    }

    /**
     *
     * @param ul
     */
    public UserList(UserList ul) {
        userL = ul.cloneUsers();
    }

    
    /**
     *
     * @param userL
     */
    public void setUserList(HashMap<String, User> userL) {
        this.userL = userL;
    }

    /**
     * Adiciona um novo utilizador à lista.
     *
     * @param mail Email do novo utilizador.
     * @param pw Password do novo utilizador.
     * @param nome Nome do novo utilizador.
     * @param genero Género do novo utilizador.
     * @param morada Morada do novo utilizador.
     * @param dn Data de nascimento do novo utilizador.
     * @throws EmailJaExisteException
     * @throws CamposInvalidosException
     * @throws GeneroInvalidoException
     * @throws DataInvalidaException
     */
    public void addUser(String mail, String pw, String nome, String genero, String morada, LocalDate dn) throws EmailJaExisteException, CamposInvalidosException, GeneroInvalidoException, DataInvalidaException {
        if (mail == null || pw == null || nome == null || genero == null || morada == null || dn == null) {
            throw new CamposInvalidosException();
        }
        if (mail.equals("") || pw.equals("") || nome.equals("") || genero.equals("") || morada.equals("")) {
            throw new CamposInvalidosException();
        }
        if (userL.containsKey(mail)) {
            throw new EmailJaExisteException();
        }
        if (!genero.equals("Masculino") && !genero.equals("Feminino")) {
            throw new GeneroInvalidoException();
        }
        if (dn.isAfter(LocalDate.now())) {
            throw new DataInvalidaException();
        }
        User u = new User(mail, pw, nome, genero, morada, dn);
        userL.put(mail, u);
    }

    /**
     * Adiciona um novo utilizador à lista.
     *
     * @param u Utilizador a adicionar.
     * @throws CamposInvalidosException
     * @throws EmailJaExisteException
     * @throws GeneroInvalidoException
     * @throws DataInvalidaException
     */
    public void addUser(User u) throws CamposInvalidosException, EmailJaExisteException, GeneroInvalidoException, DataInvalidaException {
        if (u.getMail() == null || u.getNome() == null || u.getGenero() == null || u.getMorada() == null || u.getDn() == null) {
            throw new CamposInvalidosException();
        }
        if (u.getMail().equals("") || u.checkPass("") || u.getNome().equals("") || u.getGenero().equals("") || u.getMorada().equals("")) {
            throw new CamposInvalidosException();
        }
        if (userL.containsKey(u.getMail())) {
            throw new EmailJaExisteException();
        }
        if (!u.getGenero().equals("Masculino") && !u.getGenero().equals("Feminino")) {
            throw new GeneroInvalidoException();
        }
        if (u.getDn().isAfter(LocalDate.now())) {
            throw new DataInvalidaException();
        }
        userL.put(u.getMail(), u);
    }

    /**
     * Procura o utilizador correspondente a um email dado.
     *
     * @param mail Email do utilizador que se procura.
     * @return Utilizador correspondente ao email.
     * @throws EmailInvalidoException
     * @throws UserNaoExisteException
     */
    public User getUser(String mail) throws EmailInvalidoException, UserNaoExisteException {
        if (mail == null) {
            throw new EmailInvalidoException();
        }
        if (mail.equals("")) {
            throw new EmailInvalidoException();
        }
        if (!userL.containsKey(mail)) {
            throw new UserNaoExisteException();
        }
        return userL.get(mail);
    }

    /**
     * Verifica se existe um utilizador com o dado email.
     *
     * @param mail Email a verificar.
     * @return True se existe, False caso contrário.
     * @throws EmailInvalidoException
     */
    public boolean existeUser(String mail) throws EmailInvalidoException {
        if (mail == null) {
            throw new EmailInvalidoException();
        }
        if (mail.equals("")) {
            throw new EmailInvalidoException();
        }
        return userL.containsKey(mail);
    }

    /**
     * Verifica se a password e o email dados correspondem.
     *
     * @param mail Email a verificar.
     * @param pass Password a verificar.
     * @return Verdadeiro se corresponderem, falso caso contrário.
     * @throws EmailInvalidoException
     * @throws CamposInvalidosException
     * @throws UserNaoExisteException
     */
    public boolean checkPass(String mail, String pass) throws EmailInvalidoException, CamposInvalidosException, UserNaoExisteException {
        if (mail == null) {
            throw new EmailInvalidoException();
        }
        if (mail.equals("")) {
            throw new EmailInvalidoException();
        }
        if (pass == null) {
            throw new CamposInvalidosException();
        }
        if (pass.equals("")) {
            throw new CamposInvalidosException();
        }
        if (!userL.containsKey(mail)) {
            throw new UserNaoExisteException();
        }
        if (userL.get(mail).checkPass(pass)) {
            return true;
        }
        return false;
    }

    /**
     * Remove um utilizador do sistema.
     *
     * @param mail Email do utilizador a remover.
     * @param pass Password do utilizador a remover.
     * @throws EmailInvalidoException
     * @throws CamposInvalidosException
     * @throws UserNaoExisteException
     * @throws PasswordMissmatchException
     */
    public void removeUser(String mail, String pass) throws EmailInvalidoException, CamposInvalidosException, UserNaoExisteException, PasswordMissmatchException {
        if (checkPass(mail, pass)) {
            userL.remove(mail);
        } else {
            throw new PasswordMissmatchException();
        }
    }

    /**
     *
     * @return
     */
    public HashMap<String, User> cloneUsers() {
        HashMap<String, User> r = new HashMap<>();
        User u;
        for (String s : this.userL.keySet()) {
            try {
                u = this.getUser(s);
                r.put(s, u);
            } catch (EmailInvalidoException | UserNaoExisteException ex) {
                //nunca acontece...
            }
        }
        return r;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserList clone() {
        return new UserList(this);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.userL);
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
        final UserList other = (UserList) obj;
        if (!Objects.equals(this.userL, other.cloneUsers())) {
            return false;
        }
        return true;
    }

    

}
