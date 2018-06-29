package controlador;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import mail.MailSender;


@ManagedBean(name = "mailControl")
@SessionScoped
public class MailController {
    
    private String titulo;
    private String mensaje;
    private String destinatarios;
    private String check;

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
    
    public void send(){
        try {
            MailSender mailSender = new MailSender();
            mailSender.sendMail(titulo, mensaje, destinatarios);
        } catch (Exception e) {
        }
    }
    
}
