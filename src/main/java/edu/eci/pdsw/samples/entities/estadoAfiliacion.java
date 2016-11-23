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
public class estadoAfiliacion {
    int DocumentoID;
    Date fechaInicio,fechaFin;
    String Estado;

    public estadoAfiliacion(int DocumentoID, Date fechaInicio, Date fechaFin, String Estado) {
        this.DocumentoID = DocumentoID;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.Estado = Estado;
    }

    public estadoAfiliacion() {
    }
    
    public int getDocumentoID() {
        return DocumentoID;
    }

    public void setDocumentoID(int DocumentoID) {
        this.DocumentoID = DocumentoID;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
}
