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
     *Dado um dia, mes e ano constroí o respetivo LocalDate.
     * @param dia
     * @param mes
     * @param ano
     * @return  LocalDate respetivo.
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

    public void updateName(String name) throws NomeInvalidoException {
        if (name == null) {
            throw new NomeInvalidoException();
        }
        if (name.equals("")) {
            throw new NomeInvalidoException();
        }
        sessao.setNome(name);
    }

    public void updateMorada(String x) throws MoradaInvalidaException {
        if (x == null) {
            throw new MoradaInvalidaException();
        }
        if (x.equals("")) {
            throw new MoradaInvalidaException();
        }
        sessao.setMorada(x);
    }

    public void trocaGenero() {
        String g = sessao.getGenero();
        if (g.equals("Masculino")) {
            sessao.setGenero("Feminino");
        } else {
            sessao.setGenero("Masculino");
        }
    }

    public void updateDN(int dia, int mes, int ano) throws CamposInvalidosException {
        sessao.setDn(buildDate(dia, mes, ano));
    }

    public boolean checkPass(String x) {
        return sessao.checkPass(x);
    }

    public void updatePass(String x) throws PasswordInvalidaException {
        if (x == null) {
            throw new PasswordInvalidaException();
        }
        if (x.equals("")) {
            throw new PasswordInvalidaException();
        }
        sessao.setPw(x);
    }

    public User getInfo() {
        User u = new User(sessao);
        u.setPw("");
        return u;
    }

    public void pedeAmigo(String m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
