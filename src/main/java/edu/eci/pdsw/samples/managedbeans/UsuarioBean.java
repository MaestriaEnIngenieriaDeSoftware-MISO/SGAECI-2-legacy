package edu.eci.pdsw.samples.managedbeans;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.services.ServiciosSAGECI;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.faces.FacesException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Ricardo
 * @author Cristian
 */
@ManagedBean(name = "Usuario")
@RequestScoped
public class UsuarioBean {

    ServiciosSAGECI SAGECI = ServiciosSAGECI.getInstance();
    private static final long serialVersionUID = 1L;

    private StreamedContent streamedContent;
    private boolean documento = false;
    Estudiante estudiante;
    Egresado egresado;
    Persona persona;
    private int documentoID;
    String plantillaEst, plantillaEgr, plantillaRG;

    public boolean isDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    @PostConstruct
    public void init() {
        try {
            //----------------------------------
            Document doc = new Document();
            OutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();
            LogginBean bean = (LogginBean) getManagedBean("Loggin");
            documentoID = Integer.parseInt(bean.getUsername());
            // Falta anexar el codigo del documento
            doc.add(new Paragraph("Bogotá DC"));
            doc.addCreationDate();
            // Image imagen = Image.getInstance("aecimagen.png");
            //  imagen.setAbsolutePosition(450f, 10f);
            //  doc.add(imagen);
            doc.addTitle("EL SUSCRITO SECRETARIO DE LA ASOCIACIÓN DE EGRESADOS DE LA ESCUELA COLOMBIANA DE INGENIERÍA HACE CONSTAR:");
            plantillaEst = "Que el estudiante $1, identificado con $2  No. $3, estudiante de $5 semestre del programa de $4,  se encuentra afiliado en la asociación de egresados de la escuela colombiana de ingeniería Julio Garavito desde $6 hasta $7, que cuenta con una afiliación gratuita de 6 meses dada su condición de estudiante activo. \n Que la presente constancia se expide a solicitud del interesado.";
            persona = 
            /**/
            //if(true){
            //System.out.println("error antes consultar el del estudiante");
            estudiante = SAGECI.consultarEstudiante(documentoID);
            System.out.println("error despues de consultar del estudiante");
            System.out.println(estudiante);

            plantillaEst = "Que el estudiante $1, identificado con $2  No. $3, estudiante de $5 semestre del programa de $4,  se encuentra afiliado en la asociación de egresados de la escuela colombiana de ingeniería Julio Garavito desde $6 hasta $7, que cuenta con una afiliación gratuita de 6 meses dada su condición de estudiante activo.\n"
                    + "\n"
                    + "Que la presente constancia se expide a solicitud del interesado.";
            plantillaEst.replaceAll("$1", estudiante.getApellido() + " " + estudiante.getNombre());
            plantillaEst.replaceAll("$2", estudiante.getTipoDocumentoID());
            plantillaEst.replaceAll("$3", Integer.toString(documentoID));
            plantillaEst.replaceAll("$4", estudiante.getCarrera());
            plantillaEst.replaceAll("$5", Integer.toString(estudiante.getSemestrePonderado()));
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date fecha = SAGECI.consultarEstadoAfiliacion(documentoID).getFechaInicio();
            plantillaEst.replaceAll("$6", df.format(fecha));
            Date fechafin = SAGECI.consultarEstadoAfiliacion(documentoID).getFechaFin();
            plantillaEst.replaceAll("$7", df.format(fechafin));
            doc.add(new Paragraph(plantillaEst));
            doc.close();
            out.close();

            InputStream in = new ByteArrayInputStream(((ByteArrayOutputStream) out).toByteArray());

            streamedContent = new DefaultStreamedContent(in, "application/pdf", "Certificado.pdf");
            //-------
            Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            byte[] b = (byte[]) session.get("reportBytes");
            if (b != null) {
                streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(b), "application/pdf", "Certificado.pdf");
            }
        } catch (Exception e) {
            System.out.println("entro por un error " + e.getMessage());
        }

    }

    //==================================================================
    public StreamedContent getStreamedContent() {
        if (FacesContext.getCurrentInstance().getRenderResponse()) {
            return new DefaultStreamedContent();
        } else {
            return streamedContent;
        }
    }

    //==================================================================
    public void setStreamedContent(StreamedContent streamedContent) {
        this.streamedContent = streamedContent;
    }
    //=====================================================================

    public static Object getManagedBean(final String beanName) {
        FacesContext fc = FacesContext.getCurrentInstance();

        Object bean;
        try {
            ELContext elContext = fc.getELContext();
            bean = elContext.getELResolver().getValue(elContext, null, beanName);
        } catch (RuntimeException e) {
            throw new FacesException(e.getMessage(), e);

        }
        return bean;
    }

}
