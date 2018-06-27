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
import modelo.Alojamiento;
import modelo.Entretenimiento;
import modelo.Paquete;
import modelo.Pasaje;

@ManagedBean(name = "PaqController")
@SessionScoped
public class PaquetesController implements Serializable {
    
    @EJB
    private PaqueteInterface ejbPaquete;
    private List<Paquete> paquete;
    
    private Paquete paquete1;
    Paquete paquetenue = new Paquete();

    
    
 
    
    @PostConstruct
    public void init(){
        paquete = ejbPaquete.findAll();
        paquete1 = new Paquete();
       
    }
    
    public List<Paquete> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<Paquete> paquete) {
        this.paquete = paquete;
    }
    
    // getter y setter

    public Paquete getPaquete1() {
        return paquete1;
    }

    public void setPaquete1(Paquete paquete1) {
        this.paquete1 = paquete1;
    }


    public Paquete getPaquetenue() {
        return paquetenue;
    }

    public void setPaquetenue(Paquete paquetenue) {
        this.paquetenue = paquetenue;
    }
    
    
    
    
    //-----------------------------
    
    public  void leer(Paquete paqueteSelec){
        paquete1 = paqueteSelec;
    }
    
    public void modificarPaquete(){
        ejbPaquete.edit(paquete1);
    } 
    
    public void EliminarPaquete(){
            try {
            ejbPaquete.remove(paquete1);
        } catch (Exception e) {
        }
    }
    
    public void nuevoPaquete() {        
            paquetenue = paquete1;
            ejbPaquete.create(paquetenue);   
    }
    
    
    }
    
