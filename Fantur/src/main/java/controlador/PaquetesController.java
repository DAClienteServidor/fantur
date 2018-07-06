/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.AlojamientoInterface;
import dao.ContrataInterface;
import dao.EntretenimientoInterface;
import dao.PaqueteInterface;
import dao.PasajeInterface;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Alojamiento;
import modelo.Entretenimiento;
import modelo.Paquete;
import modelo.Pasaje;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

@ManagedBean(name = "PaqController")
@SessionScoped
public class PaquetesController implements Serializable {

    @EJB
    private ContrataInterface ejbCon;
    
    @EJB
    private PasajeInterface ejbPasaje;

    @EJB
    private PaqueteInterface ejbPaquete;
    
    @EJB
    private AlojamientoInterface ejbAlojamiento;
    private List<Paquete> paquete;
    private Paquete paq;
    private Entretenimiento ent;
    private Pasaje pas;
    private Alojamiento aloj;
    
    private String entId;
    private String alojId;
    private String pasId;
    
    @EJB
    private EntretenimientoInterface ejbEntretenimiento;
    
            

    private Paquete paquete1;

    
    @PostConstruct
    public void init() {
        paquete = ejbPaquete.findAll();
        //this.paq = null;
        paquete1 = new Paquete();



    }
    

    public List<Paquete> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<Paquete> paquete) {
        this.paquete = paquete;
    }

    public void leer(Paquete paq) {
        paquete1 = paq;
    }

    public Paquete getPaq() {
        return paq;
    }

    public void setPaq(Paquete paq) {
        this.paq = paq;
    }

     // getter y setter 
    public Paquete getPaquete1() {
        return paquete1;
    }

    public void setPaquete1(Paquete paquete1) {
        this.paquete1 = paquete1;
    }


    public Entretenimiento getEnt() {
        return ent;
    }

    public void setEnt(Entretenimiento ent) {
        this.ent = ent;
    }

    public Pasaje getPas() {
        return pas;
    }

    public void setPas(Pasaje pas) {
        this.pas = pas;
    }

    public Alojamiento getAloj() {
        return aloj;
    }

    public void setAloj(Alojamiento aloj) {
        this.aloj = aloj;
    }
    
    
     //------------------------------------------------------

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getAlojId() {
        return alojId;
    }

    public void setAlojId(String alojId) {
        this.alojId = alojId;
    }

    public String getPasId() {
        return pasId;
    }

    public void setPasId(String pasId) {
        this.pasId = pasId;
    }


    
    
    
     public  void leerPaqSelct(Paquete paqueteSelec){
        paquete1 = paqueteSelec;
    }
    
    public void modificarPaquete(){
        paquete1.setPasaje(pas);
        paquete1.setAlojamiento(aloj);
        paquete1.setEntretenimiento(ent);
        ejbPaquete.edit(paquete1);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se Modifico correctamente"));    
       
    } 
    
    public void EliminarPaquete(){
            ejbPaquete.remove(paquete1);
            paquete = ejbPaquete.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se Elimino correctamente"));
     
    }
    
    public void nuevoPaquete() {  
            paquete1.setPasaje(pas);
            paquete1.setAlojamiento(aloj);
            paquete1.setEntretenimiento(ent);
            ejbPaquete.create(paquete1);
            paquete = ejbPaquete.findAll();
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se creo correctamente"));
            
        
    }
    
    //-------------------------------------------------------
    
    public void cargarEntretenimiento(){
        ent = new Entretenimiento();
        try {
            Entretenimiento entre = ejbEntretenimiento.find(Integer.parseInt(entId));
            this.ent = entre;
        } catch (Exception e) {
            System.out.print("Me tiene podrido esta poronga en entretenimiento");
        }
    }
    
    public void cargarPasaje(){
        pas = new Pasaje();
        try { 
            this.pas = ejbPasaje.find(Integer.parseInt(pasId));
            
        } catch (Exception e) {
            System.out.print("Me tiene podrido esta poronga en pasaje");
        }
    }
    
    public void cargarAlojamiento(){
        aloj = new Alojamiento();
        try {
            this.aloj = ejbAlojamiento.find(Integer.parseInt(alojId));
        } catch (Exception e) {
            System.out.print("Me tiene podrido esta poronga en Alojamiento");
        }
    }
    
    



    
    
    
}
