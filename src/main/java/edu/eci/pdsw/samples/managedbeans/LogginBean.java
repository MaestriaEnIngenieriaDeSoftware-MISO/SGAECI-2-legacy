/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import Security.AsignacionUser_password;
import Security.SHA1;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2090683
 */

@ManagedBean (name= "Loggin")
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

    
    

    public boolean isAutenticacion() throws NoSuchAlgorithmException {
        AsignacionUser_password as =new AsignacionUser_password();
        Hashtable<String, String> contenedor= as.getCorreocontraseña();
        SHA1 s = new SHA1();
        Persona usuario = new Persona();
        boolean valido=false;
        String correo=usuario.getCorreo();
         autenticacion=contenedor.containsKey(username); //valida que se encuentre la llave
        if (autenticacion==true){ // si la llave existe, busca el valor 
            valido = contenedor.containsValue(s.getHash(password));
        }          
        
        return valido;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }
   

}
