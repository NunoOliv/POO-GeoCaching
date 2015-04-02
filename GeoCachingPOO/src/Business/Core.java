package Business;

import Data.User;
import Data.UserList;
import Exceptions.CamposInvalidosException;
import Exceptions.DataInvalidaException;
import Exceptions.EmailInvalidoException;
import Exceptions.EmailJaExisteException;
import Exceptions.GeneroInvalidoException;
import Exceptions.PasswordMissmatchException;
import Exceptions.UserNaoExisteException;
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
    
    public Core(){
        userL = new UserList();
        sessao=null;
    }
    
    public void inicialize(){
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
    
    public void login(String mail,String pass) throws EmailInvalidoException, CamposInvalidosException, UserNaoExisteException, PasswordMissmatchException{
        userL.existeUser(mail);
        if(userL.checkPass(mail, pass)){
            sessao=userL.getUser(mail);
        }else{
            throw new PasswordMissmatchException();
        }
    }
}
