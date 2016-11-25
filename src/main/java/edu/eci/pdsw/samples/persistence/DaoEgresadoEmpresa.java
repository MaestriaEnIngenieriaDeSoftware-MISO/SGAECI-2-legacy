/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import java.util.List;

/**
 *
 * @author 2107713
 */
public interface DaoEgresadoEmpresa {
    
        /**
     * carga una Empresa de la base de datos dado el nombre de la empresa
     * @param Nombre
     * @return Egresado_Empresa relacionado con dicho nombre
     * @throws PersistenceException 
     */
    public Egresado_Empresa load(String Nombre) throws PersistenceException;
    /**
     * registra una Empresa en la base de datos
     * @param emp empresa
     * @throws PersistenceException 
     */
    public void save(Egresado_Empresa emp) throws PersistenceException;
    /**
     * consulta todos las Egresado_Empresa en la base de datos 
     * @return lista con todos las Egresado_Empresa
     * @throws PersistenceException 
     */
    public List<Egresado_Empresa> loadAll() throws PersistenceException;
    
}
