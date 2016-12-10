/*
 * Copyright (C) 2016 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoPagoAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoSolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EgresadoMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EmpresaMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EstudianteMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PagoAfMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.PersonaMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.SolicitudAfMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author hcadavid
 */
public class MyBatisDAOPagoAfiliacion implements DaoPagoAfiliacion {

    private SqlSession currentSession = null;

    public MyBatisDAOPagoAfiliacion(SqlSession session) {
        this.currentSession = session;
    }

    @Override
    public List<PagoAfiliacion> load(int id) throws PersistenceException {
        List<PagoAfiliacion> temp=null;
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        return temp=pedmp.getPagoAf(id);
    }

    @Override
    public List<PagoAfiliacion> loadAll() throws PersistenceException {
        List<PagoAfiliacion> temp=null;
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        return temp=pedmp.getPagosAfiliacion();
    }

    

    @Override
    public List<PagoAfiliacion> loadAll2() throws PersistenceException {
        List<PagoAfiliacion> temp=null;
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        return temp=pedmp.getPagosAfiliacionNoTramitados();
    }

    @Override
    public List<PagoAfiliacion> loadAll3() throws PersistenceException {
        List<PagoAfiliacion> temp=null;
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        return temp=pedmp.getPagosAfiliacionRegistrados();
    }
    
    @Override
    public void save(PagoAfiliacion Pa) throws PersistenceException {
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        pedmp.insertarPagoAfiliacion(Pa.getFecharegistro(), Pa.getDocumentoid(), Pa.getValor(), Pa.getFechapago(), Pa.getEstado(), Pa.getImg(),Pa.getTipo());
    }

    @Override
    public void update(PagoAfiliacion Pa) throws PersistenceException {
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        pedmp.updatePagoAfiliacion(Pa.getEstado(),Pa.getPagoid());
    }

    @Override
    public void delete(PagoAfiliacion Pa) throws PersistenceException {
        PagoAfMapper pedmp = currentSession.getMapper(PagoAfMapper.class);
        pedmp.DeletePagoAfiliacion(Pa.getPagoid());
    }

  
    

}
