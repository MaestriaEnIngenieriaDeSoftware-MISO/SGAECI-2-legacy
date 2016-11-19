/*
 * To change this licensfae header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 2106796
 */
public class Egresado extends Persona{
    private String semestreGrado,correoEstudiantil,labora,cargo;
    private Egresado_Empresa emp;
    private Date fechaGraduacion;
    
    public Egresado(int documentoID, long telefono1, BigInteger telefono2,String tipoDocumentoID, String nombre,String apellido, String direccion, String correo, String genero,Rol rol, String semestreGrado, String correoPersonal, String cargo, String labora,Egresado_Empresa egresadoEmpresa, Date graduacion) {
        super(documentoID,telefono1,telefono2,tipoDocumentoID,nombre, apellido, direccion,correoPersonal,genero,rol);
        this.semestreGrado = semestreGrado;
        this.correoEstudiantil = correo;
        this.cargo=cargo;
        this.emp=egresadoEmpresa;
        this.labora=labora;
        this.fechaGraduacion=graduacion;
    }

    public Egresado() {
    }

    public String getSemestreGrado() {
        return semestreGrado;
    }

    public void setSemestreGrado(String semestreGrado) {
        this.semestreGrado = semestreGrado;
    }

    public String getCorreoEstudiantil() {
        return correoEstudiantil;
    }

    public void setCorreoEstudiantil(String correoEstudiantil) {
        this.correoEstudiantil = correoEstudiantil;
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

    public Date getFechaGraduacion() {
        return fechaGraduacion;
    }

    public void setFechaGraduacion(Date fechaGraduacion) {
        this.fechaGraduacion = fechaGraduacion;
    }
}
