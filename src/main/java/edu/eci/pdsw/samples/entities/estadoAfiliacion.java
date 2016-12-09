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
    private int DocumentoID;
    private Date fechaInicio,fechaFin;
    private String Estado;
    private Egresado e1;
    private Estudiante e2;

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
    
    
    
    
}
