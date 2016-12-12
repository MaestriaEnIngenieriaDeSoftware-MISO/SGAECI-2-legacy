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
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcadavid
 */
public interface EgresadoMapper {
    /**
     * consuta todos los egresados en la base de datos 
     * @return lista con todos los egresados
     */
    public List<Egresado> getEgresados();
    
    /**
     * registrar un egresado en la base de datos 
     * @param documentoID
     * @param semestreGraduacion
     * @param correoPersonal
     * @param labora
     * @param cargo
     * @param nombreempre
     * @param graduacion 
     * @param carrera 
     */
    public void insertarEgresado( @Param("documentoID") int documentoID,@Param("semestreGraduacion") String semestreGraduacion,@Param("correoPersonal") String correoPersonal,@Param("labora") String labora,@Param("cargo") String cargo,@Param("empresaID") String nombreempre,@Param("fechaGraduacion")Date graduacion,@Param("carrera")String carrera);                                    
    
    /**
     * obtener un egresado con un documento de identidad especifico
     * @param documentoID
     * @return egresado relacionado con dicho documento de identidad
     */
    public Egresado getEgresado(@Param("id") int documentoID );
    
}
