/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.estadoAfiliacion;
import java.util.List;

/**
 *
 * @author 2106796
 */
public interface DaoEstadoAfiliacion {
    /**
     * consulta un estadoAfiliacion con un documento de identidad especifico de la base de datos
     * @param DocumentoID
     * @return estadoAfiliacion relacionado con la base de datos 
     * @throws PersistenceException 
     */
    public estadoAfiliacion load(int DocumentoID) throws PersistenceException;
    
    /**
     * registra un estadoAfiliacion en la base de datos 
     * @param e
     * @throws PersistenceException 
     */
    public void save(estadoAfiliacion e) throws PersistenceException;
    
    /**
     * consulta todos los estadoAfiliaciones registrados en la base de datos 
     * @return lista con todos los estadoAfiliaciones
     * @throws PersistenceException 
     */
    public List<estadoAfiliacion> loadAll() throws PersistenceException;
    
    /**
     * actualizar un estadoAfiliacion en la base de datos
     * @param p
     * @throws PersistenceException 
     */
    public void update(estadoAfiliacion p) throws PersistenceException;
    
}
