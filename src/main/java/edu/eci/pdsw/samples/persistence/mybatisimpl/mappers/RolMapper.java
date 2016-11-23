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
public interface RolMapper {
    public void insertarContra( @Param("docuID") int documentoID,@Param("c") String contra,@Param("tipo") int tipo);  
}



    
