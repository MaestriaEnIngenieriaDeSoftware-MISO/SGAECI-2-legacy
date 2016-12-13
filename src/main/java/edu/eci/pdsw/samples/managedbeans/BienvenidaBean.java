/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import edu.eci.pdsw.samples.entities.imagen;
import edu.eci.pdsw.samples.javamail.core.Email;
import edu.eci.pdsw.samples.javamail.core.EmailConfiguration;
import edu.eci.pdsw.samples.javamail.core.EmailSender;
import edu.eci.pdsw.samples.javamail.core.SimpleEmail;
import edu.eci.pdsw.samples.javamail.core.SimpleEmailSender;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author 2107713
 */
@ManagedBean (name="Bienvenida")
@SessionScoped
public class BienvenidaBean implements Serializable {
     
    private List<imagen> images;
    EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
    Email emailsend = null;
    private String nombre="",email="",asunto="",mensaje="";
     
    public BienvenidaBean() {
        images = new ArrayList<>();
        images.add(new imagen("nature1.jpg", "Tech Movil"));
        images.add(new imagen("nature2.jpg", "Centro Seguros"));
        images.add(new imagen("nature3.jpg", "Inaseg LTDA"));
        images.add(new imagen("nature4.jpg", "Education First"));
        images.add(new imagen("nature5.jpg", "Escuela Colombiana de Ingeniería Julio Garavito"));
        images.add(new imagen("nature6.jpg", "ColSanitas"));
        images.add(new imagen("nature7.jpg", "UltraBox"));
        images.add(new imagen("nature8.jpg", "Casa Editorial EL TIEMPO"));
        images.add(new imagen("nature9.png", "Passport Language Centers"));
        images.add(new imagen("nature10.png", "E-vocs"));
        images.add(new imagen("nature11.png", "Autoniza Chevrolet"));
        images.add(new imagen("nature12.png", "EUDE Business School"));
        images.add(new imagen("nature13.png", "Magna Green Group"));
        images.add(new imagen("nature14.png", "LEGIS"));
        images.add(new imagen("nature11.png", "Autoniza Chevrolet"));
        
    }
 
    public List<imagen> getImages() {
        return images;
    }

    public void setImages(List<imagen> images) {
        this.images = images;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    

    
    
    public void showMessage(boolean m) {
        FacesMessage message;
        if (m) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "Se acaba de enviar su peticion.");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrecto", "Hubo un error, verifique nuevamente los campos.");
        }
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public void enviarMensaje(){
        System.out.println(nombre+"  "+email+"   "+asunto+"   "+mensaje);
        if(!nombre.equals("") && !email.equals("") && !asunto.equals("") && !mensaje.equals("")){
            emailsend = new SimpleEmail("5d8dd682c0-c92f3e@inbox.mailtrap.io", email, asunto , mensaje);
            try {
                sender.send(emailsend);
                nombre="";email="";asunto="";mensaje="";
                System.out.println("enrteo");
                showMessage(true);
            } catch (MessagingException ex) {
                showMessage(false);
                System.out.println("enrteo3");
                Logger.getLogger(BienvenidaBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            showMessage(false);
            System.out.println("enrteo2");
        }
    }
    
}