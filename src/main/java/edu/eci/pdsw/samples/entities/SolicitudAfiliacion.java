/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.util.Date;

/**
 *
 * @author 2106796
 */
public class SolicitudAfiliacion {
    private int solicitudID;
    private Date fechaSolicitud;
    private String estadoSolicitud,comentario,tipoSol;
    private Egresado e1;
    private Estudiante e2;
    
    public SolicitudAfiliacion(int solicitudID, Date fechaSolicitud, String estadoSolicitud, String comentario, Egresado e1, Estudiante e2) {
        this.solicitudID = solicitudID;
        this.fechaSolicitud = fechaSolicitud;
        this.estadoSolicitud = estadoSolicitud;
        this.comentario = comentario;
        this.e1 = e1;
        this.e2 = e2;
        
        
    }

    public SolicitudAfiliacion(){
        tipoSol = "Estudiante";
        if(this.e1!=null){
            tipoSol = "Egresado";
        }
    }
    
    public int getSolicitudID() {
        return solicitudID;
    }

    public void setSolicitudID(int solicitudID) {
        this.solicitudID = solicitudID;
    }


    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstadoSolicitud() {
        return estadoSolicitud;
    }

    public void setEstadoSolicitud(String estadoSolicitud) {
        this.estadoSolicitud = estadoSolicitud;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Egresado getE1() {
        return e1;
    }

    public void setE1(Egresado e1) {
        this.e1 = e1;
    }

    public Estudiante getE2() {
        return e2;
    }

    public void setE2(Estudiante e2) {
        this.e2 = e2;
    }
    
    public String getTipoSol() {
        return tipoSol;
    }

    public void setTipoSol(String tipoSol) {
        this.tipoSol = tipoSol;
    }
}
