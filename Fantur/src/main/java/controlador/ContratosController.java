package controlador;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Contrata;
import modelo.Paquete;
import modelo.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ContrataFacadeREST;

@ManagedBean(name = "ConController")
@ViewScoped
public class ContratosController {

    @EJB
    private ContrataFacadeREST ContrataWS;
    
    @EJB
    private Compra ejbcompra;
    private List<Contrata> contrata;
    private Contrata contrato;

    protected static final Logger logger = LoggerFactory.getLogger(ContratosController.class);

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        contrata = ContrataWS.findByUsu(us.getDni());
    }
    
    public String cargar(Paquete paq) {
        
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        ejbcompra.cargarCompra(paq,us);
        contrata = ContrataWS.findByUsu(us.getDni());
        return "./misPaquetes.xhtml?faces-redirect=true";
    }

    public List<Contrata> getContrata() {
        return contrata;
    }

    public void setContrata(List<Contrata> con) {
        this.contrata = con;
    }

    public Contrata getCon() {
        return contrato;
    }

    public void setCon(Contrata con) {
        this.contrato = con;
    }

}
