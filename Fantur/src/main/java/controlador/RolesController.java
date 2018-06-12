/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.RolInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Rol;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "RolController")
@SessionScoped
public class RolesController {
    @EJB
    private RolInterface ejbRol;
    private List<Rol> rol;
    
    @PostConstruct
    public void init(){
        rol = ejbRol.findAll();
    }
    
    public List<Rol> getRol() {
        return rol;
    }

    public void setUsuario(List<Rol> rol) {
        this.rol = rol;
    }
}
