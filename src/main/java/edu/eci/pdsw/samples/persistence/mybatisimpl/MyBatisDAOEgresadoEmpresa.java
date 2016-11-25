/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.persistence.DaoEgresadoEmpresa;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EmpresaMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PersonaMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author 2107713
 */
class MyBatisDAOEgresadoEmpresa implements DaoEgresadoEmpresa {
    EmpresaMapper Pemap=null;
    private SqlSession currentSession=null;

    public MyBatisDAOEgresadoEmpresa(SqlSession currentSession) {
        Pemap=currentSession.getMapper(EmpresaMapper.class);
    }

    @Override
    public Egresado_Empresa load(String Nombre) throws PersistenceException {
        return Pemap.getEmpresa(Nombre);
    }

    @Override
    public void save(Egresado_Empresa emp) throws PersistenceException {
        Pemap.insertarEmpresa(emp.getNombreempre(), emp.getDirempre(), emp.getTelempre());
    }

    @Override
    public List<Egresado_Empresa> loadAll() throws PersistenceException {
        return Pemap.getEmpresas();
    }
    
}
