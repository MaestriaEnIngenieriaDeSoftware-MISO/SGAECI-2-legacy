/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.persistence.DaoEgresado;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EgresadoMapper;
/**
 *
 * @author ricardo
 */
public class MyBatisDAOEgresado implements DaoEgresado{
    
    EgresadoMapper Egmap=null;
    private SqlSession currentSession=null;

    public MyBatisDAOEgresado(SqlSession session) {
        
        Egmap=session.getMapper(EgresadoMapper.class);
    }
    
     @Override
    public Egresado load(int DocumentoID) throws PersistenceException {
        if (Egmap.getEgresado(DocumentoID)==null)throw new PersistenceException ("El Egresado con  " + DocumentoID + "de identificacion no existe");
        return Egmap.getEgresado(DocumentoID);
    }

    @Override
    public List<Egresado> loadAll() throws PersistenceException {
        return Egmap.getEgresados();
    }
    /* @Override
    public List<Persona> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    @Override
    public void save(Egresado e) throws PersistenceException {
      if (Egmap.getEgresado(e.getDocumentoID())!= null)  throw new PersistenceException("El Egresado con Id : "+e.getDocumentoID()+" ya esta registrado");
      Egmap.insertarEgresado(e);
        //To change body of generated methods, choose Tools | Templates.
    }

   
}
