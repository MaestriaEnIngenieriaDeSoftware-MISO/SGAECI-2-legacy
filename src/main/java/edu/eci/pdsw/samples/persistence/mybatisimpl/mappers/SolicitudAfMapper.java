/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modifyint,java.util.Date,java.lang.String,java.lang.String
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
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcadavid
 */
public interface SolicitudAfMapper {
    /**
     * inserta persona en la base de datos
     * @param documentoID
     * @param tipoDocumentoID
     * @param nombre
     * @param direccion
     * @param correo
     * @param genero
     * @param telefono1
     * @param telefono2 
     */
    public void insertarPersona(@Param("documentoID") int documentoID, @Param("tipoDocumentoID") String tipoDocumentoID,@Param("nombre") String nombre,@Param("direccion") String direccion,@Param("correo") String correo, @Param("genero") String genero, @Param("telefono1") long telefono1,@Param("telefono2") long telefono2);
    
    /**
     * Consulta todas las Solicitudes de afiliacion que estan registradas en la base de
     * datos
     * @return lista de las solicitudes de afiliacion 
     */
    public List<SolicitudAfiliacion> getSolicitudesAfiliacion();
    
    /**
     * consulta una solicitud de afiliacion con un identificador especifico
     * @param id
     * @return la solicitud de afiliacion con ese identificador 
     */
    public SolicitudAfiliacion getSolicitudAf(@Param("identificador") int id);
    
    /**
     * registra en la base de datos una solicitude de afiliacion
     * @param documentoID
     * @param fechaSolicitud
     * @param estadoSolicitud
     * @param coment 
     */
    public void insertarSolicitudAfiliacion(@Param("documentoid")int documentoID , @Param("fecha")Date fechaSolicitud, @Param("estado")String estadoSolicitud,@Param("comentario")String coment);
    
    /**
     * elimina de la base de datos una persona con un documento de identidad especifico
     * @param documentoID 
     */
    public void eliminarPersona(@Param("documentoID") int documentoID);
    
    /**
     * elimina la solicitud de afiliacion realizada pon un usuario especifico
     * @param documentoID 
     */
    public void eliminarSolicitud(@Param("documentoID") int documentoID);
    
    /**
     * actualiza datos de una solicitud de afiliacion
     * @param estadoSolicitud
     * @param comentario
     * @param solicitudID 
     */
    public void actualizarSolicitud(@Param("estadoSolicitud") String estadoSolicitud, @Param("comentario")  String comentario, @Param("solicitudID") int solicitudID);
}
