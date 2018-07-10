/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.RolInterface;
import dao.UsuarioInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Rol;
import modelo.Usuario;
import service.RolFacadeREST;
import service.UsuarioFacadeREST;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "UsuController")
@ViewScoped
public class UsuariosController {

      @EJB
    private UsuarioFacadeREST UsuWS;
    private List<Usuario> usuario;
    
    private Usuario usu;
    

    
        @EJB
    private RolFacadeREST RolWS;
    private List<Rol> roles;
   
    
    
        private Usuario usuario1; 
        private Rol rol;
        
    @PostConstruct
    public void init(){
        usuario = UsuWS.findAll();
        roles = RolWS.findAll();
            usuario1 = new Usuario();
            rol = new Rol();
    }
    
    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usu) {
        this.usuario = usu;
    }

    public Usuario getUsu() {
        return usu;
    }

    public void setUsu(Usuario usu) {
        this.usu = usu;
    }
    
    public void leer (Usuario usuarioSelect){
    }
    // getter y setter

    public Usuario getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(Usuario usuario1) {
        this.usuario1 = usuario1;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    //---------------------------------------
           
    public void registrar() {
        try {
          UsuWS.create(usuario1);
          rol.setRol("usuario");
          rol.setUsuario(usuario1);
          rol.setUsuarioDni(usuario1.getDni());
          RolWS.create(rol);
        } catch (Exception e) {
            System.out.print("No anda che" + e.getMessage());
        }
    }
    
    public void registrarAdmin(){
        try {
            UsuWS.create(usuario1);
            rol.setUsuario(usuario1);
            rol.setUsuarioDni(usuario1.getDni());
            RolWS.create(rol);
            usuario = UsuWS.findAll();
        } catch (Exception e) {
        }
    }
    
    public void leerUsuSelec(Usuario usuSelect) {
        usuario1 = usuSelect;
    }
    public  void modificarAdmin(){
        try {
            UsuWS.edit(usuario1);
            usuario1=null; 
        } catch (Exception e) {
        }
        
    }
    
        public  void eliminar(){
        UsuWS.remove(usuario1);
        usuario = UsuWS.findAll();
        
    }
    
    
}
