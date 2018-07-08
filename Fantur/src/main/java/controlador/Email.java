/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioInterface;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import modelo.Usuario;


/**
 *
 * @author usuario
 */
@Stateless
public class Email {
    
    @EJB
    private UsuarioInterface ejbUsuario;
    
    List<Usuario> usuario;

    public void send(String check, String mensaje, String titulo, String destinatarios) {
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
                usuario = ejbUsuario.findAll();
                for (int i = 0; i < usuario.size(); i++) {
                    
                    items = usuario.get(i);
                    
                    Message mailMessage = new MimeMessage(mailSession);
                    mailMessage.setFrom(new InternetAddress(emisor));
                    mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(items.getEmail()));
                    mailMessage.setContent(mensaje, "text/html");
                    mailMessage.setSubject(titulo);

                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect("smtp.gmail.com", emisor, clave);
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                     
                }
               
            } else {
                Message mailMessage = new MimeMessage(mailSession);
                mailMessage.setFrom(new InternetAddress(emisor));
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(destinatarios));
                mailMessage.setContent(mensaje, "text/html");
                mailMessage.setSubject(titulo);

                Transport transport = mailSession.getTransport("smtp");
                transport.connect("smtp.gmail.com", emisor, clave);
                transport.sendMessage(mailMessage, mailMessage.getAllRecipients());             
            }
        } catch (Exception e) {
        }
    }
}
