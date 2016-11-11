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
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoSolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EgresadoMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EmpresaMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.SolicitudAfMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

/**
 *
 * @author hcadavid
 */
 public class MyBatisDAOSolicitudAfiliacion implements DaoSolicitudAfiliacion{

    private SqlSession currentSession=null;

    public MyBatisDAOSolicitudAfiliacion(SqlSession session) {
        this.currentSession=session;
    }
        
    @Override
    public SolicitudAfiliacion load(int id) throws PersistenceException {
        SolicitudAfMapper pedmp=currentSession.getMapper(SolicitudAfMapper.class);
        return pedmp.getSolicitudAf(id);

    }

    @Override
    public List<SolicitudAfiliacion> loadAll() throws PersistenceException {
        SolicitudAfMapper pedmp=currentSession.getMapper(SolicitudAfMapper.class);
        return pedmp.getSolicitudesAfiliacion();
    }

    @Override
    public void save(SolicitudAfiliacion Sa) throws PersistenceException {
       SolicitudAfMapper pedmp=currentSession.getMapper(SolicitudAfMapper.class);
       pedmp.insertarPersona(Sa.getE1().getDocumentoID(), Sa.getE1().getTipoDocumentoID(), Sa.getE1().getNombre(), Sa.getE1().getDireccion(), Sa.getE1().getCorreo(), Sa.getE1().getGenero(), Sa.getE1().getTelefono1(), Sa.getE1().getTelefono2());
       Sa.getE1().getNombre();
       if(Sa.getE1()==null){
           pedmp.insertarSolicitudAfiliacion(Sa.getE2().getDocumentoID(),Sa.getFechaSolicitud(),Sa.getEstadoSolicitud(),Sa.getComentario());
       }else{
           Egresado e = Sa.getE1();
           EgresadoMapper pedmp2=currentSession.getMapper(EgresadoMapper.class);
           EmpresaMapper pedmp3=currentSession.getMapper(EmpresaMapper.class);
           if(e.getEmp().getNombreempre()!=null ){
                if( !pedmp3.getEmpresa(e.getEmp().getNombreempre()).getNombreempre().equals(e.getEmp().getNombreempre().toUpperCase())){
                   pedmp3.insertarEmpresa(e.getEmp().getEmpresaid(), e.getEmp().getNombreempre(), e.getEmp().getDirempre(), e.getEmp().getTelempre());
                }
           }
           pedmp2.insertarEgresado(e.getDocumentoID(),e.getSemestreGrado(),e.getCorreoPersonal(),e.getEmp().getEmpresaid(),e.getLabora(),e.getCargo());
           pedmp.insertarSolicitudAfiliacion(e.getDocumentoID(),Sa.getFechaSolicitud(),Sa.getEstadoSolicitud(),Sa.getComentario());
       }
       
        
    }

    @Override
    public void update(SolicitudAfiliacion Sa) throws PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     

    
    
    
}
