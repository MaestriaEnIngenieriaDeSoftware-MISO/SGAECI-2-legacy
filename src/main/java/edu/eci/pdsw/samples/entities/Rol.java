/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import Security.AsignacionUser_password;
import java.util.HashMap;
import java.util.Map;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
/**
 *
 * @author 2090683
 */
public class Rol {
    private String rol;
    private String password;
    private String sal;
    private Map acceso;
  
    public static String usuario = "Afiliado", Admin = "Administrador ";
    
    
    public Rol() {     
    }
    
      /**
     * Constructor de la clase rol
     *
     * @param r
     * @param cont
     * @param s
     */
    public Rol(String r, String cont, String s) {
        this.rol = r;
        this.password = cont;
        this.sal = s;
        acceso=new HashMap<>();
        if (r.equalsIgnoreCase(usuario)){
            acceso.put(SolicitudAfiliacion., SolicitudAfilicion);
        }
       
    }
    /**
     * @return the rol
     */
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    /**
     * @return the contraseña
     */
    public String getPassword() {
        AsignacionUser_password asi=new AsignacionUser_password ();
        asi.getPassword();
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSal() {
        return sal;
    }

    public void setSal(String sal) {
        this.sal = sal;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Rol.usuario = usuario;
    }

    public static String getAdmin() {
        return Admin;
    }

    public static void setAdmin(String Admin) {
        Rol.Admin = Admin;
    }

}

