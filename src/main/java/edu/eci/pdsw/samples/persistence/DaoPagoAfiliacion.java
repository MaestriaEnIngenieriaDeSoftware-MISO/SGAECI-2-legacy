/*
 * Copyright (C) 2016 hcadavid
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
package edu.eci.pdsw.samples.persistence;


import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import java.util.List;

/**
 *
 * @author hcadavid
 */
public interface DaoPagoAfiliacion{
    /**
     * consutla un pago  afiliacion con identificador especifico de la base de datos  
     * @param id
     * @return Pago afiliacion 
     * @throws PersistenceException 
     */
    public List<PagoAfiliacion> load(int id) throws PersistenceException;
    /**
     * consulta todos los pagos afiliacion de la base de datos 
     * @return lista con los pagos afiliacion 
     * @throws PersistenceException 
     */
    public List<PagoAfiliacion> loadAll() throws PersistenceException;
    /**
     * consulta todos los pagos afiliacion de la base de datos que esten registrados
     * @return lista con los pagos afiliacion 
     * @throws PersistenceException 
     */
    public List<PagoAfiliacion> loadAll2() throws PersistenceException;
    /**
     * consulta todos los pagos afiliacion de la base de datos que no esten tramitados 
     * @return lista con los pagos afiliacion 
     * @throws PersistenceException 
     */
    public List<PagoAfiliacion> loadAll3() throws PersistenceException;
    
    
    
    /**
     * registrar un pago afiliacion en la base de datos
     * @param Pa
     * @throws PersistenceException 
     */
    public void save(PagoAfiliacion Pa) throws PersistenceException;
   
    /**
     * actualiza un pago afiliacion en la base de datos 
     * @param Pa
     * @throws PersistenceException 
     */
    public void update(PagoAfiliacion Pa) throws PersistenceException;
    
    /**
     * Elimina un pago afiliacion que fue rechazado por el administrador 
     * @param Pa
     * @throws PersistenceException 
     */
    public void delete(PagoAfiliacion Pa) throws PersistenceException;
}
