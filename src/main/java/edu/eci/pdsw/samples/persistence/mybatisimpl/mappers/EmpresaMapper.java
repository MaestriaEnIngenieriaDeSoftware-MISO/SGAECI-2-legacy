/*
 * Copyright (C) 2015 hcadavid
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
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcadavid
 */
public interface EmpresaMapper {
    /**
     * insertar una empresa a la que trabaja un egresado a la base de datos
     * @param nombre
     * @param direccion
     * @param telefono 
     */
    public void insertarEmpresa( @Param("nombre") String nombre, @Param("direccion") String direccion, @Param("telefono") int telefono);
    
    /**
     * consulta una empresa con un nombre especifico
     * @param nombre
     * @return empresa con dicho nombre
     */
    public Egresado_Empresa getEmpresa(@Param("nombre") String nombre);
    
    
    /**
     * consulta todas las empresas de la base de datos
     * @return empresas de la base de datos
     */
    public List<Egresado_Empresa> getEmpresas();
    
    
}
