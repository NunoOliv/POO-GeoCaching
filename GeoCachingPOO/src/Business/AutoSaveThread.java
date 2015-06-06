/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;

/**
 *
 * @author Rafael
 */
public class AutoSaveThread implements Runnable {

    private final Core core;

    /**
     *
     * @param core
     */
    public AutoSaveThread(Core core) {
        this.core = core;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(5 * 60 * 1000);//2 min * 60 segs * 1000 milisegundos.
            } catch (InterruptedException ex) {
            }
            guardar();
        }
    }

    private void guardar() {
        String ficheiro = "geocaching.poo";
        System.out.println("A auto-gravar dados...");
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(ficheiro);
            try (ObjectOutputStream outF = new ObjectOutputStream(fileOut)) {
                outF.writeObject(core);
            }
            fileOut.close();
            System.out.println("Dados auto-guardados em: " + ficheiro + "\n\n");
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
    }

}
