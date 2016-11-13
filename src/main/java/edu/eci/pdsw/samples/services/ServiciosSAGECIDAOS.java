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
package edu.eci.pdsw.samples.services;

import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.persistence.PersistenceException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.eci.pdsw.samples.persistence.DaoEstudiante;

/**
 *
 * @author hcadavid
 */



public class ServiciosSAGECIDAOS extends ServiciosSAGECI implements Serializable{

    private final DaoFactory daof;
    private final Properties properties;

    public ServiciosSAGECIDAOS() {
        this.properties = new PropertiesLoader().readProperties("applicationconfig.properties");
        this.daof=DaoFactory.getInstance(properties);
    }

    @Override
    public void agregarEstudiante(int codigoEstudiante, int documentoID, String semestrePonderado, int telefono1,int telefono2, String tipoDocumentoID, String nombre,String direccion,String carrera,String correo,String genero){
        try {
            Estudiante e = new Estudiante(documentoID, telefono1, telefono2, tipoDocumentoID, nombre, direccion, correo, genero, codigoEstudiante, telefono2, carrera);
            daof.beginSession();
            daof.getDaoEstudiante().save(e);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @Override
    public void agregarEgresado(int documentoID, int telefono1, int telefono2,String tipoDocumentoID, String nombre, String direccion, String correo, String genero, String cargo, String semestreGrado, String correoPersonal, String labora,Egresado_Empresa egresadoEmpresa) {     
        try {
            Egresado e = new Egresado( documentoID,  telefono1,  telefono2, tipoDocumentoID,  nombre,  direccion,  correo,  genero,  cargo,  semestreGrado,  correoPersonal,  labora, egresadoEmpresa);
            daof.beginSession();
            daof.getDaoEgresado().save(e);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<SolicitudAfiliacion> consultarSolicitudAfiliaciones() throws ExcepcionServiciosSAGECI {
        List<SolicitudAfiliacion> solicitudesAfiliaciones = null;
        try {
            daof.beginSession();
            solicitudesAfiliaciones = daof.getDaoSolicitudAfiliacion().loadAll();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return solicitudesAfiliaciones;
    }

    @Override
    public SolicitudAfiliacion consultarSolicitudAfiliacion(int SolicitudID) throws ExcepcionServiciosSAGECI {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarNuevaSolicitud(SolicitudAfiliacion s) throws ExcepcionServiciosSAGECI {
        try {
            daof.beginSession();
            daof.getDaoSolicitudAfiliacion().save(s);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }        

    @Override
    public void agregarComentarioSolicitud(int SolicitudID, String c) throws ExcepcionServiciosSAGECI {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estudiante consultarEstudiante(int DocumentoID) throws ExcepcionServiciosSAGECI {
        Estudiante e = null;
        try {
            daof.beginSession();
            e = daof.getDaoEstudiante().load(DocumentoID);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;
    }
    
    @Override
    public Egresado consultarEgresado(int DocumentoID) throws ExcepcionServiciosSAGECI {
        Egresado e = null;
        try {
            daof.beginSession();
            e = daof.getDaoEgresado().load(DocumentoID);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return e;

    }

    @Override
    public void actualizarSolicitudAfliliacion(SolicitudAfiliacion Sa) throws ExcepcionServiciosSAGECI {
        try {
            daof.beginSession();
            daof.getDaoSolicitudAfiliacion().update(Sa);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    }
class PropertiesLoader {

    public Properties readProperties(String fileName){
        InputStream input = null;
        Properties properties = new Properties();
        input = this.getClass().getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }
}
