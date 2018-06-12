/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.UsuarioInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Usuario;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "UsuController")
@SessionScoped
public class UsuariosController {
      @EJB
    private UsuarioInterface ejbUsu;
    private List<Usuario> usuario;
    
    @PostConstruct
    public void init(){
        usuario = ejbUsu.findAll();
    }
    
    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usu) {
        this.usuario = usu;
    }
}
