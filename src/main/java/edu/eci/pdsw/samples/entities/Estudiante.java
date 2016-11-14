/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.entities;

/**
 *
 * @author 2106796
 */
public class Estudiante extends Persona{
    
    private int codigoEstudiante,semestrePonderado;
    private String carrera;
    
    public Estudiante(int documentoID, long telefono1, long telefono2, String tipoDocumentoID, String nombre, String direccion, String correo, String genero,int codigoEstudiante,int semestrePonderado, String carrera) {
        super(documentoID,telefono1,telefono2,tipoDocumentoID,nombre,direccion,correo,genero);
        this.codigoEstudiante = codigoEstudiante;
        this.semestrePonderado = semestrePonderado;
        this.carrera=carrera;
    }

    
    public Estudiante() {
    }

    public int getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(int codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public int getSemestrePonderado() {
        return semestrePonderado;
    }

    public void setSemestrePonderado(int semestrePonderado) {
        this.semestrePonderado = semestrePonderado;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    
}
