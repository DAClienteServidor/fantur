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
    
    private String consulta;
   
   private List<Alojamiento> alojamientos;
   private List<Entretenimiento> entretenimientos;
   private List<Pasaje> pasajes;
    
    @EJB
    private EntretenimientoInterface ejbEntretenimiento;
    
            

    private Paquete paquete1;

    
    @PostConstruct
    public void init() {
        paquete = ejbPaquete.findAll();
        //this.paq = null;
        paquete1 = new Paquete();
        alojamientos = ejbAlojamiento.findAll();
        entretenimientos = ejbEntretenimiento.findAll();
        pasajes = ejbPasaje.findAll();
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

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(List<Alojamiento> alojamientos) {
        this.alojamientos = alojamientos;
    }

    public List<Entretenimiento> getEntretenimientos() {
        return entretenimientos;
    }

    public void setEntretenimientos(List<Entretenimiento> entretenimientos) {
        this.entretenimientos = entretenimientos;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPasajes(List<Pasaje> pasajes) {
        this.pasajes = pasajes;
    }
    
    //String de consulta

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = "%"+consulta+"%";
    }
    
    //Leer de cada objeto
    public void leerEnt(Entretenimiento ent){
        this.paquete1.setEntretenimiento(ent);
    }   
    
    public void leerPas(Pasaje pas){
        this.paquete1.setPasaje(pas);
    } 
    
    public void leerAlo(Alojamiento aloj){
        this.paquete1.setAlojamiento(aloj);
    } 
    
    //Actualizar tablas
    public void actualizarAlojamientos(){
        this.alojamientos = ejbAlojamiento.findByAlgo(consulta);
    }
    
    public void actualizarPasajes(){
        this.pasajes = ejbPasaje.findByAlgo(consulta);
    }
    
    public void actualizarEntretenimientos(){
        this.entretenimientos = ejbEntretenimiento.findByAlgo(consulta);
    }
   
     public  void leerPaqSelct(Paquete paqueteSelec){
        paquete1 = paqueteSelec;
    }
    
    public void modificarPaquete(){
        ejbPaquete.edit(paquete1);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se Modifico correctamente"));    
        
    } 
    
    public void EliminarPaquete(){
            ejbPaquete.remove(paquete1);
            paquete = ejbPaquete.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se Elimino correctamente"));
     
    }
    
    public void nuevoPaquete() {  
            ejbPaquete.create(paquete1);
            paquete = ejbPaquete.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se creo correctamente"));
            
        
    }
}
