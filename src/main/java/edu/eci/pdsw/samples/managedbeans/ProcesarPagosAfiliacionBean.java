/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.managedbeans;


import edu.eci.pdsw.samples.Security.SHA1;
import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import edu.eci.pdsw.samples.javamail.core.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author hcadavid
 */
@ManagedBean (name= "ProcesarPagosAfiliacion")
@SessionScoped
public class ProcesarPagosAfiliacionBean implements Serializable{

    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();
    PagoAfiliacion pagoSelection;
    EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
    Email email = null;
    Egresado e;
    private StreamedContent i;
    private List<PagoAfiliacion> pagosafiliado;
    private int docid=0;
    private boolean b=true;
    private String codigobanco;
    final String from = "5d8dd682c0-c92f3e@inbox.mailtrap.io";
    final String subject = "Pago de Afiliacion/Renovacion";
    final String messageRechazado = "Su Pago de Afiliacion/Renovacion ha sido Rechazado. por favor contactese directamente con nuestra asociacion para resolver su problema. ";
    final String messageAprovado = "Su pago fue registrado exitosamente en el sistema.";
    
    public ProcesarPagosAfiliacionBean() {
        
    }
    
    public void getpagosAfiliadoEspecifico() throws ExcepcionServiciosSAGECI{
        pagosafiliado = getPagosAfiliacionEspecifico();
    }

    public List<PagoAfiliacion> getPagosafiliado() {
        return pagosafiliado;
    }

    public void setPagosafiliado(List<PagoAfiliacion> pagosafiliado) {
        this.pagosafiliado = pagosafiliado;
    }
    
    public List<PagoAfiliacion> getPagosAfiliacionRegsitrados() throws ExcepcionServiciosSAGECI{
        List<PagoAfiliacion> temp =SAGECI.consultarPagosAfiliacionesRegistrados();
        return temp;
    }
    
    
     public List<PagoAfiliacion> getPagosAfiliacion() throws ExcepcionServiciosSAGECI{
        List<PagoAfiliacion> temp =SAGECI.consultarPagosAfiliaciones();
        return temp;
    }
    
    
    public List<PagoAfiliacion> getPagosAfiliacionNoTramitados() throws ExcepcionServiciosSAGECI{
        List<PagoAfiliacion> temp =SAGECI.consultarPagosAfiliacionesNoTramitados();
        return temp;
    }
    
     public List<PagoAfiliacion> getPagosAfiliacionEspecifico() throws ExcepcionServiciosSAGECI{
        List<PagoAfiliacion> temp =SAGECI.consultarPagosAfiliacionesEspecifico(docid);
        return temp;
    }

    public PagoAfiliacion getPagoSelection() {
        return pagoSelection;
    }

    public void setPagoSelection(PagoAfiliacion pagoSelection) {
        this.pagoSelection = pagoSelection;
    }

    
    public Egresado getE() {
        return e;
    }

    public void setE(Egresado e) {
        this.e = e;
    }
    
      public void showMessage(boolean m) {
        FacesMessage message;
        if (m) {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Correcto", "El pago fue aceptado correctamente.");
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Incorrecto", "El pago se encuentra Resgistrado o Hubo un error inesperado.");
        }
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    
    public void aceptarPagoAfiliacion(ActionEvent actionEvent) throws ExcepcionServiciosSAGECI{
        try{
            if(pagoSelection.getEstado().equals("No Tramitado")){
                e = pagoSelection.getE1();         
                pagoSelection.setEstado("Registrado");
                SAGECI.actualizarPagoAfliliacion(pagoSelection);
                String toEgresado = e.getCorreo_Personal();
                email = new SimpleEmail(from, toEgresado, subject , messageAprovado);
                sender.send(email);
            }
            else{
                showMessage(false);
            }
           
            
        }catch(Exception e){
            showMessage(false);b=false;
        }
        if(b){showMessage(true);}
    }
    
    public void rechazarPagoAfiliacion(ActionEvent actionEvent) throws ExcepcionServiciosSAGECI{ 
        try{
            if(pagoSelection.getEstado().equals("No tramitado")){
                e = pagoSelection.getE1();        
                SAGECI.BorrarPagoAfliliacion(pagoSelection);
                String toEgresado = e.getCorreo_Personal();
                email = new SimpleEmail(from, toEgresado, subject , messageRechazado);
                sender.send(email);
            }
            else{
                showMessage(false);
            }
            
        }catch(Exception e){
            showMessage(false);b=false;
        }
        if(b){showMessage(true);}
    }

    public String getCodigobanco() {
        return codigobanco;
    }

    public void setCodigobanco(String codigobanco) {
        this.codigobanco = codigobanco;
    }

    public int getDocid() {
        return docid;
    }

    public void setDocid(int docid) {
        this.docid = docid;
    }
    
    public void byteToImage() throws IOException {
        this.i= new DefaultStreamedContent(new ByteArrayInputStream(pagoSelection.getImg()));
    }
  

    public StreamedContent getI() {
        return i;
    }
    
    public void setI(StreamedContent i) {
        this.i = i;
}

    
}
