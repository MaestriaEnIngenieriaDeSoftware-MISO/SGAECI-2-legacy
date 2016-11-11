/*
 * To change this licensfae header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

/**
 *
 * @author 2106796
 */
public class Egresado {
    private String semestreGrado,correoPersonal,labora,cargo;
    private Egresado_Empresa emp;
    private int documentoID,telefono1,telefono2;
    private String tipoDocumentoID,nombre,direccion,correo,genero;
    
    public Egresado(int documentoID, int telefono1, int telefono2,String tipoDocumentoID, String nombre, String direccion, String correo, String genero, String semestreGrado, String correoPersonal, String cargo, String labora,Egresado_Empresa egresadoEmpresa) {
       // super(documentoID,telefono1,telefono2,tipoDocumentoID,nombre,direccion,correo,genero);
        this.semestreGrado = semestreGrado;
        this.correoPersonal = correoPersonal;
        this.cargo=cargo;
        this.emp=egresadoEmpresa;
        this.labora=labora;
    }

    public Egresado() {
    }

    public int getDocumentoID() {
        return documentoID;
    }

    public void setDocumentoID(int documentoID) {
        this.documentoID = documentoID;
    }

    public int getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(int telefono1) {
        this.telefono1 = telefono1;
    }

    public int getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(int telefono2) {
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
    
 

    public String getSemestreGrado() {
        return semestreGrado;
    }

    public void setSemestreGrado(String semestreGrado) {
        this.semestreGrado = semestreGrado;
    }

    public String getCorreoPersonal() {
        return correoPersonal;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public String getLabora() {
        return labora;
    }

    public void setLabora(String labora) {
        this.labora = labora;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Egresado_Empresa getEmp() {
        return emp;
    }

    public void setEmp(Egresado_Empresa emp) {
        this.emp = emp;
    }

    
    
}
