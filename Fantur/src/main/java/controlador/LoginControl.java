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
 * @author Nico
 */
@ManagedBean(name = "loginControl")
@SessionScoped
public class LoginControl implements Serializable{
    
    @EJB
    private UsuarioInterface ejbUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        String redireccion = null;
        Usuario us;
        try{
            us = ejbUsuario.iniciarSesion(usuario);
            if (us!=null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us); //Crea una sesion
                redireccion = "index?faces-redirect=true";
            }else{
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Aviso","Usuario o clave erronea"));
                  }    
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_FATAL,"Aviso","Error logueo"));
        }
        return redireccion;
    }
    
    public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }
}
