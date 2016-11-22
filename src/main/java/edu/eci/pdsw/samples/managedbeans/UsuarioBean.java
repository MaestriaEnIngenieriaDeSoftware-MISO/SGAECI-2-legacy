package edu.eci.pdsw.samples.managedbeans;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author alfa
 */
@ManagedBean (name= "Usuario")
@RequestScoped
public class UsuarioBean {

    private static final long serialVersionUID = 1L;

    private StreamedContent streamedContent;
    private boolean documento=false;

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
            doc.add(new Paragraph("Este es el certificado Bla Bla Bla"));
            doc.close(); 
            out.close();

            InputStream in =new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());

            streamedContent = new DefaultStreamedContent(in, "application/pdf", "Certificado.pdf");
        //-------
        Map<String, Object> session = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        byte[] b = (byte[]) session.get("reportBytes");
        if (b != null) {
            streamedContent = new DefaultStreamedContent(new ByteArrayInputStream(b), "application/pdf", "Certificado.pdf");
        }            
        } catch (Exception e) {
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

}