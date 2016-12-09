/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.tests;

import edu.eci.pdsw.samples.persistence.DaoFactory;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author 2105537
 * 
 * CLASES DE EQUIVALENCIA
 * 
 *   CONDICION DE ENTRADA       |     TIPO      |     CLASE DE EQUIVALENCIA VALIDA     |     CLASE DE EQUIVALENCIA NO VALIDA
 *                              |               |                                      |
 *  La fecha actual debe ser    |               |                                      |
 *  menor a la Fecha de Cobro   |    Rango      |   DATEDIFF(SYSDATE(),Fecha_Fin)>=0   | La fecha de cobro ha superado la fecha actual
 *  del pago de la cuota        |               |                                      | por lo que la persona esta desvinculado de la
 *  correspondiente al asociado |               |                                      |                 asociacion.
 * 
 */
public class PersonasPagoRealizarTest {
    public static ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();
    
    private DaoFactory getFactoryTest() throws IOException{
        InputStream input;
        input = ClassLoader.getSystemResourceAsStream("applicationconfig_test.properties");
        Properties properties = new Properties();
        properties.load(input);
        return DaoFactory.getInstance(properties);
    }
    /*
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;DB_CLOSE_DELAY=-1;MODE=MYSQL", "anonymous", "");        
    }
    
    @Before
    public void setUp() throws SQLException{
        Connection conn = getConnection();
        Statement stmt=conn.createStatement();  
        
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1013567876,'CE','Marcos','Perez','Cll 27 sur','marcos@mail.com','MASCULINO',2879878,3167898786)");
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1014567877,'CC','Cristian','Rodriguez','Cll 145 Norte','cristian@mail.com','MASCULINO',2987653,3016578987)");
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1015567878,'CE','Pedro','Ortiz','Av 39 No. 45','Pedro@mail.com','MASCULINO',3456789,3209898076)");
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1016567879,'CC','Monica','Dominguez','AutoNorte 206','Monica@mail.com','MASCULINO',2675463,3167896567)");
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1017567880,'CE','Tatiana','Gomez','Calle 67 Av chile','Tatiana@mail.com','FEMENINO',1987967,3105990807)");
        stmt.execute("INSERT INTO `Persona` (`DocumentoID`, `TipoDocumentoID`, `Nombre`, `Apellido`,`Direccion`,`Correo_Personal`,`Genero`,`Telefono`,`Celular`) VALUES (1018567881,'CC','Juan','Alayon','Av Suba con 45','Juan@mail.com','MASCULINO',2978965,3093657687)");
        stmt.execute("INSERT INTO `Egresado_Empresa` (`Nombre`,`Direccion`,`Telefono`) VALUES ('Microsoft','Calle 85',2879876)");
        stmt.execute("INSERT INTO `Egresado` (`DocumentoID`,`Semestre_Graduacion`,`Correo_Estudiantil`,`Empresa`,`Labora`,`Cargo`,`Fecha_Graduacion`) VALUES (1013567876,'2012-2','Marcos@mail.escuelaing.edu.co',null,null,null,'2012-09-12')");
        stmt.execute("INSERT INTO `Egresado` (`DocumentoID`,`Semestre_Graduacion`,`Correo_Estudiantil`,`Empresa`,`Labora`,`Cargo`,`Fecha_Graduacion`) VALUES (1014567877,'2014-1','Cristian@mail.escuelaing.edu.co',null,null,null,'2014-05-01')");
        stmt.execute("INSERT INTO `Egresado` (`DocumentoID`,`Semestre_Graduacion`,`Correo_Estudiantil`,`Empresa`,`Labora`,`Cargo`,`Fecha_Graduacion`) VALUES (1015567878,'2009-2','Pedro@mail.escuelaing.edu.co',null,null,null,'2009-09-13')");
        stmt.execute("INSERT INTO `Egresado` (`DocumentoID`,`Semestre_Graduacion`,`Correo_Estudiantil`,`Empresa`,`Labora`,`Cargo`,`Fecha_Graduacion`) VALUES (1016567879,'2011-2','Monica@mail.escuelaing.edu.co','Microsoft','Si','Gerente','2011-09-13')");
        stmt.execute("INSERT INTO `Estudiante` (`Codigo_Estudiante`,`DocumentoID`,`Semestre_Ponderado`,`Carrera`) VALUES (2105678,1017567880,8,'INGENIERIA CIVIL')");
        stmt.execute("INSERT INTO `Estudiante` (`Codigo_Estudiante`,`DocumentoID`,`Semestre_Ponderado`,`Carrera`) VALUES (2094567,1018567881,7,'ECONOMIA')");
        
        conn.commit();
        conn.close();
    }

    @After
    public void clearDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:file:./target/db/testdb2;MODE=MYSQL", "anonymous", "");
        Statement stmt = conn.createStatement();
        stmt.execute("ALTER TABLE Egresado DROP FOREIGN KEY Egresado_Persona;");
        stmt.execute("ALTER TABLE Egresado DROP FOREIGN KEY Empresa_Egresado;");
        stmt.execute("ALTER TABLE Estado_afiliacion DROP FOREIGN KEY Estado_afiliacion_Persona;");
        stmt.execute("ALTER TABLE Estudiante DROP FOREIGN KEY Estudiante_Persona;");
        stmt.execute("ALTER TABLE Pago_Cuota DROP FOREIGN KEY Pago_Cuota_Egresado;");
        stmt.execute("ALTER TABLE Persona_Servicios DROP FOREIGN KEY Persona_Servicios_Persona;");
        stmt.execute("ALTER TABLE Persona_Servicios DROP FOREIGN KEY Persona_Servicios_Servicio;");
        stmt.execute("ALTER TABLE Solicitud_Afiliacion DROP FOREIGN KEY Persona_Solicitud_Afiliacion;");
        stmt.execute("ALTER TABLE Rol_Persona DROP FOREIGN KEY Rol_Persona_Persona;");
        stmt.execute("ALTER TABLE Rol_Persona DROP FOREIGN KEY Rol_Persona_Roles;");
        stmt.execute("ALTER TABLE Servicio DROP FOREIGN KEY Servicio_Empresa_Convenio;");
        stmt.execute("DROP TABLE Egresado;");
        stmt.execute("DROP TABLE Egresado_Empresa;");
        stmt.execute("DROP TABLE Empresa_Convenio;");
        stmt.execute("DROP TABLE Estado_afiliacion;");
        stmt.execute("DROP TABLE Estudiante;");
        stmt.execute("DROP TABLE Pago_Cuota;");
        stmt.execute("DROP TABLE Persona;");
        stmt.execute("DROP TABLE Persona_Servicios;");
        stmt.execute("DROP TABLE Rol_Persona;");
        stmt.execute("DROP TABLE Roles;");
        stmt.execute("DROP TABLE Servicio;");
        stmt.execute("DROP TABLE Solicitud_Afiliacion;");
        conn.commit();
        conn.close();
    }
    
    @Test
    public void DeberianExisitirTresPersonasconplazoparaPagarSuAfiliacion()throws SQLException, IOException{
        Connection conn=getConnection();
        Statement stmt=conn.createStatement();
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1013567876,'2015-12-12','2016-01-01','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1014567877,'2015-12-12','2016-02-12','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1015567878,'2015-12-12','2017-03-13','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1016567879,'2015-12-12','2017-04-14','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1017567880,'2015-12-12','2016-05-15','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Estado_Afiliacion (`DocumentoID`,`Fecha_Inicio`,`Fecha_Fin`,`Estado`) VALUES (1018567881,'2015-12-12','2017-06-16','AFILIADO ACTIVO')");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        stmt.execute("INSERT INTO Pago_Cuota (`PagoID`,`Fecha_Cobro`,`Valor_Pago`,`DocumentoID`,`Estado`,`Fecha_Pago`,`Comprobante_Pago`)");
        
        /*
        ResultSet rs = SAGECI.consultarAfiliacionesPorVencer();
        */
    /*
        conn.commit();
        conn.close();
        assertEquals("Deben haber 3 persnas egresados ya que los estudiantes no pagan cuota de afiliacion. ",true,true);
        
    }
    */
}
