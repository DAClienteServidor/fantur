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
import javax.faces.bean.ViewScoped;
import modelo.Rol;
import service.RolFacadeREST;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "RolController")
@ViewScoped
public class RolesController {
    @EJB
    private RolFacadeREST RolWS;
    private List<Rol> rol;

    
    
    private Rol rol1;
    
    
    
    @PostConstruct
    public void init(){
        rol = RolWS.findAll();
        rol1 = new Rol();
    }
    
    public List<Rol> getRol() {
        return rol;
    }

    public void setUsuario(List<Rol> rol) {
        this.rol = rol;
    }
    
    //Rol1

    public Rol getRol1() {
        return rol1;
    }

    public void setRol1(Rol rol1) {
        this.rol1 = rol1;
    }
    
    
    
    //Metodos para los gestionar
    public void EditarRol(){
        RolWS.edit(rol1);
    }
    
    public  void leer(Rol rolSelect){
        rol1 = rolSelect;
    }
    

}
