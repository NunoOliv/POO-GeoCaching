/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import Data.Coords;
import Exceptions.CacheNaoExisteException;
import Exceptions.CamposInvalidosException;
import Exceptions.DataInvalidaException;
import Exceptions.DificuldadeInvalidaException;
import Exceptions.EmailInvalidoException;
import Exceptions.EmailJaExisteException;
import Exceptions.GeneroInvalidoException;
import Exceptions.JaEAmigoException;
import Exceptions.PasswordMissmatchException;
import Exceptions.PedidoInvalidoException;
import Exceptions.PedidoNaoExisteException;
import Exceptions.TipoDeCacheNaoExisteException;
import Exceptions.UserNaoExisteException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nuno Oliveira
 */
public class TestClass {

    private final Core core;
    private final int cont;
    private int cacheCont;

    public TestClass(Core core, int cont) {
        this.core = core;
        this.cont=cont;
        cacheCont = 0;
        init(cont);

    }

    private void init(int cont) {
        addUsers();
        addCaches();
        

    }

    private void addUsers() {
        //add users
        for (int i = 0; i < cont; i++) {
            try {
                core.registar("user" + (i + 1) + "@mail.com", "123", "Utilizador " + (i + 1), "M", "Rua", 1, 1, 2000);
            } catch (CamposInvalidosException | EmailJaExisteException | GeneroInvalidoException | DataInvalidaException | EmailInvalidoException ex) {
                Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //adicionar amigos
        for (int i = 0; i < cont; i++) {
            try {
                core.login("user" + (i + 1) + "@mail.com", "123");

                for (int j = 0; j < cont; j = j + 2) {
                    try {
                        core.pedeAmigo("user" + (j + 1) + "@mail.com");
                    } catch (JaEAmigoException | PedidoInvalidoException ex) {
                        Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (EmailInvalidoException | CamposInvalidosException | UserNaoExisteException | PasswordMissmatchException ex) {
                Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (int j = 0; j < i; j = j + 4) {
                try {
                    core.aceitaAmigo("user" + (j + 1) + "@mail.com");
                } catch (EmailInvalidoException | UserNaoExisteException | PedidoNaoExisteException | JaEAmigoException ex) {
                    Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    private void addCaches() {
        Random randomGenerator = new Random();
        for (int i = 0; i<cont; i++) {
            int rand = randomGenerator.nextInt(3);
            try {
                core.login("user" + (i + 1) + "@mail.com", "123");
            } catch (EmailInvalidoException | CamposInvalidosException | UserNaoExisteException | PasswordMissmatchException ex) {
                Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            //trad cache
            for (int j = 0; j < rand; j++) {
                try {
                    core.addTradCache("cache"+cacheCont, new Coords(12345,12345), "TradCache"+ (i+1), randomGenerator.nextInt(5)+1);
                    cacheCont++;
                } catch (DificuldadeInvalidaException | CacheNaoExisteException | TipoDeCacheNaoExisteException ex) {
                    Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //micro cache
            rand = randomGenerator.nextInt(3);
            for (int j = 0; j < rand; j++) {
                try {
                    core.addMicroCache("cache"+cacheCont, new Coords(12345,12345), "MicroCache"+ (i+1), randomGenerator.nextInt(5)+1);
                    cacheCont++;
                } catch (DificuldadeInvalidaException | CacheNaoExisteException | TipoDeCacheNaoExisteException ex) {
                    Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //multi cache
            rand = randomGenerator.nextInt(3);
            for (int j = 0; j < rand; j++) {
                
                try {
                    HashMap<Integer, Coords> pe = new HashMap<>();
                    pe.put(1,new Coords(12345,12345));
                    core.addmultiCache("cache"+cacheCont, new Coords(12345,12345), "multiCache"+ (i+1), pe, randomGenerator.nextInt(5)+1,randomGenerator.nextInt(3)+1 );
                    
                    
                    
                    cacheCont++;
                } catch (DificuldadeInvalidaException | CacheNaoExisteException | TipoDeCacheNaoExisteException ex) {
                    Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            }
            //cache mistério
            rand = randomGenerator.nextInt(3);
            for (int j = 0; j < rand; j++) {
                try {
                    core.addCacheMisterio("cache"+cacheCont, "Descrição do puzzle", randomGenerator.nextInt(3)+1 ,new Coords(12345,12345), "CacheMistério"+ (i+1), randomGenerator.nextInt(5)+1);
                    cacheCont++;
                } catch (DificuldadeInvalidaException | CacheNaoExisteException | TipoDeCacheNaoExisteException ex) {
                    Logger.getLogger(TestClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }

   

}
