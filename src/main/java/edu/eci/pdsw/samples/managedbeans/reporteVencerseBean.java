/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**SELECT Persona.Nombre,Persona.DocumentoID,Direccion, Correo 
FROM Persona JOIN Estado_afiliacion ON(Persona.DocumentoID = Estado_afiliacion.DocumentoID)
WHERE DATEDIFF(Fecha_Fin,SysDate())<=30;
 *
 * @author pdswgr2
 */


@ManagedBean (name= "ReporteVencerse")
@SessionScoped

public class reporteVencerseBean {
    
    
    
}
