/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioInterface;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;

/**
 * Controla el login del usuario y el cerrar sesion
 *
 * @author Nico
 */
@ManagedBean(name = "loginControl")
@SessionScoped
public class LoginControl implements Serializable {

    @EJB
    private UsuarioInterface ejbUsuario;
    private String usu;
    private String pas;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        usuario = null;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsu() {
        return usu;
    }

    public void setUsu(String usu) {
        this.usu = usu;
    }

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
    }

    public String iniciarSesion() {
        String rol;
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            //por si ya hay una sesion la destruye
            if (usuario != null) {
                cerrarSesion();
            }

            usuario = ejbUsuario.iniciarSesion(this.usu,this.pas);
            if (usuario != null) {
                context.getExternalContext().getSessionMap().put("usuario", usuario); //Crea una sesion
                rol = usuario.getRol().getRol();
                if (rol.equals("Administrador")) {
                    return "/administradores/gesPaquetes.xhtml?faces-redirect=true";
                } else {
                    return "/usuarios/index?faces-redirect=true";
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Usuario o clave erronea"));
            }
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error logueo"));
        }
        return "";
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        this.usuario = null;
    }

    public String nombreUsuario() {
        return usuario.getNombre();
    }
}
