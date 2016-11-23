/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import edu.eci.pdsw.samples.entities.estadoAfiliacion;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Carlo
 */
public interface EstadoAfMapper {
    
    /**
     * consulta una solicitud de afiliacion con un identificador especifico
     * @param id
     * @return la solicitud de afiliacion con ese identificador 
     */
    public estadoAfiliacion getEstadoAf(@Param("identificador") int id);
    
}
