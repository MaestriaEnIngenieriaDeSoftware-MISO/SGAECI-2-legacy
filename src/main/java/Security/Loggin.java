/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author vivi-
 */
class Loggin {
    public static String usuario = "afiliado";
     
      /**
     * Envia los errores a un archivo .log
     *
     * @param e , excepcion que se presenta en la ejecucion del programa
     */
     public static void error(Exception e) {
        try {
            Logger logger = Logger.getLogger(usuario);
            FileHandler file = new FileHandler(usuario + ".log", true);
            file.setFormatter(new SimpleFormatter());
            logger.addHandler(file);
            logger.log(Level.SEVERE, e.toString(), e);
            file.close();
        } catch (Exception oe) {
            oe.printStackTrace();
        }
    }

}

