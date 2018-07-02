/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ContrataInterface;
import dao.PaqueteInterface;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Alojamiento;
import modelo.Contrata;
import modelo.ContrataPK;
import modelo.Entretenimiento;
import modelo.Paquete;
import modelo.Pasaje;
import modelo.Usuario;

@ManagedBean(name = "PaqController")
@SessionScoped
public class PaquetesController implements Serializable {

    @EJB
    private ContrataInterface ejbCon;

    @EJB
    private PaqueteInterface ejbPaquete;
    private List<Paquete> paquete;
    private Paquete paq;
    private Entretenimiento ent;
    private Pasaje pas;
    private Alojamiento aloj;
            

    private Paquete paquete1;
    Paquete paquetenue = new Paquete();

    
    @PostConstruct
    public void init() {
        paquete = ejbPaquete.findAll();
        //this.paq = null;
        paquete1 = new Paquete();
        ent = new Entretenimiento();
        pas = new Pasaje();
        aloj = new Alojamiento();
    }
    

    public List<Paquete> getPaquete() {
        return paquete;
    }

    public void setPaquete(List<Paquete> paquete) {
        this.paquete = paquete;
    }

    public void leer(Paquete paq) {
        this.paq = paq;
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

    public Paquete getPaquetenue() {
        return paquetenue;
    }

    public void setPaquetenue(Paquete paquetenue) {
        this.paquetenue = paquetenue;
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
    
     public  void leerPaqSelct(Paquete paqueteSelec){
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
    
    //-------------------------------------------------------
    

    /** public String cargar() {
        ContrataPK pk = new ContrataPK();
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        pk.setPaquete(this.paq.getIdpaquete());
        System.out.println(pk.getPaquete());
        pk.setUsuario(us.getUsuario());
        contrato.setContrataPK(pk);
        contrato.setPaquete1(this.paq);
        contrato.setPagado("Si");

        contrato.setUsuario1(us);
        contrato.setPermitido("Si");
        contrato.setFechaContrato(new Date());
        ejbCon.create(contrato);

        return "./index.xhtml";
    } **/

    
    
    
}
