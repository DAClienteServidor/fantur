/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ContrataInterface;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Contrata;
import modelo.Paquete;
import modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "ConController")
@SessionScoped
public class ContratosController {

    @EJB
    private ContrataInterface ejbCon;
    private List<Contrata> contrata;
    private Contrata contrato;

    protected static final Logger logger = LoggerFactory.getLogger(ContratosController.class);

    @PostConstruct
    public void init() {
        contrata = ejbCon.findAll();
    }

    public String cargar(Paquete paq) {
        contrato = new Contrata();
        contrato.setContrataPK(new modelo.ContrataPK());
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        
        logger.info(String.format("Llega usuario", paq.getNombre()));
        contrato.setPaquete1(paq);
        logger.info(String.format("Anda paquete", paq.getIdpaquete()));
        
        logger.info(String.format("Llega usuario", us.getNombre()));
        contrato.setUsuario1(us);
        logger.info(String.format("Anda usuario", us.getNombre()));
        contrato.setPagado("Si");
        contrato.setPermitido("Si");
        contrato.setFechaContrato(new Date());

        contrato.getContrataPK().setUsuario(contrato.getUsuario1().getDni());
        contrato.getContrataPK().setPaquete(contrato.getPaquete1().getIdpaquete());
        ejbCon.create(contrato);
        return "./prueba.xhtml";
    }

    public List<Contrata> getContrato() {
        return contrata;
    }

    public void setContrato(List<Contrata> con) {
        this.contrata = con;
    }

    public Contrata getCon() {
        return contrato;
    }

    public void setCon(Contrata con) {
        this.contrato = con;
    }

}
