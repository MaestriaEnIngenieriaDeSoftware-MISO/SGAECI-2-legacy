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
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *
 * @author hcadavid
 */

public abstract class ServiciosSAGECI {
    
    private static final ServiciosSAGECI instance=new ServiciosSAGECIDAOS();
    
    protected ServiciosSAGECI(){        

    }
    
    public static ServiciosSAGECI getInstance() throws RuntimeException{        
        return instance;
    }
    
    /**
     * Registra un estudiante en la base de dates
     * @param codigoEstudiante codigo estudiantil del estudiante
     * @param documentoID   documento del estudiante
     * @param semestrePonderado semestre ponderado del estudiante  
     * @param telefono1 telefono de casa del estudiante
     * @param telefono2 telefono celular del estudiante
     * @param nombre nombre completo del estudiante
     * @param correo correo estudiantil del estudiante
     * @param carrera carrera Universitaria del estudiante
     * @param tipoDocumentoID tipo de doclumento del estudiante
     * @param direccion direccion de vivienda del estudiante
     * @param genero genero del estudiante
     * @throws ExcepcionServiciosSAGECI si se presento un error de persistencia
     */

    public abstract void agregarEstudiante(int codigoEstudiante, int documentoID, int semestrePonderado, int telefono1,BigInteger telefono2, String tipoDocumentoID, String nombre, String apellido, String direccion,String carrera,String correo,String genero,Rol rol) throws ExcepcionServiciosSAGECI;

    
    /**
     * Registra un egresado en la base de datos 
     * @param documentoID documento de identidad del egresado 
     * @param telefono1 telefono fijo del egresado 
     * @param telefono2 telefono celular del egresado
     * @param tipoDocumentoID tipo de documento del egresado
     * @param nombre nombre completo del egresado 
     * @param direccion direccion de vivienda del egresado
     * @param correo correo estudiantil del egresado
     * @param genero genero del egresado
     * @param cargo cargo empresarial de la empresa donde labora 
     * @param semestreGrado semestre de graduacion del egresado
     * @param correoPersonal correo personal del egresado
     * @param labora verificador si el egresado trabaja (si) o (no)
     * @param egresadoEmpresa empresa donde tabaja el egresado
     * @param fechaGraduacion fecha de graduacion del egresado
     * @throws ExcepcionServiciosSAGECI 
     */

    public abstract void agregarEgresado(int documentoID, int telefono1, BigInteger telefono2,String tipoDocumentoID, String nombre, String apellido, String direccion, String correo, String genero, Rol rol,String semestreGrado, String correoPersonal,String cargo , String labora,Egresado_Empresa egresadoEmpresa,Date fechaGraduacion) throws ExcepcionServiciosSAGECI;
    
    
    
    /**
     * Registra un Rol en la base de datos 
     * @param documentoID documento de identidad de la persona 
     * @param tipo tipo de privilegio
     * @param contra contraseña de la persona
     * @throws ExcepcionServiciosSAGECI 
     */
    public abstract void agregarRolPersona(int documentoID,int tipo,String contra) throws ExcepcionServiciosSAGECI;

    
    /**
     * Consulta todas las solicitudes de afiliaciones registradas en la base de datos
     * @return el conjunto de las solicitudes de afiliaciones
     * @throws ExcepcionServiciosSAGECI si se presento un error de persistencia
     */
    public abstract List<SolicitudAfiliacion> consultarSolicitudAfiliaciones() throws ExcepcionServiciosSAGECI;


    /**
     * Dado un identificador, consulta una solicitud de afiliacion
     * @param SolicitudID identificador de la solicitud de afiliacion
     * @return la solicitud de afiliacion
     * @throws ExcepcionServiciosSAGECI si el identificador no corresponde a una solicitud
     */
    public abstract SolicitudAfiliacion consultarSolicitudAfiliacion(int SolicitudID) throws ExcepcionServiciosSAGECI;
    

    /**
     * REGISTRA una nueva solicitud de afiliacion a la pagina, el identificador y la fecha  se auto generan
     * @param s la nueva solicitud de afiliacion
     * @throws ExcepcionServiciosSAGECI si se presento un error de persistencia
     */
    public abstract void registrarNuevaSolicitud(SolicitudAfiliacion s) throws ExcepcionServiciosSAGECI;
    

