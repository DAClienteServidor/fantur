package controlador;

import dao.UsuarioInterface;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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

    @PostConstruct
    public void init() {
        usuario = ejbUsuario.findAll();
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
            String emisor = "fantur.turismo@gmail.com";
            String clave = "fantur18";

            Properties props = System.getProperties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.fallback", "false");

            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(true);

            Usuario items;

            if (check.equals("Si")) {
                for (int i = 0; i < this.usuario.size(); i++) {
                    
                    items = this.usuario.get(i);
                    
                    Message mailMessage = new MimeMessage(mailSession);
                    mailMessage.setFrom(new InternetAddress(emisor));
                    mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(items.getEmail()));
                    mailMessage.setContent(this.mensaje, "text/html");
                    mailMessage.setSubject(this.titulo);

                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect("smtp.gmail.com", emisor, clave);
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                     
                }
               
            } else {
                Message mailMessage = new MimeMessage(mailSession);
                mailMessage.setFrom(new InternetAddress(emisor));
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(this.destinatarios));
                mailMessage.setContent(this.mensaje, "text/html");
                mailMessage.setSubject(this.titulo);

                Transport transport = mailSession.getTransport("smtp");
                transport.connect("smtp.gmail.com", emisor, clave);
                transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                
            }
        } catch (Exception e) {
        }
    }

}
