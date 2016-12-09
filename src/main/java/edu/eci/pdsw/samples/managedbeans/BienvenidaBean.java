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
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author 2107713
 */
@ManagedBean (name="Bienvenida")
@SessionScoped
public class BienvenidaBean implements Serializable {
     
    private List<String> images;
     
    public BienvenidaBean() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 15; i++) {
            if(i<=8){
                images.add("nature" + i + ".jpg");
            }
            else if(i>8 && i<=14){
                images.add("nature" + i + ".png");
            }
        }
    }
 
    public List<String> getImages() {
        return images;
    }
}