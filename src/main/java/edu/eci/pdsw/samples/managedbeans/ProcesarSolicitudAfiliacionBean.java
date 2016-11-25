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


import Security.SHA1;
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

    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();
    SolicitudAfiliacion solicitudSelection;
    String Comentario;
    EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
    Email email = null;
    private SHA1 sh;
    final String from = "5d8dd682c0-c92f3e@inbox.mailtrap.io";
    final String subjectAprobado = "Solicitud de Ingreso AECI: Aprobada";
    final String messageRechazado = "Su solicitud ha sido Rechazada por lo siguiente: "+Comentario;

    public ProcesarSolicitudAfiliacionBean() {
        
    }
    
    public List<SolicitudAfiliacion> getSolicitudes() throws ExcepcionServiciosSAGECI{
        List<SolicitudAfiliacion> temp =SAGECI.consultarSolicitudAfiliaciones();
        for(SolicitudAfiliacion s :temp){
            if(s.getE1().getSemestreGrado()==null){
                s.setTipoSol("Estudiante");
            }else{
                s.setTipoSol("Egresado");
            }
        }
        return temp;
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
            String toEgresado = e1.getCorreo_Personal();
            String toEstudiante = e2.getCorreo_Personal();            
            solicitudSelection.setEstadoSolicitud("ACEPTADA");
            solicitudSelection.setComentario(Comentario);
            SAGECI.actualizarSolicitudAfliliacion(solicitudSelection);
            System.out.println("actualizo solicitud con exito");
            String shacontrasena;
            if (e1==null){
                System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                System.out.println("antes del hash");
                shacontrasena = sh.generateHash(Integer.toString(e2.getDocumentoID()));
                System.out.println("despues del hash");
                System.out.println(shacontrasena);
                email = new SimpleEmail(from, toEstudiante, subjectAprobado+", Su Usuario y la contraseña es su Documento de identidad" , messageAprobado);
                System.out.println("despues del crear el email");
                System.out.println(e2.getDocumentoID());
                SAGECI.agregarRolPersona(e2.getDocumentoID(),3,shacontrasena);
                System.out.println("antes de enviar email");
                sender.send(email);
                System.out.println("despues de enviar email");
            }else{
                System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
                System.out.println("antes del hash");
                shacontrasena = sh.generateHash(Integer.toString(e1.getDocumentoID()));
                System.out.println("despues del hash");
                System.out.println(shacontrasena);
                email = new SimpleEmail(from, toEgresado, subjectAprobado+", Su Usuario y la contraseña es su Documento de identidad" , messageAprobado);
                System.out.println("despues del crear el email");
                System.out.println(e1.getDocumentoID());
                SAGECI.agregarRolPersona(e1.getDocumentoID(),2,shacontrasena);
                System.out.println("antes de enviar email");
                sender.send(email);
                System.out.println("despues de enviar email");
            }
        }catch(MessagingException e){System.out.println("email mal");}
        catch(Exception e){System.out.println("otro error");}
    }
    
    public void rechazarSolicitudAfiliacion(ActionEvent actionEvent) throws ExcepcionServiciosSAGECI{
        try{
            Egresado e1 = solicitudSelection.getE1();
            Estudiante e2 =solicitudSelection.getE2();
            String messageAprobado = "Su solicitud ha sido Rechazada: "+Comentario;
            String toEgresado = e1.getCorreo_Personal();
            String toEstudiante = e2.getCorreo_Personal();
            String subjectRechazado = "Solicitud de Ingreso AECI: Rechazada";
            solicitudSelection.setEstadoSolicitud("RECHAZADA");
            solicitudSelection.setComentario(Comentario);
            SAGECI.actualizarSolicitudAfliliacion(solicitudSelection);
            if (e1==null){
                email = new SimpleEmail(from, toEstudiante, subjectRechazado, messageAprobado);
                sender.send(email);
            }else{
                email = new SimpleEmail(from, toEgresado, subjectRechazado, messageAprobado);
                sender.send(email);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

}
