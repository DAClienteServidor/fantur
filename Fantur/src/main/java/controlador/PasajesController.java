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
import javax.faces.bean.ViewScoped;
import modelo.Pasaje;

/**
 *
 *
 */
@ManagedBean(name = "PasController")
@ViewScoped
public class PasajesController {
       
    @EJB
    private PasajeInterface ejbPasaje;
    private List<Pasaje> pasaje;
    
    private Pasaje pasaje1;
    private int id2;
    
    
    @PostConstruct
    public void init(){
        pasaje = ejbPasaje.findAll();
        pasaje1 = new Pasaje();
    }
    
    public List<Pasaje> getPasaje() {
        return pasaje;
    }

    public void setPasaje(List<Pasaje> pasaje) {
        this.pasaje = pasaje;
    }
    
    // Getter y setter de pasaje1

    public Pasaje getPasaje1() {
        return pasaje1;
    }

    public void setPasaje1(Pasaje pasaje1) {
        this.pasaje1 = pasaje1;
    }
    
    
    //------------------------------------
    
    public void resgistrarNuevoPasaje(){
        try {
            ejbPasaje.create(pasaje1);
            pasaje = ejbPasaje.findAll();
        } catch (Exception e) {
        } 
    }
    
        public void leer(Pasaje pasaje2) {
        pasaje1 = pasaje2;
    }

        public  void ModificarPasaje() {
            try {
                ejbPasaje.edit(pasaje1);
            } catch (Exception e) {
            }
        }
    
        public void EliminarPasaje(){
            try {
                ejbPasaje.remove(pasaje1);
                pasaje = ejbPasaje.findAll();
            } catch (Exception e) {
            }
        }
    
}
