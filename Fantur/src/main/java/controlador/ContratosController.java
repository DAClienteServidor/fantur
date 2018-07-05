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
import ws.validar;

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
        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
        contrata = ejbCon.findByUsu(us.getDni());
    }

    public String cargar(Paquete paq) {
        contrato = new Contrata();
        contrato.setContrataPK(new modelo.ContrataPK());

        FacesContext context = FacesContext.getCurrentInstance();
        Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        validar val = new validar();
        contrato.setPermitido(val.getVal(us.getDni()));
        contrato.setPaquete1(paq);
        contrato.setUsuario1(us);    
        contrato.setPagado("Si");
        contrato.setFechaContrato(new Date());

        contrato.getContrataPK().setUsuario(contrato.getUsuario1().getDni());
        contrato.getContrataPK().setPaquete(contrato.getPaquete1().getIdpaquete());
        ejbCon.create(contrato);

        MailController email = new MailController();
        email.setDestinatarios(us.getEmail());
        email.setCheck("No");
        email.setMensaje("Paquete "+ paq.getNombre() + "comprado con exito");
        email.setTitulo("Compra de paquete");
        email.send();
        contrata = ejbCon.findByUsu(us.getDni());
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
