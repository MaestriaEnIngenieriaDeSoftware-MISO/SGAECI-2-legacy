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
    
    
    public Egresado load(int DocumentoID) throws PersistenceException;
    
    public void save(Egresado e) throws PersistenceException;

    public List<Egresado> loadAll() throws PersistenceException;

    
}
