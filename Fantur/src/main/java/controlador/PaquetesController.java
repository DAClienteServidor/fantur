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
import modelo.Contrata;
import modelo.ContrataPK;
import modelo.Paquete;
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

    @PostConstruct
    public void init() {
        paquete = ejbPaquete.findAll();
        //this.paq = null;
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
