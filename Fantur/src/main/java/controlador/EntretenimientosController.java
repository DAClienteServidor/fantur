/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EntretenimientoInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import modelo.Entretenimiento;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "EntController")
@ViewScoped
public class EntretenimientosController {
    
    @EJB
    private EntretenimientoInterface ejbEnt;
    private List<Entretenimiento> entretenimiento;
    
    private Entretenimiento ent;
    
    @PostConstruct
    public void init(){

        try {
                    entretenimiento = ejbEnt.findAll();
                    ent = new Entretenimiento();
                    
            
        } catch (Exception e) {
        }

        entretenimiento = ejbEnt.findAll();

    }
    
    public List<Entretenimiento> getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(List<Entretenimiento> ent) {
        this.entretenimiento = ent;
    }
    
    //-------------------------------------------------------------
       public Entretenimiento getEnt() {
        return ent;
    }

    public void setEnt(Entretenimiento ent) {
        this.ent = ent;
    }
    
    //------------------------------------------------------------


        
    public void resgistrarNuevoEntretenimiento(){
        try {
            ejbEnt.create(ent);
            entretenimiento = ejbEnt.findAll();
        } catch (Exception e) {
        } 
    }
    
        public void leer(Entretenimiento ent2) {
        ent= ent2;
    }

        public  void ModificarEntretenimiento() {
            try {
                ejbEnt.edit(ent);
            } catch (Exception e) {
            }
        }
    
        public void EliminarEntretenimiento(){
            try {
                ejbEnt.remove(ent);  
                entretenimiento = ejbEnt.findAll();
            } catch (Exception e) {
            }
        }
    
    
    
}
