/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence;

/**
 *
 * @author Martin
 */
public interface  DaoRolPersona {
   
    /**
     * registra una contraseña encriptada con SHA1 en la base de datos y le asigna su propietario
     * @param contra contraseña
     * @param tipo
     * @param DocumentoID documento de la persona a la que se le asociara la contraseña
     * @throws PersistenceException 
     */
    public void save(int DocumentoID,int tipo,String contra) throws PersistenceException;
    
    /**
     * Actualzia una contraseña encriptada con SHA1 en la base de datos 
     * @param contra contraseña
     * @param DocumentoID documento de la persona a la que se le asociara la contraseña
     * @throws PersistenceException 
     */
    public void update(int DocumentoID,String contra) throws PersistenceException;
  
}
