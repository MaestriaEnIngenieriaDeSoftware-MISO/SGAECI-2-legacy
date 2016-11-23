/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

/**
 *
 * @author 2106796
 */
public class Persona {
    private int documentoID;
    private long telefono1;
    private BigInteger telefono2;
    private String tipoDocumentoID,nombre,apellido,direccion,correo_Personal,genero;
    Rol rol;

    
    
    
     /**
     * Constructor de persona
     *
     * @param DocumentoID
     * @param telefono1
     * @param telefono2
     * @param TipoID
     * @param nombre
     * @param apellido
     * @param direccion
     * @param correo
     * @param genero
     * @param rol
     */
    public Persona(int documentoID,long telefono1,BigInteger telefono2,String tipoDocumentoID,String nombre,String apellido ,String direccion ,String correo,String genero,Rol rol){
        this.documentoID = documentoID;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.tipoDocumentoID = tipoDocumentoID;
        this.nombre = nombre;
        this.apellido=apellido;
        this.direccion = direccion;
        this.correo_Personal = correo;
        this.genero = genero;
        this.apellido=apellido;
        this.rol=rol;
        
        

    }

    public Persona() {
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    
    
    public int getDocumentoID() {
        return documentoID;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public void setDocumentoID(int documentoID) {
        this.documentoID = documentoID;
    }

    public long getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(long telefono1) {
        this.telefono1 = telefono1;
    }

    public BigInteger getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(BigInteger telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTipoDocumentoID() {
        return tipoDocumentoID;
    }

    public void setTipoDocumentoID(String tipoDocumentoID) {
        this.tipoDocumentoID = tipoDocumentoID;
    }

    public String getCorreo_Personal() {
        return correo_Personal;
    }

    public void setCorreo_Personal(String correo_Personal) {
        this.correo_Personal = correo_Personal;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }   
    
    public String toString() {
        String res;
        res= "Persona:[" +documentoID+" "+telefono1+" "+ telefono2+" "+tipoDocumentoID+" "+nombre+" "+apellido+" "+direccion+" "+ correo_Personal +" "+ genero+" "+ rol+ "] \n";
        return res;
    }
}
