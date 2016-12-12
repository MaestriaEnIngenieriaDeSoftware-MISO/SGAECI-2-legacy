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
        /**
         * consulta todos los estudiantes registrados en la base de datos 
         * @return lista con los estudiantes ingresados
         */
        public List<Estudiante> getEstudiantes();
        
        /**
         * inserta un estudiante a la base de datos
         * @param codigoEstudiante
         * @param documentoID
         * @param semestrePonderado
         * @param carrera 
         */
        public void insertarEstudiante(@Param("codigoEstudiante") int codigoEstudiante,@Param("documentoID") int documentoID,@Param("semestrePonderado") int semestrePonderado,@Param("carrera") String carrera);
        
        /**
         * consulta un estudiante con un documento de identidad especifico
         * @param documentoID
         * @return estudiante con documento de indentidad
         */
        public Estudiante getEstudiante(@Param("id") int documentoID );
        
     
}
