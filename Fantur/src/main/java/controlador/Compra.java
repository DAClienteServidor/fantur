package controlador;

import dao.ContrataInterface;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Contrata;
import modelo.Paquete;
import modelo.Usuario;
import ws.validar;

@Stateless
public class Compra {

    @EJB
    private ContrataInterface ejbCon;

    @EJB
    private Email email;

    public void cargarCompra(Paquete paq, Usuario us) {
        try {
            Contrata contrato = new Contrata();
            contrato.setContrataPK(new modelo.ContrataPK());

            validar val = new validar();
            contrato.setPermitido(val.getVal(us.getUsuario()));
            contrato.setPaquete1(paq);
            contrato.setUsuario1(us);
            contrato.setPagado("Si");
            contrato.setFechaContrato(new Date());

            contrato.getContrataPK().setUsuario(contrato.getUsuario1().getDni());
            contrato.getContrataPK().setPaquete(contrato.getPaquete1().getIdpaquete());
            ejbCon.create(contrato);

            String mensaje = "Paquete " + paq.getNombre() + "comprado con exito";

            email.send("No", mensaje, "Compra de paquete", us.getEmail());
        } catch (Exception e) {
        }
    }
}
