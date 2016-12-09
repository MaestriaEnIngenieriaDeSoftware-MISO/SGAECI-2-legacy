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

import com.mysql.jdbc.Blob;
import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author hcadavid
 */
public interface PagoAfMapper {
     /**
     * Consulta todas los pagos de afiliacion que estan registradas en la base de
     * datos
     * @return lista de pagos de afiliacion 
     */
    public List<PagoAfiliacion> getPagosAfiliacionRegistrados();
    
    /**
     * Consulta todos los pagos afiliacion que no estan tramitados
     * datos
     * @return lista de pagos no tramitados 
     */
    public List<PagoAfiliacion> getPagosAfiliacionNoTramitados();
    
    
    /**
     * Consulta todos los pagos afiliacion en la base de datos
     * datos
     * @return lista de pagos no tramitados 
     */
    public List<PagoAfiliacion> getPagosAfiliacion();
    
    
    /**
     * Consulta todos los pagos afiliacion en la base de datos
     * datos
     * @param id
     * @return lista de pagos no tramitados 
     */
    public List<PagoAfiliacion> getPagosAfiliacionEspecifico(@Param("documentoid") int id);
    
    
     /**
     * Actualiza un estado de un pago afiliacion
     * datos
     * @param estado 
     * @param id 
     */
    public void updatePagoAfiliacion(@Param("estado") String estado,@Param("id") int id);
    
     /**
     * Elimina un pago afiliacion rechazado por el atministrador
     * datos 
     * @param id
     */
    public void DeletePagoAfiliacion(@Param("id") int id);
    
    
    /**
     * consulta un pago afiliacion con un identificador especifico
     * @param id
     * @return El pago afiliacion con ese identificador 
     */
    public List<PagoAfiliacion> getPagoAf(@Param("identificador") int id);
    
    /**
     * registra en la base de datos un pago afiliacion
     * @param fr
     * @param documentoID
     * @param v
     * @param fp 
     * @param estado 
     * @param comprobante 
     */
    public void insertarPagoAfiliacion(@Param("fecharegistro")Date fr ,@Param("documentoid")int documentoID ,@Param("valor")int v , @Param("fechapago")Date fp, @Param("estado")String estado,@Param("comprobante") Blob comprobante);
    
}
