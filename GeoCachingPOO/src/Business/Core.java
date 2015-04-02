package Business;

import Data.User;
import Data.UserList;
import Exceptions.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class Core {

    private UserList userL;
    private User sessao;

    public Core() {
        userL = new UserList();
        sessao = null;
    }

    public void inicialize() {
        LocalDate dn1 = LocalDate.of(1994, Month.OCTOBER, 27);
        LocalDate dn2 = LocalDate.of(1994, Month.JUNE, 10);
        LocalDate dn3 = LocalDate.of(1994, Month.MAY, 4);
        try {
            userL.addUser("rafa@mail.com", "123", "Rafael", "Masculino", "Rua da Pera", dn1);
            userL.addUser("nuno@mail.com", "123", "Nuno", "Masculino", "Rua da Laranja", dn2);
            userL.addUser("rui@mail.com", "123", "Rui", "Masculino", "Rua da Maçâ", dn3);
        } catch (EmailJaExisteException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CamposInvalidosException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GeneroInvalidoException ex) {
            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataInvalidaException ex) {
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

    public void checkMail(String mail) throws EmailInvalidoException {
        if (mail == null) {
            throw new EmailInvalidoException();
        }
        if (mail.equals("")) {
            throw new EmailInvalidoException();
        }
        if (!mail.matches("(.+)@(.+)\\.(.+)")) {
            throw new EmailInvalidoException();
        }
    }

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

}
