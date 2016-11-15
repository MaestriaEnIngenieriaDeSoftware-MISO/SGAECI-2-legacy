/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import Security.AsignacionUser_password;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.Serializable;
import java.util.Hashtable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2090683
 */

@ManagedBean (name= "LogginBean")
@SessionScoped
public class LogginBean implements Serializable{
   private String password;
   public  String username;
   private boolean autenticacion;
   
   
   
  

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    

    public boolean isAutenticacion() {
        AsignacionUser_password as =new AsignacionUser_password();
        Hashtable<String, String> contenedor= as.getCorreocontraseña();
        Persona usuario = new Persona();
        String correo=usuario.getCorreo();
        autenticacion=contenedor.get(username).equals(contenedor.get(as.getUsuario())); //compara el username ingresado en la xhtml  con el user asignado guardado en la hashtable 
          
        return autenticacion;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }
   

}
