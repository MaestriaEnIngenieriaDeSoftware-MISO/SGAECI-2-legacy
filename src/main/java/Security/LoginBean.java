/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Security;



import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vivi-
 */
@ManagedBean(name = "beanLogin")
@SessionScoped
public class LoginBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(LoginBean.class); 
    private String password;
    public static String username;
    private Boolean rememberMe;

    public LoginBean() {
        
    }
    
    //Returns the currently accessible Subject available to the calling code depending on runtime environment.
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }
    
    public static String generateHash (String password){
        DefaultHashService hashService= new DefaultHashService();
        hashService.setHashIterations(2000);
        hashService.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        hashService.setPrivateSalt(new SimpleByteSource("myprivatesalt")); 
        hashService.setGeneratePublicSalt(true);
        DefaultPasswordService passwordService = new DefaultPasswordService();
        passwordService.setHashService(hashService);
        String encryptedPassword = passwordService.encryptPassword(password);
        
        return encryptedPassword;
    }
    
     /**
     * Try and authenticate the user
     */
    public void doLogin() {
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(getUsername(), getPassword(), getRememberMe());
        try {
            subject.login(token);
            if (subject.hasRole("admin")) {               
                FacesContext.getCurrentInstance().getExternalContext().redirect("ProcesarSolicitudesAfiliacion.xhtml");
            }
            else if(subject.hasRole("afiliado")){
                FacesContext.getCurrentInstance().getExternalContext().redirect("Beneficios.xhtml");
            }
            else {
                FacesContext.getCurrentInstance().getExternalContext().redirect("Registro.xhtml");
            }
        }
        catch (UnknownAccountException ex) {
            facesError("El usuario no se encuentra registrado. Por favor, verifique los datos");
            Loggin.error(ex);
        }
        catch (IncorrectCredentialsException ex) {
            facesError("Datos erróneos. Por favor, inténtelo otra vez.");
            Loggin.error(ex);
        }
        catch (LockedAccountException ex) {
            facesError("Su cuenta ha sido bloqueada. Por favor, inténtelo más tarde");
             Loggin.error(ex);
        }
        catch (AuthenticationException | IOException ex) {
            facesError("Error inesperado: " + ex.getMessage());
            Loggin.error(ex);
        }
        finally {
            token.clear();
        }
    }
/**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
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

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean lembrar) {
        this.rememberMe = lembrar;
    }
}
