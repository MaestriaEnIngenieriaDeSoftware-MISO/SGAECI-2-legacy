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

import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcadavid
 */
public interface PersonaMapper {
    
    public List<Persona> getAfiliacionesVencerse ();

    
    /**
     * inserta persona en la base de datos
     * @param documentoID
     * @param tipoDocumentoID
     * @param nombre
     * @param apellido
     * @param direccion
     * @param correo
     * @param genero
     * @param telefono1
     * @param telefono2 
     */
    public void insertarPersona(@Param("documentoID") int documentoID, @Param("tipoDocumentoID") String tipoDocumentoID,@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("direccion") String direccion,@Param("correo") String correo, @Param("genero") String genero, @Param("telefono1") long telefono1,@Param("telefono2") BigInteger telefono2);
    
    
    /**
     * elimina de la base de datos una persona con un documento de identidad especifico
     * @param documentoID 
     */
    public void eliminarPersona(@Param("documentoID") int documentoID);
    
    }
