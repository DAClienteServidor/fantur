/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.PasajeInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Pasaje;

import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import java.text.SimpleDateFormat;
import javax.faces.application.FacesMessage;
/**
 *
 *
 */
@ManagedBean(name = "PasController")
@SessionScoped
public class PasajesController implements Serializable{
       
    @EJB
    private PasajeInterface ejbPasaje;
    private List<Pasaje> pasaje;
    
    private Pasaje pasaje1;
    private int id2;
    
    
    @PostConstruct
    public void init(){
        try {
            pasaje = ejbPasaje.findAll();
            pasaje1 = new Pasaje();
            
        } catch (Exception e) {
        }
        
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
            } catch (Exception e) {
            }
        }
        
        //-------------------
        public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
}
