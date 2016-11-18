/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author vivi-
 */
public class AsignacionUser_password {
    
   final Hashtable<String,String> correocontraseña=new Hashtable<String,String>();
   String usuario;
   String password;
   

    public Hashtable<String, String> getCorreocontraseña() {// Llave:usuario Contenido: contraseña encrptada 
        
        return correocontraseña; // 
    }
// mete en una hashtable el usuario asignado como llave y lo relaciona a la contraseña encriptada
    public void setCorreocontraseña(Hashtable<String, String> correocontraseña) throws NoSuchAlgorithmException {
        AsignacionUser_password as= new AsignacionUser_password();
         SHA1 s = new SHA1();
        
        correocontraseña.put(as.getUsuario(),s.getHash(as.getPassword())); // Llave:usuarioasignado 
                                                                            //Contenido:passwordencriptada
        
        
    }
// Asigna un usuario al afiliado,toma el correo como usuario 
    public String getUsuario() {
        Persona usuario = new Persona();
        String correo=usuario.getCorreo_Personal();
        String usuarioAsignado=correo;
        return usuarioAsignado;
    }

   
// Asigna una contraseña al afiliado, toma el valores random + id 
    public String getPassword() {
        Persona user= new Persona();
        Random s=new Random();
        String passAsignado =Integer.toString( s.nextInt(3))+ Integer.toString(user.getDocumentoID());
        return passAsignado;
    }

}
