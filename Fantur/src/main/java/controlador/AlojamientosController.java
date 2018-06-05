/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlojamientoInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Alojamiento;
/**
 *
 * @author usuario
 */
@ManagedBean(name = "AloController")
@SessionScoped
public class AlojamientosController {
      @EJB
    private AlojamientoInterface ejbAlo;
    private List<Alojamiento> alojamiento;
    
    @PostConstruct
    public void init(){
        alojamiento = ejbAlo.findAll();
    }
    
    public List<Alojamiento> getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(List<Alojamiento> alo) {
        this.alojamiento = alo;
    }
    
}
