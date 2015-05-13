package View;

import Business.Core;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Rafael Antunes
 * @author Nuno Oliveira
 * @author Rui Pereira
 */
public class GeoCachingPOO {

    private static Core core;
    private static Menu menu;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FileInputStream fileIn = null;
        ObjectInputStream in = null;
        try {
            fileIn = new FileInputStream("geocaching.poo");
            in = new ObjectInputStream(fileIn);
            System.out.println("A ler ficheiro \"geocaching.poo\"...");
            core = (Core) in.readObject();
            System.out.println("\nConcluído!\n");
            in.close();
            fileIn.close();
          
        } catch (FileNotFoundException ex) {
            System.out.println("Ficheiro de recuperação de dados não encontrado.");
            core = new Core();
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
            core = new Core();
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
            core = new Core();
        }
            menu = new Menu(core);
            //core.inicialize();
            menu.start();
    }
}
