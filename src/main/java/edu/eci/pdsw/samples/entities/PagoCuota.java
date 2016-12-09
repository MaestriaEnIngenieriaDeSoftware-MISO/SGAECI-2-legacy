/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.util.Date;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FamiliaRamirez
 */
public class PagoCuota {
    private String estado;
    private int pagoID,valor_Pago,documentoID;
    private Date fecha_Cobro,fecha_Pago;
    private UploadedFile file;

    public PagoCuota(String estado, int pagoID, int valor_Pago, int documentoID, Date fecha_Cobro, Date fecha_Pago, UploadedFile file) {
        this.estado = estado;
        this.pagoID = pagoID;
        this.valor_Pago = valor_Pago;
        this.documentoID = documentoID;
        this.fecha_Cobro = fecha_Cobro;
        this.fecha_Pago = fecha_Pago;
        this.file = file;
    }
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getPagoID() {
        return pagoID;
    }

    public void setPagoID(int pagoID) {
        this.pagoID = pagoID;
    }

    public int getValor_Pago() {
        return valor_Pago;
    }

    public void setValor_Pago(int valor_Pago) {
        this.valor_Pago = valor_Pago;
    }

    public int getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(int documentoID) {
        this.documentoID = documentoID;
    }

    public Date getFecha_Cobro() {
        return fecha_Cobro;
    }

    public void setFecha_Cobro(Date fecha_Cobro) {
        this.fecha_Cobro = fecha_Cobro;
    }

    public Date getFecha_Pago() {
        return fecha_Pago;
    }

    public void setFecha_Pago(Date fecha_Pago) {
        this.fecha_Pago = fecha_Pago;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }
    
}
