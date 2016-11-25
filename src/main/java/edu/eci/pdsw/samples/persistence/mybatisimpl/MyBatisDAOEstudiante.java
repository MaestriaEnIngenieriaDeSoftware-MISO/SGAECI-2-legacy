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
package edu.eci.pdsw.samples.persistence.mybatisimpl;

import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import edu.eci.pdsw.samples.persistence.mybatisimpl.mappers.EstudianteMapper;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import edu.eci.pdsw.samples.persistence.DaoEstudiante;


/**
 *
 * @author vivi
 */
public class MyBatisDAOEstudiante implements DaoEstudiante{

    EstudianteMapper Esmap=null;
    private SqlSession currentSession=null;

    public MyBatisDAOEstudiante(SqlSession session) {
         Esmap=session.getMapper(EstudianteMapper.class);
    }

    @Override
    
    public void update(Estudiante p) throws PersistenceException {
        
    }

    @Override
    public Estudiante load(int DocumentoID) throws PersistenceException {
       
        return Esmap.getEstudiante(DocumentoID);
            }

    @Override
    public List<Estudiante> loadAll() throws PersistenceException {
        return Esmap.getEstudiantes();
        
    }

    
    @Override
    public void save(Estudiante e) throws PersistenceException {
        Esmap.insertarEstudiante(e.getCodigoEstudiante(), e.getDocumentoID(), e.getSemestrePonderado(),e.getCarrera().toUpperCase());
    }
    
    
}
