/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.persistence.DaoPersona;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PersonaMapper;

/**
 *
 * @author Little
 */
public class MyBatisDAOPersona implements DaoPersona{
    PersonaMapper Pemap=null;
    private SqlSession currentSession=null;
    public MyBatisDAOPersona(SqlSession session) {
        Pemap=session.getMapper(PersonaMapper.class);
    }

    
    
    @Override
    public Persona load(int DocumentoID) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save(Persona p) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Persona> loadLosing() throws PersistenceException {
        return Pemap.getAfiliacionesVencerse();
    }
    
}
