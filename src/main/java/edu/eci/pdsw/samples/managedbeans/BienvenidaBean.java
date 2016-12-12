/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.pdsw.samples.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import edu.eci.pdsw.samples.entities.imagen;

/**
 *
 * @author 2107713
 */
@ManagedBean (name="Bienvenida")
@SessionScoped
public class BienvenidaBean implements Serializable {
     
    private List<imagen> images;
     
    public BienvenidaBean() {
        images = new ArrayList<>();
        images.add(new imagen("nature1.jpg", "Tech Movil"));
        images.add(new imagen("nature2.jpg", "Centro Seguros"));
        images.add(new imagen("nature3.jpg", "Inaseg LTDA"));
        images.add(new imagen("nature4.jpg", "Education First"));
        images.add(new imagen("nature5.jpg", "Escuela Colombiana de Ingeniería Julio Garavito"));
        images.add(new imagen("nature6.jpg", "ColSanitas"));
        images.add(new imagen("nature7.jpg", "UltraBox"));
        images.add(new imagen("nature8.jpg", "Casa Editorial EL TIEMPO"));
        images.add(new imagen("nature9.png", "Passport Language Centers"));
        images.add(new imagen("nature10.png", "E-vocs"));
        images.add(new imagen("nature11.png", "Autoniza Chevrolet"));
        images.add(new imagen("nature12.png", "EUDE Business School"));
        images.add(new imagen("nature13.png", "Magna Green Group"));
        images.add(new imagen("nature14.png", "LEGIS"));
        images.add(new imagen("nature11.png", "Autoniza Chevrolet"));
        
    }
 
    public List<imagen> getImages() {
        return images;
    }

    public void setImages(List<imagen> images) {
        this.images = images;
    }
    
}