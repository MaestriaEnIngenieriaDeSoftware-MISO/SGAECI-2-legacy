/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;



import com.mysql.jdbc.Blob;
import java.util.Date;

/**
 *
 * @author Martin
 */
public class PagoAfiliacion {
    private int pagoid;
    private int valor;
    private int documentoid;
    private Date fecharegistro;
    private Date fechapago;
    private Egresado e1;
    private String estado;
    private byte[] img;
    private String tipo;

    public PagoAfiliacion(int pagoid, int valor, int documentoid, Date fecharegistro, Date fechapago, String estado, byte[] img,String tipo) {
        this.pagoid = pagoid;
        this.valor = valor;
        this.documentoid = documentoid;
        this.fecharegistro = fecharegistro;
        this.fechapago = fechapago;
        this.estado = estado;
        this.img=img;
        this.tipo=tipo;
        
    }

    
    
    public PagoAfiliacion(){}

    public int getPagoid() {
        return pagoid;
    }

    public void setPagoid(int pagoid) {
        this.pagoid = pagoid;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getDocumentoid() {
        return documentoid;
    }

    public void setDocumentoid(int documentoid) {
        this.documentoid = documentoid;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public Date getFechapago() {
        return fechapago;
    }

    public void setFechapago(Date fechapago) {
        this.fechapago = fechapago;
    }

    public Egresado getE1() {
        return e1;
    }

    public void setE1(Egresado e1) {
        this.e1 = e1;
    }


    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
}
