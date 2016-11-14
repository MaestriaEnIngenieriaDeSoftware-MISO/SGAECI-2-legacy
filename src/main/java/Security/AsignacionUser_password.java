/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;

import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import java.util.Hashtable;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author vivi-
 */
public class AsignacionUser_password {
    
    Hashtable<String,String> correocontraseña=new Hashtable<String,String>();
   String usuario;
   String password;

    public Hashtable<String, String> getCorreocontraseña() {
        return correocontraseña;
    }

    public void setCorreocontraseña(Hashtable<String, String> correocontraseña) {
        this.correocontraseña = correocontraseña;
    }

    public String getUsuario() {
        Persona usuario = new Persona();
        String nombre=usuario.getNombre();
        StringTokenizer st = new StringTokenizer(nombre, " ");
        Random s=new Random();
        String usuarioAsignado=st.nextToken()+s.nextInt(3);
        return usuarioAsignado;
    }

    /*public void setUsuario(String usuario) {
        this.usuario = usuario;
    }*/

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
