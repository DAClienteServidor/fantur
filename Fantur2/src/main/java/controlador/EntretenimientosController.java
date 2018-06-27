/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.EntretenimientoInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Entretenimiento;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "EntController")
@SessionScoped
public class EntretenimientosController implements Serializable{
    
    @EJB
    private EntretenimientoInterface ejbEnt;
    private List<Entretenimiento> entretenimiento;
    
    @PostConstruct
    public void init(){
        try {
                    entretenimiento = ejbEnt.findAll();
            
        } catch (Exception e) {
        }

    }
    
    public List<Entretenimiento> getEntretenimiento() {
        return entretenimiento;
    }

    public void setEntretenimiento(List<Entretenimiento> ent) {
        this.entretenimiento = ent;
    }
    
}
