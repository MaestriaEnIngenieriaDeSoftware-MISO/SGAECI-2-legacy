/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.util.Set;

/**
 *
 * @author 2106796
 */
public class Persona {
    private int documentoID;
    private long telefono1,telefono2;
    private String tipoDocumentoID,nombre,apellido,direccion,correo,genero;
    
    public Persona(int documentoID,long telefono1,long telefono2,String tipoDocumentoID,String nombre,String direccion ,String correo,String genero,String apellido){
        this.documentoID = documentoID;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.tipoDocumentoID = tipoDocumentoID;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.genero = genero;
        this.apellido=apellido;
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

    public void setDocumentoID(int documentoID) {
        this.documentoID = documentoID;
    }

    public long getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(long telefono1) {
        this.telefono1 = telefono1;
    }

    public long getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(long telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTipoDocumentoID() {
        return tipoDocumentoID;
    }

    public void setTipoDocumentoID(String tipoDocumentoID) {
        this.tipoDocumentoID = tipoDocumentoID;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }   
    
}
