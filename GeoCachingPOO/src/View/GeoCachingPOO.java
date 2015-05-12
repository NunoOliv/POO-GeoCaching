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
            core = (Core) in.readObject();
            in.close();
            fileIn.close();
          
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
            core = new Core();
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException: " + ex.getMessage());
        }
            menu = new Menu(core);
            //core.inicialize();
            menu.start();
    }
}
