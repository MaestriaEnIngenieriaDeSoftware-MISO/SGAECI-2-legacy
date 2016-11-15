/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Persona;
import java.util.List;

/**
 *
 * @author ricardo
 */
public interface DaoEgresado {
    
    /**
     * carga un egresado de la base de datos con un documento de identidad especifico
     * @param DocumentoID
     * @return egresado relacionado con dicho documento de identidad
     * @throws PersistenceException 
     */
    public Egresado load(int DocumentoID) throws PersistenceException;
    /**
     * registra un egresado en la base de datos
     * @param e
     * @throws PersistenceException 
     */
    public void save(Egresado e) throws PersistenceException;
    /**
     * consulta todos los egresados en la base de datos 
     * @return lista con todos los egresados
     * @throws PersistenceException 
     */
    public List<Egresado> loadAll() throws PersistenceException;

    
}
