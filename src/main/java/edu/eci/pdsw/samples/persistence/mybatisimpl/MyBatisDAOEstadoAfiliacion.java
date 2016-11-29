/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.estadoAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoEstadoAfiliacion;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EstadoAfMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author 2106796
 */
public class MyBatisDAOEstadoAfiliacion implements DaoEstadoAfiliacion{
    
    EstadoAfMapper Esmap=null;
    private SqlSession currentSession=null;

    public MyBatisDAOEstadoAfiliacion(SqlSession session) {
         Esmap=session.getMapper(EstadoAfMapper.class);
    }
    
    @Override
    public estadoAfiliacion load(int DocumentoID) throws PersistenceException {
        return Esmap.getEstadoAf(DocumentoID);
    }

    @Override
    public void save(estadoAfiliacion e) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<estadoAfiliacion> loadAll() throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(estadoAfiliacion p) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
