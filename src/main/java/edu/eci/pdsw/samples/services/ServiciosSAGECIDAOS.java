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

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Egresado_Empresa;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.Rol;
import edu.eci.pdsw.samples.entities.SolicitudAfiliacion;
import edu.eci.pdsw.samples.entities.estadoAfiliacion;
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
import java.math.BigInteger;
import java.util.Date;

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
    public void agregarEstudiante(int codigoEstudiante, int documentoID, int semestrePonderado, int telefono1,BigInteger telefono2, String tipoDocumentoID, String nombre, String apellido, String direccion,String carrera,String correo,String genero,Rol rol) throws ExcepcionServiciosSAGECI{
        try {
            Estudiante e = new Estudiante(documentoID, telefono1, telefono2, tipoDocumentoID, nombre, apellido, direccion, correo, genero,rol, codigoEstudiante,semestrePonderado, carrera);
            daof.beginSession();
            daof.getDaoEstudiante().save(e);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    

    @Override

    public void agregarEgresado(int documentoID, int telefono1, BigInteger telefono2,String tipoDocumentoID, String nombre, String apellido, String direccion, String correo, String genero, Rol rol,String semestreGrado, String correoPersonal,String cargo , String labora,Egresado_Empresa egresadoEmpresa,Date fechaGraduacion,String carrera)throws ExcepcionServiciosSAGECI {     
        try {
            Egresado e = new Egresado( documentoID,  telefono1,  telefono2, tipoDocumentoID,  nombre,  apellido, direccion,  correo,  genero,rol,  cargo,  semestreGrado,  correoPersonal,  labora, egresadoEmpresa,fechaGraduacion,carrera);
            daof.beginSession();
            daof.getDaoEgresado().save(e);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return solicitudesAfiliaciones;
    }

    @Override
    public SolicitudAfiliacion consultarSolicitudAfiliacion(int SolicitudID) throws ExcepcionServiciosSAGECI {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registrarNuevaSolicitud(SolicitudAfiliacion s) throws ExcepcionServiciosSAGECI{
        try {
            daof.beginSession();
            daof.getDaoSolicitudAfiliacion().save(s);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
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
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
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
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void agregarRolPersona(int documentoID,int tipo,String contra) throws ExcepcionServiciosSAGECI {
        try {
            daof.beginSession();
            daof.getDaoRolPersona().save(documentoID, tipo, contra);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public List<Persona> consultarSolicitudAfiliacionesVencidas() throws ExcepcionServiciosSAGECI {
        List<Persona> solicitudesVencidas= null;
        try {
            daof.beginSession();
            solicitudesVencidas = daof.getDaoPersona().loadLosing();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return solicitudesVencidas;
    }

    @Override
    public estadoAfiliacion consultarEstadoAfiliacion(int DocumentoID) throws ExcepcionServiciosSAGECI {
        estadoAfiliacion estadoAfiliacion= null;
        try {
            daof.beginSession();
            estadoAfiliacion = daof.getDaoEstadoAfiliacion().load(DocumentoID);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return estadoAfiliacion;
    }

    @Override
    public List<PagoAfiliacion> consultarPagosAfiliacionesNoTramitados() throws ExcepcionServiciosSAGECI {
        List<PagoAfiliacion> pagosnotramitados= null;
        try {
            daof.beginSession();
            pagosnotramitados = daof.getDaoPagoAfiliacion().loadAll3();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return pagosnotramitados;
    }

    @Override
    public List<PagoAfiliacion> consultarPagosAfiliacionesRegistrados() throws ExcepcionServiciosSAGECI {
        List<PagoAfiliacion> pagosregistrados= null;
        try {
            daof.beginSession();
            pagosregistrados = daof.getDaoPagoAfiliacion().loadAll2();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return pagosregistrados;
    }

    @Override
    public List<PagoAfiliacion> consultarPagosAfiliaciones() throws ExcepcionServiciosSAGECI {
        List<PagoAfiliacion> pagosAfiliaciones= null;
        try {
            daof.beginSession();
            pagosAfiliaciones = daof.getDaoPagoAfiliacion().loadAll();
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return pagosAfiliaciones;
    }

    @Override
    public void actualizarPagoAfliliacion(PagoAfiliacion Pa) throws ExcepcionServiciosSAGECI {
         try {
            daof.beginSession();
            daof.getDaoPagoAfiliacion().update(Pa);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void BorrarPagoAfliliacion(PagoAfiliacion s) throws ExcepcionServiciosSAGECI {
          try {
            daof.beginSession();
            daof.getDaoPagoAfiliacion().delete(s);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public List<PagoAfiliacion> consultarPagosAfiliacionesEspecifico(int id) throws ExcepcionServiciosSAGECI {
        List<PagoAfiliacion> pagosAfiliacionesegre= null;
        try {
            daof.beginSession();
            pagosAfiliacionesegre = daof.getDaoPagoAfiliacion().loadAll4(id);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return pagosAfiliacionesegre;
    }

    @Override
    public void agregarPagoAfliliacion(PagoAfiliacion pg) throws ExcepcionServiciosSAGECI {
        try {
            daof.beginSession();
            daof.getDaoPagoAfiliacion().save(pg);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actualizarUsuario(int documentoid, String direccionVivienda, String correo, int telefono, int telefono2) throws ExcepcionServiciosSAGECI {
       try {
            daof.beginSession();
            daof.getDaoPersona().update(documentoid, direccionVivienda, correo, telefono, telefono2);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actualizarContra(int documentoid, String contra) throws ExcepcionServiciosSAGECI {
        try {
            daof.beginSession();
            daof.getDaoRolPersona().update(documentoid, contra);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            try {
                daof.rollbackTransaction();
            } catch (PersistenceException ex1) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
            
        }finally{
            try {
                daof.endSession();
            } catch (PersistenceException ex) {
                Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Persona consultarPersona(int DocumentoID) throws ExcepcionServiciosSAGECI {
        Persona p= null;
        try {
            daof.beginSession();
            p = daof.getDaoPersona().load(DocumentoID);
            daof.commitTransaction();
            daof.endSession();
        } catch (PersistenceException ex) {
            Logger.getLogger(ServiciosSAGECIDAOS.class.getName()).log(Level.SEVERE, null, ex);
            throw new ExcepcionServiciosSAGECI(ex.getLocalizedMessage(),ex);
        }
        return p;
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
