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

    ServiciosSAGECI servicios = ServiciosSAGECI.getInstance();
    SolicitudAfiliacion solicitudSelection;

    public ProcesarSolicitudAfiliacionBean() {
        
    }
    
    public List<SolicitudAfiliacion> getSolicitudes() throws ExcepcionServiciosSAGECI{
        return servicios.consultarSolicitudAfiliaciones();
    }
    
    public ServiciosSAGECI getServicios() {
        return servicios;
    }

    public void setServicios(ServiciosSAGECI servicios) {
        this.servicios = servicios;
    }

    public SolicitudAfiliacion getSolicitudSelection() {
        return solicitudSelection;
    }

    public void setSolicitudSelection(SolicitudAfiliacion solicitudSelection) {
        this.solicitudSelection = solicitudSelection;
    }

    
    public void prueba(){
        try {
            System.out.println(servicios.consultarSolicitudAfiliaciones());
        } catch (ExcepcionServiciosSAGECI ex) {
            Logger.getLogger(ProcesarSolicitudAfiliacionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    

}
