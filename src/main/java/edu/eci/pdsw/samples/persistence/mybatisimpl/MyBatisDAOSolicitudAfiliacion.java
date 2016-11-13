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
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoSolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EgresadoMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EmpresaMapper;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EstudianteMapper;
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
        List<SolicitudAfiliacion> temp;
        temp=pedmp.getSolicitudesAfiliacion();
        return temp;
    }

    @Override
    public void save(SolicitudAfiliacion Sa) throws PersistenceException {
       SolicitudAfMapper pedmp=currentSession.getMapper(SolicitudAfMapper.class);
       if(Sa.getE1()==null){
           pedmp.insertarPersona(Sa.getE2().getDocumentoID(), Sa.getE2().getTipoDocumentoID(), Sa.getE2().getNombre(), Sa.getE2().getDireccion(), Sa.getE2().getCorreo(), Sa.getE2().getGenero(), Sa.getE2().getTelefono1(), Sa.getE2().getTelefono2());
           Estudiante e = Sa.getE2();
           EstudianteMapper pedmp2=currentSession.getMapper(EstudianteMapper.class);
           pedmp2.insertarEstudiante(e.getCodigoEstudiante(), e.getDocumentoID(), e.getSemestrePonderado(),e.getCarrera().toUpperCase());
           pedmp.insertarSolicitudAfiliacion(Sa.getE2().getDocumentoID(),Sa.getFechaSolicitud(),Sa.getEstadoSolicitud(),Sa.getComentario().toUpperCase());
       }else{       
           pedmp.insertarPersona(Sa.getE1().getDocumentoID(), Sa.getE1().getTipoDocumentoID(), Sa.getE1().getNombre(), Sa.getE1().getDireccion(), Sa.getE1().getCorreo(), Sa.getE1().getGenero(), Sa.getE1().getTelefono1(), Sa.getE1().getTelefono2());
           Egresado e = Sa.getE1();
           EgresadoMapper pedmp2=currentSession.getMapper(EgresadoMapper.class);
           EmpresaMapper pedmp3=currentSession.getMapper(EmpresaMapper.class);
           if(e.getLabora().equals("si") ){
                if(pedmp3.getEmpresa(e.getEmp().getNombreempre())==null){
                   pedmp3.insertarEmpresa(e.getEmp().getEmpresaid(), e.getEmp().getNombreempre().toUpperCase(), e.getEmp().getDirempre().toUpperCase(), e.getEmp().getTelempre());
                }
           }
           pedmp2.insertarEgresado(e.getDocumentoID(),e.getSemestreGrado(),e.getCorreoPersonal(),e.getEmp().getEmpresaid(),e.getLabora(),e.getCargo());
           pedmp.insertarSolicitudAfiliacion(e.getDocumentoID(),Sa.getFechaSolicitud(),Sa.getEstadoSolicitud(),Sa.getComentario().toUpperCase());
       }
       
        
    }

    @Override
    public void update(SolicitudAfiliacion Sa) throws PersistenceException {
        SolicitudAfMapper pedmp=currentSession.getMapper(SolicitudAfMapper.class);
        System.out.println(Sa.getEstadoSolicitud());
        pedmp.actualizarSolicitud(Sa.getEstadoSolicitud(), Sa.getComentario(), Sa.getSolicitudID());
        System.out.println(Sa.getEstadoSolicitud());
    }

     

    
    
    
}
