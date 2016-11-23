/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;
import edu.eci.pdsw.samples.persistence.DaoRol;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.RolMapper;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author Martin
 */
public class MyBatisDAORol implements DaoRol {
    RolMapper Rolmap=null;
    private SqlSession currentSession=null;

    public MyBatisDAORol(SqlSession session) {
        Rolmap=session.getMapper(RolMapper.class);
    }

    @Override
    public void save(int DocumentoID, String contra,int tipo) throws PersistenceException {
      
    }
    
}