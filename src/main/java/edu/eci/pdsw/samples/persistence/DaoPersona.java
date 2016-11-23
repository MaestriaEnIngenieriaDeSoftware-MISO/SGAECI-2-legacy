/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Persona;
import java.util.List;

/**
 *
 * @author Little
 */
public interface DaoPersona {
    /**
     * carga un Persona de la base de datos con un documento de identidad especifico
     * @param DocumentoID
     * @return Persona relacionado con dicho documento de identidad
     * @throws PersistenceException 
     */
    public Persona load(int DocumentoID) throws PersistenceException;
    /**
     * registra un persona en la base de datos
     * @param p
     * @throws PersistenceException 
     */
    public void save(Persona p) throws PersistenceException;
    /**
     * consulta todos los Personas en la base de datos 
     * @return lista con todos los Personas
     * @throws PersistenceException 
     */
    public List<Persona> loadAll() throws PersistenceException;
    /**
     * consulta una lista con las personas prontas a vencer su afiliacion
     * @return lista de Personas
     * @throws PersistenceException 
     */
    public List<Persona> loadLosing() throws PersistenceException;
}
