package edu.eci.pdsw.samples.managedbeans;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.pdf.PdfWriter;
import edu.eci.pdsw.samples.entities.Egresado;
import edu.eci.pdsw.samples.entities.Estudiante;
import edu.eci.pdsw.samples.entities.PagoAfiliacion;
import edu.eci.pdsw.samples.entities.Persona;
import edu.eci.pdsw.samples.entities.estadoAfiliacion;
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
    estadoAfiliacion eaf;
    PagoAfiliacion paf;

    public PagoAfiliacion getPaf() {
        return paf;
    }

    public void setPaf(PagoAfiliacion paf) {
        this.paf = paf;
    }

    public boolean isDocumento() {
        return documento;
    }

    public void setDocumento(boolean documento) {
        this.documento = documento;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public estadoAfiliacion getEaf() {
        System.out.println(eaf.getEstado());
        return eaf;
    }

    public void setEaf(estadoAfiliacion eaf) {
        this.eaf = eaf;
    }

    @PostConstruct
    public void init() {
        try {
            //----------------------------------
            Document doc = new Document();
            Rectangle rect=new Rectangle(PageSize.A4);
            doc.setPageSize(rect);
            OutputStream out = new ByteArrayOutputStream();
            PdfWriter writer = PdfWriter.getInstance(doc, out);
            doc.open();
            LogginBean bean = (LogginBean) getManagedBean("Loggin");
            documentoID = Integer.parseInt(bean.getUsername());
            /**
             doc.add(new Paragraph("image example")); //Add image 
             Image image1=Image.getInstance("aecimagen.png"); //Fixed position
             image1.setAbsolutePosition(100f, 100f); //scale to new height...
             image1.scaleAbsolute(200,200); //add to document 
             doc.add(image1);
             **/
            doc.add(new Paragraph("CERTIFICADO DE AFILIACION AECI"));
            doc.addCreationDate();
            doc.addTitle("La Asociación de Egresados de la Escuela Colombiana de Ingeniería Julio Garavito AECI,con Nit. 830.031.137-4,certifica que");
            if (bean.getTipo().equals("Estudiante")) {
                estudiante = SAGECI.consultarEstudiante(documentoID);
                setPersona(estudiante);
            } else {
                egresado = SAGECI.consultarEgresado(documentoID);
                setPersona(egresado);
            }
            plantillaEst = "Que el Estudiante $1, identificado con $2  No. $3, cursando actualmente $5 semestre del programa de $4, está afiliado en la asociación de Estudiantes de la escuela colombiana de ingeniería Julio Garavito desde $6 hasta $7, que cuenta con una afiliación gratuita de 6 meses dada su condición de estudiante activo. "
                    +" "
                    + ""+ " Es de anotar que para disfrutar de los convenios a los cuales  tiene derecho es necesario que su afiliación permanezca vigente realizando  el correspondiente pago anual./n El presente certificado se expide con destino a los convenios de asociados a la  AECI en Bogotá el día $8.";
            plantillaEgr = "Que el Egresado $1, identificado con $2  No. $3, Egresado del periodo $4,  se encuentra afiliado en la asociación de egresados de la escuela colombiana de ingeniería Julio Garavito desde $5 hasta $6 \n" + "\n" + "Que la presente constancia se expide a solicitud del interesado.";
            setEaf(SAGECI.consultarEstadoAfiliacion(documentoID));
            if (bean.getTipo().equals("Estudiante")) {
                plantillaEst = plantillaEst.replace("$1", estudiante.getApellido() + " " + estudiante.getNombre());
                plantillaEst = plantillaEst.replace("$2", estudiante.getTipoDocumentoID());
                plantillaEst = plantillaEst.replace("$3", Integer.toString(documentoID));
                plantillaEst = plantillaEst.replace("$4", estudiante.getCarrera());
                plantillaEst = plantillaEst.replace("$5", Integer.toString(estudiante.getSemestrePonderado()));
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date fecha = eaf.getFechaInicio();
                plantillaEst = plantillaEst.replace("$6", df.format(fecha));
                Date fechafin = eaf.getFechaFin();
                plantillaEst = plantillaEst.replace("$7", df.format(fechafin));
                boolean fechaExp = doc.addCreationDate();
                //plantillaEst=plantillaEst.replace("$8",toString(fechaExp));
                doc.add(new Paragraph(plantillaEst));
            } else {
                plantillaEgr = plantillaEgr.replace("$1", egresado.getApellido() + " " + egresado.getNombre());
                plantillaEgr = plantillaEgr.replace("$2", egresado.getTipoDocumentoID());
                plantillaEgr = plantillaEgr.replace("$3", Integer.toString(documentoID));
                plantillaEgr = plantillaEgr.replace("$4", egresado.getSemestreGrado());
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                Date fecha = eaf.getFechaInicio();
                plantillaEgr = plantillaEgr.replace("$5", df.format(fecha));
                Date fechafin = eaf.getFechaFin();
                plantillaEgr = plantillaEgr.replace("$6", df.format(fechafin));
                doc.add(new Paragraph(plantillaEgr));

            }

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
