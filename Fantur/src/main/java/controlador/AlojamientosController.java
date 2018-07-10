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
import javax.faces.bean.ViewScoped;
import modelo.Alojamiento;
import service.AlojamientoFacadeREST;
/**
 *
 * @author usuario
 */
@ManagedBean(name = "AloController")
@ViewScoped
public class AlojamientosController {
    @EJB
    private AlojamientoInterface AlojWS;
    private List<Alojamiento> alojamiento;
    
    private Alojamiento aloj;
    
    @PostConstruct
    public void init(){

        try {
            alojamiento = AlojWS.findAll();
            aloj = new Alojamiento();
        } catch (Exception e) {
        }

        alojamiento = AlojWS.findAll();

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
            AlojWS.create(aloj);
            alojamiento = AlojWS.findAll();
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
                AlojWS.edit(aloj);
            } catch (Exception e) {
            }
        }
    
        public void EliminarAlojamiento(){
            try {
                AlojWS.remove(aloj);
                alojamiento = AlojWS.findAll();
            } catch (Exception e) {
            }
        }
        

    
    

}
