/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PaqueteInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Paquete;

@ManagedBean(name = "PaqController")
@SessionScoped
public class PaquetesController implements Serializable {
    
    @EJB
    private PaqueteInterface ejbPaquete;
    private List<Paquete> paquete;
    
    @PostConstruct
    public void init(){
        paquete = ejbPaquete.findAll();
    }
    
    public List<Paquete> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<Paquete> paquete) {
        this.paquete = paquete;
    }
}
