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



import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.services.ExcepcionServiciosSAGECI;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author hcadavid
 */
@ManagedBean (name= "ProcesarSolicitudAfiliacion")
@SessionScoped
public class ProcesarSolicitudAfiliacionBean implements Serializable{

    ServiciosSAGECI SAGECI=ServiciosSAGECI.getInstance();
    SolicitudAfiliacion solicitudSelection;

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

    
    public void aceptarSolicitudAfiliacion() throws ExcepcionServiciosSAGECI{
    
        Egresado e1 = solicitudSelection.getE1();
        Estudiante e2 =solicitudSelection.getE2();
        System.out.println("entro al metodo aceptarSolicitudAfiliacion ");
        if (e2==null){
            System.out.println("entro por parte del estudiante");
            /*
            el estudiante si es aceptado siempre cumplira los requisitos para la afiliacion.
            */
            solicitudSelection.setEstadoSolicitud("ACEPTADA");
            solicitudSelection.setComentario("Tradar de ingresar un cuadro de texto, "
            + "para que el usuario ingrese, porque acepto la solicitud");
            SAGECI.actualizarSolicitudAfliliacion(solicitudSelection);
        }else{
            System.out.println("entro por parte del egresado");
        }
        System.out.println(SAGECI.consultarSolicitudAfiliacion(solicitudSelection.getSolicitudID()).getEstadoSolicitud());
    } 
    

}