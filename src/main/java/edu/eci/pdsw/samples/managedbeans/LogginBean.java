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
 * @author 2090683
 */
@ManagedBean(name = "Loggin")
@SessionScoped
public class LogginBean implements Serializable {

    private static final Logger log = LoggerFactory.getLogger(LogginBean.class);

    private String password;
    public String username;
    private boolean autenticacion;
    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();

    public LogginBean() {

    }

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getAutenticacion());
        try {
            subject.login(token);
            if (subject.hasRole("Administrador")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Admin/ProcesarSolicitudesAfiliacion.xhtml");
            }else if (subject.hasRole("Egresado")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Egresado/EgrUsuario.xhtml");
            }else if (subject.hasRole("Estudiante")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Estudiante/EstUsuario.xhtml"); 
            } else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Registro.xhtml");
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String senha) {
        this.password = senha;
    }

    public boolean getAutenticacion() {
        return autenticacion;
    }

    public void setAutenticacion(boolean autenticacion) {
        this.autenticacion = autenticacion;
    }

  


}
