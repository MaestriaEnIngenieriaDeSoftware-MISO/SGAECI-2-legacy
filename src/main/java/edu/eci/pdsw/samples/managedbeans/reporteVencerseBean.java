/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.javamail.core.Email;
import edu.eci.pdsw.samples.javamail.core.EmailConfiguration;
import edu.eci.pdsw.samples.javamail.core.EmailSender;
import edu.eci.pdsw.samples.javamail.core.SimpleEmail;
import edu.eci.pdsw.samples.javamail.core.SimpleEmailSender;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;

/**SELECT Persona.Nombre,Persona.DocumentoID,Direccion, Correo 
FROM Persona JOIN Estado_afiliacion ON(Persona.DocumentoID = Estado_afiliacion.DocumentoID)
WHERE DATEDIFF(Fecha_Fin,SysDate())<=30;
 *
 * @author pdswgr2
 */


@ManagedBean (name= "ReporteVencerse")
@SessionScoped

public class reporteVencerseBean implements Serializable{
    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance(); 
    List<Persona> Personas;
    EmailSender sender = new SimpleEmailSender(new EmailConfiguration());
    Email email = null;
    final String from = "5d8dd682c0-c92f3e@inbox.mailtrap.io";
    final String subjectAprobado = "Recordatorio de vencimiento próximo de afiliación..";


    
    public reporteVencerseBean() {
    }
    
    public List<Persona> getSolicitudesAfiliacionVencidas() throws ExcepcionServiciosSAGECI{
        Personas=SAGECI.consultarSolicitudAfiliacionesVencidas();
        return Personas;
    }
    
    
    public void recordatorio() throws ExcepcionServiciosSAGECI{
        for (Persona per : Personas) {
            String letra="a";
            if(per.getGenero().equals("MASCULINO")){
                letra="o";
            }
            String messageAprobado ="Apreciad"+letra+" "+per.getNombre()+" "+per.getApellido()+ " le recordamos que su plazo de afiliación esta próximo"
                    + " a vencerse, por favor realice su pago semestral para seguir disfrutando de nuestros servicios.";
            String toPersona = per.getCorreo_Personal();
            email = new SimpleEmail(from, toPersona, subjectAprobado, messageAprobado);
            try {
                sender.send(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    
}
