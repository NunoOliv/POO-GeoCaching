package Data;

import java.time.LocalDate;
import java.util.HashMap;

import Exceptions.*;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class UserList {

    private HashMap<String, User> userL;

    public UserList() {
        userL = new HashMap<>();
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
     * Procura o a instância User relativa ao email dado.
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

}
