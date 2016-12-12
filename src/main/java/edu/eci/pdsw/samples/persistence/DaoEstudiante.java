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
package edu.eci.pdsw.samples.persistence;

import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import java.util.List;



/**
 *
 * @author hcadavid
 */
public interface DaoEstudiante {

    /**
     * consulta un estudiante con un documento de identidad especifico de la base de datos
     * @param DocumentoID
     * @return estudiante relacionado con la base de datos 
     * @throws PersistenceException 
     */
    public Estudiante load(int DocumentoID) throws PersistenceException;
    
    /**
     * registra un estudiante en la base de datos 
     * @param e
     * @throws PersistenceException 
     */
    public void save(Estudiante e) throws PersistenceException;
    
    /**
     * consulta todos los estudiantes registrados en la base de datos 
     * @return lista con todos los estudiantes
     * @throws PersistenceException 
     */
    public List<Estudiante> loadAll() throws PersistenceException;
    
    
    
}
