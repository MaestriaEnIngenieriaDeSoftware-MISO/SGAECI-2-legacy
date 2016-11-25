/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;

/**
 *
 * @author Martin
 */
public interface RolPersonaMapper {
    public void insertarRolPersona( @Param("ID") int documentoID,@Param("t") int tipo,@Param("c") String contra);  
}



    
