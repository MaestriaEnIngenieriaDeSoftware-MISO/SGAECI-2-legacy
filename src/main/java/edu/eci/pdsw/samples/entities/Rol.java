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
    private String user;
    private Map acceso;
  
    public static String Afiliado = "Afiliado", Administrador = "Administrador ";
    
    
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
        this.user = s;
        //acceso=new HashMap<>();
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
        password =asi.getPassword();
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSal() {
        AsignacionUser_password asi=new AsignacionUser_password ();
        user=asi.getUsuario();
        return user;
    }

    public void setSal(String sal) {
        this.user = sal;
    }

    public static String getAfiliado() {
        return Afiliado;
    }

    public static void setAfiliado (String usuario) {
        Rol.Afiliado = usuario;
    }

    public static String getAdmininistrador() {
        return Administrador;
    }

    public static void setAdministrador(String Admin) {
        Rol.Administrador  = Admin;
    }

}

