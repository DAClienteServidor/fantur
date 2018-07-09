/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.RolInterface;
import dao.UsuarioInterface;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Rol;
import modelo.Usuario;

/**
 *
 * @author maria
 */
@ManagedBean(name = "registrar")
@ViewScoped
public class RegistroController implements Serializable{
    
    @EJB
    private UsuarioInterface UsuarioEJB;
    @EJB
    private RolInterface RolEJB;
    @EJB
    private Email email;
    
    
        private Usuario usuario; 
        private Rol rol;

        
        @PostConstruct
        public  void init() {
            usuario = new Usuario();
            rol = new Rol(); 
        }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
       
    public String registrar() {
        try {
          FacesContext context = FacesContext.getCurrentInstance();
          UsuarioEJB.create(usuario);
          rol.setRol("Usuario");
          rol.setUsuario(usuario);
          rol.setUsuarioDni(usuario.getDni());
          RolEJB.create(rol);     
          String mensaje = "Su cuenta: "+ usuario.getUsuario() + " con clave:" + usuario.getContrasena()+" ha sido creada";
          
          email.send("No", mensaje, "Registro exitoso", usuario.getEmail());
          
          context.getExternalContext().getSessionMap().put("usuario", usuario);
          return "/usuarios/index?faces-redirect=true";
        } catch (Exception e) {
            System.out.print("No anda che" + e.getMessage());
        }
        return "";
    }
    
    public void registrarAdmin(){
        try {
            UsuarioEJB.create(usuario);
            rol.setUsuario(usuario);
            rol.setUsuarioDni(usuario.getDni());
            RolEJB.create(rol);
        } catch (Exception e) {
        }
    }
    
    public void leer(Usuario usuSelect) {
        usuario = usuSelect;
    }
    public  void modificarAdmin(){
        try {
            UsuarioEJB.edit(usuario);
        } catch (Exception e) {
            System.out.print("Ayudaaa "+e.getMessage());
        }
        
    }
    
        public  void Eliminar(){
        UsuarioEJB.remove(usuario);
    }
    
}
