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
public interface  DaoRol {
   
    /**
     * registra una contraseña encriptada con SHA1 en la base de datos y le asigna su propietario
     * @param contra contraseña
     * @param DocumentoID documento de la persona a la que se le asociara la contraseña
     * @throws PersistenceException 
     */
    public void save(int DocumentoID,String contra,int tipo) throws PersistenceException;
  
}
