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
    
    public List<SolicitudAfiliacion> getSolicitudesAfiliacion();
    
    public SolicitudAfiliacion getSolicitudAf(@Param("identificador") int id); 
    
    public void insertarSolicitudAfiliacion(@Param("documentoid")int documentoID , @Param("fecha")Date fechaSolicitud, @Param("estado")String estadoSolicitud,@Param("comentario")String coment);
    
    
}
