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
import service.PasajeFacadeREST;

/**
 *
 *
 */
@ManagedBean(name = "PasController")
@ViewScoped
public class PasajesController {
       
    @EJB
    private PasajeFacadeREST PasajeWS;
    private List<Pasaje> pasaje;
    
    private Pasaje pasaje1;
    private int id2;
    
    
    @PostConstruct
    public void init(){
        pasaje = PasajeWS.findAll();
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
            PasajeWS.create(pasaje1);
            pasaje = PasajeWS.findAll();
        } catch (Exception e) {
        } 
    }
    
        public void leer(Pasaje pasaje2) {
        pasaje1 = pasaje2;
    }

        public  void ModificarPasaje() {
            try {
                PasajeWS.edit(pasaje1);
            } catch (Exception e) {
            }
        }
    
        public void EliminarPasaje(){
            try {
                PasajeWS.remove(pasaje1);
                pasaje = PasajeWS.findAll();
            } catch (Exception e) {
            }
        }
    
}
