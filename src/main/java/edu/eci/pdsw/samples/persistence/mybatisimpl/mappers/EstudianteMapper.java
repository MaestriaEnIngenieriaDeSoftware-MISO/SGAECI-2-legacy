/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author ricardo
 */
public interface EstudianteMapper {
        
        public List<Estudiante> getEstudiantes();
        public void insertarEstudiante(@Param("codigoEstudiante") int codigoEstudiante,@Param("documentoID") int documentoID,@Param("semestrePonderado") int semestrePonderado,@Param("carrera") String carrera);
        public Estudiante getEstudiante(@Param("idEstudiante") int documentoID );
        public void update(Persona anterior, Persona p);
    
}
