/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;


import Security.Registro;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.File;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.util.SimpleByteSource;


/**
 *
 * @author 2106913
 */
@ManagedBean(name = "Loggin")
@SessionScoped
public class LogginBean implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(LogginBean.class);

    private String password;
    public String username;
    private boolean autenticacion=false;
    private String tipo;
    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();

    public LogginBean() {

    }
    
     public static String generateHash(String password) {
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashIterations(500000); // 500000
        hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);

        // Same salt as in shiro.ini, but NOT base64-encoded!!
        hashService.setPrivateSalt(new SimpleByteSource("myprivatesalt"));
        hashService.setGeneratePublicSalt(true);

        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);
        String encryptedPassword = passwordService.encryptPassword(password);
        System.out.println(encryptedPassword);
        return encryptedPassword;

    }
    
    
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public void doLogin() {
        System.out.println("Entro");
        System.out.println("user "+username);
        System.out.println("pass "+password);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), isAutenticacion());
        try {
            subject.login(token);
            if (subject.hasRole("Administrador")) {
                this.tipo="Administrador";
                FacesContext.getCurrentInstance().getExternalContext().redirect("Admin/index.xhtml");
            }else if (subject.hasRole("Egresado")) {
                this.tipo="Egresado";
                FacesContext.getCurrentInstance().getExternalContext().redirect("Egresado/index.xhtml");
            }else if (subject.hasRole("Estudiante")) {
                this.tipo="Estudiante";
                FacesContext.getCurrentInstance().getExternalContext().redirect("Estudiante/index.xhtml"); 
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("unauthorized.xhtml");
            }
        }
    
    catch (UnknownAccountException ex){
        facesError("El usuario no se encuentra registrado. Por favor, verifique los datos");
        Registro.anotar(ex);

    }
    catch (IncorrectCredentialsException ex) {
            facesError("Datos erróneos. Por favor, inténtelo otra vez.");
        Registro.anotar(ex);
    }
    catch (AuthenticationException | IOException ex) {
            facesError("Error inesperado: " + ex.getMessage());
            Registro.anotar(ex);
        }
    finally {
        token.clear();
        }
    }
    
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

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
        return autenticacion;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

  


}
