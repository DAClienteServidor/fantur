package controlador;

import dao.UsuarioInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;

@ManagedBean(name = "mailControl")
@SessionScoped
public class MailController {

    private String titulo;
    private String mensaje;
    private String destinatarios;
    private String check;

    @EJB
    private UsuarioInterface ejbUsuario;
    private List<Usuario> usuario;
    
    @EJB
    private Email email;

    @PostConstruct
    public void init() {
        
        //this.paq = null;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public void send() {
        try {  
            email.send(this.check, this.mensaje, this.titulo, this.destinatarios);
        } catch (Exception e) {
        }
    }

}
