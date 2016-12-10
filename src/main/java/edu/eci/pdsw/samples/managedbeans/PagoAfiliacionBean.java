/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import static edu.eci.pdsw.samples.managedbeans.UsuarioBean.getManagedBean;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;

/**
 *
 * @author FamiliaRamirez
 */
@ManagedBean(name = "GenerarPago")
@SessionScoped
public class PagoAfiliacionBean implements Serializable {
    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();
    private UploadedFile file;
    private byte[] img;
    private Date fechaConsignacion;
     private boolean b=true;
    
     public void showMessage(boolean m) {
        FacesMessage message;
        if (m) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El pago fue enviado correctamente.");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrecto", "El pago No fue enviado hubo un error inesperado.");
        }
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    public UploadedFile getFile() {
        return file;
    }

    public Date getFechaConsignacion() {
        return fechaConsignacion;
    }

    public void setFechaConsignacion(Date fechaConsignacion) {
        this.fechaConsignacion = fechaConsignacion;
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        file = event.getFile();
        img = IOUtils.toByteArray(file.getInputstream());
    }

    public void aceptarEnvio() throws IOException {
        LogginBean bean = (LogginBean) getManagedBean("Loggin");
        PagoAfiliacion pg=null;
        List<PagoAfiliacion> temp= null;
        try {
            temp=SAGECI.consultarPagosAfiliacionesEspecifico(Integer.parseInt(bean.username));
        } catch (ExcepcionServiciosSAGECI ex) {
            Logger.getLogger(PagoAfiliacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(temp!=null){
            if(temp.size()==0){pg = new PagoAfiliacion(0, 155000,Integer.parseInt(bean.username), new Date(new java.util.Date().getTime()),fechaConsignacion,"No Tramitado", img,"Afiliacion");}
            else{pg = new PagoAfiliacion(0, 155000,Integer.parseInt(bean.username), new Date(new java.util.Date().getTime()),fechaConsignacion,"No Tramitado", img,"Renovacion");}
        }
        try {
            if(pg!=null){SAGECI.agregarPagoAfliliacion(pg);}
        } catch (ExcepcionServiciosSAGECI ex) {
            Logger.getLogger(PagoAfiliacionBean.class.getName()).log(Level.SEVERE, null, ex);
            showMessage(false);b=false;
        }
        if(b){showMessage(true);}
        

    }
    
    public static Object getManagedBean(final String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();

        Object bean;
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);

        }
        return bean;
    }
    
    

}
