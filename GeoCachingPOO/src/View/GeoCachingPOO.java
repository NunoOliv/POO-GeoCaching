package View;

import Business.Core;


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
        core = new Core();
        menu= new Menu(core);
                
        core.inicialize();
        menu.start();
        
    }

}