    /**
     * Agrega una respuesta de porque fue rechazada la solicitud de afiliacion
     * @param SolicitudID identificador de la la solicitud de afiliacion
     * @param c el comentario a ser agregado
     * @throws ExcepcionServiciosSAGECI si el identificador no corresponde a una solicitud
     */
    public abstract void agregarComentarioSolicitud(int SolicitudID,String c) throws ExcepcionServiciosSAGECI;
    
    
    /**
     * Consulta una Persona registrada o afiliada
     * @param DocumentoID el documento con el cual la persona se registro
     * @return La persona registrada
     * @throws ExcepcionServiciosSAGECI si no hay una persona asociados al documento
     */
    public abstract Estudiante consultarEstudiante(int DocumentoID) throws ExcepcionServiciosSAGECI;
    
    /**
     * Consulta una Persona registrada o afiliada
     * @param DocumentoID el documento con el cual la persona se registro
     * @return La persona registrada
     * @throws ExcepcionServiciosSAGECI si no hay una persona asociados al documento
     */
    public abstract Egresado consultarEgresado(int DocumentoID) throws ExcepcionServiciosSAGECI;
    
    /**
     * actualizar una solicitud de Afiliacion en la base de datos 
     * @param s solicitud de afiliacion para actualizar 
     * @throws ExcepcionServiciosSAGECI si no hay un documento asociado o si hay algun problema de persistenciaados
     */
    public abstract void actualizarSolicitudAfliliacion(SolicitudAfiliacion s) throws ExcepcionServiciosSAGECI;
    /**
     * Consulta las solicitudes de afiliacion a vencerce
     * @return
     * @throws ExcepcionServiciosSAGECI si hay algun problema de persistenciaados
     */
    public abstract List<Persona> consultarSolicitudAfiliacionesVencidas() throws ExcepcionServiciosSAGECI;
    
    
    /**
     * Consulta el estado de afiliacion de una Persona registrada o afiliada
     * @param DocumentoID el documento con el cual la persona se registro
     * @return el estado de la afilaicon
     * @throws ExcepcionServiciosSAGECI si no hay una persona asociados al documento
     */
    public abstract estadoAfiliacion consultarEstadoAfiliacion(int DocumentoID) throws ExcepcionServiciosSAGECI;

    
   
    /**
     * Consulta los pagos de afiliacion no tramitados 
     * @return  Lista con los pagos no tramitados 
     * @throws ExcepcionServiciosSAGECI si hay algun problema de persistenciaados
     */
    public abstract List<PagoAfiliacion> consultarPagosAfiliacionesNoTramitados() throws ExcepcionServiciosSAGECI;
    
     /**
     * Consulta los pagos de afiliacion Regsitrados
     * @return  Lista con los pagos de afiliacion registrados
     * @throws ExcepcionServiciosSAGECI si hay algun problema de persistenciaados
     */
    public abstract List<PagoAfiliacion> consultarPagosAfiliacionesRegistrados() throws ExcepcionServiciosSAGECI;
    
    
     /**
     * Consulta todos los pagos de afiliacion 
     * @return  Lista con los pagos de afiliacion 
     * @throws ExcepcionServiciosSAGECI si hay algun problema de persistenciaados
     */
    public abstract List<PagoAfiliacion> consultarPagosAfiliaciones() throws ExcepcionServiciosSAGECI;
    
     /**
     * Consulta todos los pagos de afiliacion de un egresado en especifico
     * @return  Lista con los pagos de afiliacion 
     * @throws ExcepcionServiciosSAGECI si hay algun problema de persistenciaados
     */
    public abstract List<PagoAfiliacion> consultarPagosAfiliacionesEspecifico(int id) throws ExcepcionServiciosSAGECI;
    
    /**
     * actualizar una solicitud de Afiliacion en la base de datos 
     * @param s solicitud de afiliacion para actualizar 
     * @throws ExcepcionServiciosSAGECI si no hay un id asociado o si hay algun problema de persistenciaados
     */
    public abstract void actualizarPagoAfliliacion(PagoAfiliacion s) throws ExcepcionServiciosSAGECI;
    
    /**
     * Borra un pago Afiliacion rechazado por el administrador en la base de datos 
     * @param s pago afiliacion para actualizar 
     * @throws ExcepcionServiciosSAGECI si no hay un id asociado o si hay algun problema de persistenciaados
     */
    public abstract void BorrarPagoAfliliacion(PagoAfiliacion s) throws ExcepcionServiciosSAGECI;
    
    
    
    /**
     * Agrega un pago Afiliacion a la base de datos 
     * @param pg pago afiliacion para actualizar 
     * @throws ExcepcionServiciosSAGECI si no hay un id asociado o si hay algun problema de persistenciaados
     */
    public abstract void agregarPagoAfliliacion(PagoAfiliacion pg) throws ExcepcionServiciosSAGECI;
    
}