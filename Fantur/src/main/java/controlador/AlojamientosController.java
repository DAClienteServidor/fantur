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
import modelo.Pasaje;
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
    
    private Alojamiento aloj;
    
    @PostConstruct
    public void init(){

        try {
            alojamiento = ejbAlo.findAll();
            aloj = new Alojamiento();
        } catch (Exception e) {
        }

        alojamiento = ejbAlo.findAll();

    }
    
    public List<Alojamiento> getAlojamiento() {
        return alojamiento;
    }

    public void setAlojamiento(List<Alojamiento> alo) {
        this.alojamiento = alo;
    }
    
   //--------------------------------------------

    public Alojamiento getAloj() {
        return aloj;
    }

    public void setAloj(Alojamiento aloj) {
        this.aloj = aloj;
    }
    //-------------------------------------------
    
        
    public void resgistrarNuevoAlojamiento(){
        try {
            ejbAlo.create(aloj);
            alojamiento = ejbAlo.findAll();
        } catch (Exception e) {
        } 
    }
    
        public void leer(Alojamiento alojamiento2) {
            try {
               aloj = alojamiento2;
            } catch (Exception e) {
            }

    }

        public  void ModificarAlojamiento() {
            try {
                ejbAlo.edit(aloj);
            } catch (Exception e) {
            }
        }
    
        public void EliminarAlojamiento(){
            try {
                ejbAlo.remove(aloj);
                alojamiento = ejbAlo.findAll();
            } catch (Exception e) {
            }
        }
        

    
    

}
