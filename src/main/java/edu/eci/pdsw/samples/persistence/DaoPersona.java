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
     * consulta una lista con las personas prontas a vencer su afiliacion
     * @return lista de Personas
     * @throws PersistenceException 
     */
    public List<Persona> loadLosing() throws PersistenceException;
}
