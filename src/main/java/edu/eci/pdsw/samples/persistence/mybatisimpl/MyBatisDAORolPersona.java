/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.samples.persistence.DaoRolPersona;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.RolPersonaMapper;

/**
 *
 * @author Martin
 */
public class MyBatisDAORolPersona implements DaoRolPersona {
    RolPersonaMapper Rolmap=null;
    private SqlSession currentSession=null;

    public MyBatisDAORolPersona(SqlSession session) {
        Rolmap=session.getMapper(RolPersonaMapper.class);
    }

    @Override
    public void save(int DocumentoID,int tipo,String contra) throws PersistenceException {
      Rolmap.insertarRolPersona(DocumentoID, tipo, contra);
    }
    
}