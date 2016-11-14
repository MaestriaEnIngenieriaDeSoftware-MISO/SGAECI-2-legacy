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



import Security.AsignacionUser_password;
import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import edu.eci.pdsw.samples.javamail.core.*;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;

/**
 *
 * @author hcadavid
 */
@ManagedBean (name= "ProcesarSolicitudAfiliacion")
@SessionScoped
public class ProcesarSolicitudAfiliacionBean implements Serializable{

    ServiciosSAGECI SAGECI=ServiciosSAGECI.getInstance();
    SolicitudAfiliacion solicitudSelection;
    String Comentario;
    AsignacionUser_password user;
    String usuarioAs= user.getUsuario();
    EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
    Email email = null;
    final String from = "5d8dd682c0-c92f3e@inbox.mailtrap.io";
    final String subjectAprobado = "Solicitud de Ingreso AECI: Aprobada";
    final String messageRechazado = "Su solicitud ha sido Rechazada por lo siguiente: "+Comentario;
    final String usuarioasignado = "Su usuario de ingreso asignado es: "+usuarioAs;
    public ProcesarSolicitudAfiliacionBean() {
        
    }
    
    public List<SolicitudAfiliacion> getSolicitudes() throws ExcepcionServiciosSAGECI{
        return SAGECI.consultarSolicitudAfiliaciones();
    }
    
    public ServiciosSAGECI getServicios() {
        return SAGECI;
    }

    public void setServicios(ServiciosSAGECI servicios) {
        this.SAGECI = servicios;
    }

    public SolicitudAfiliacion getSolicitudSelection() {
        return this.solicitudSelection;
    }

    public void setSolicitudSelection(SolicitudAfiliacion solicitudSelection) {
        this.solicitudSelection = solicitudSelection;
    }
    
    public void aceptarSolicitudAfiliacion(ActionEvent actionEvent) throws ExcepcionServiciosSAGECI{
        
        try{
            Egresado e1 = solicitudSelection.getE1();
            Estudiante e2 =solicitudSelection.getE2();
            String messageAprobado = "Su solicitud ha sido Aprobada: "+Comentario;
            String toEgresado = e1.getCorreoPersonal();
            String toEstudiante = e2.getCorreo();
            if (e1.getSemestreGrado()==null){
                solicitudSelection.setEstadoSolicitud("ACEPTADA");
                solicitudSelection.setComentario(Comentario);
                SAGECI.actualizarSolicitudAfliliacion(solicitudSelection);
                email = new SimpleEmail(from, toEstudiante, subjectAprobado, messageAprobado);
            }else{
                System.out.println("entro por parte del egresado");
            }
            try {
                sender.send(email);
                System.out.println("Sent message successfully!");
            } catch (MessagingException e) {
                System.err.println("Message not sent!");
                e.printStackTrace();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }      
    }
    
    public void rechazarSolicitudAfiliacion(ActionEvent actionEvent) throws ExcepcionServiciosSAGECI{
        String subjectRechazado = "Solicitud de Ingreso AECI: Rechazada";
        solicitudSelection.setEstadoSolicitud("RECHAZADA");
        solicitudSelection.setComentario(Comentario);
        SAGECI.actualizarSolicitudAfliliacion(solicitudSelection);
    }
    
    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

}