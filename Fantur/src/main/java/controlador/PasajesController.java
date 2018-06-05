/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PasajeInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pasaje;

/**
 *
 *
 */
@ManagedBean(name = "PasController")
@SessionScoped
public class PasajesController {
       
    @EJB
    private PasajeInterface ejbPasaje;
    private List<Pasaje> pasaje;
    
    @PostConstruct
    public void init(){
        pasaje = ejbPasaje.findAll();
    }
    
    public List<Pasaje> getPasaje() {
        return pasaje;
    }

    public void setPasaje(List<Pasaje> pasaje) {
        this.pasaje = pasaje;
    }
    
}
