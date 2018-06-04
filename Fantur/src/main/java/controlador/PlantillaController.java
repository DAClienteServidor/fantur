/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import modelo.Usuario;

/**
 * Para que nadie que no se haya logueado pueda acceder
 * @author Nico
 */
@ManagedBean(name = "plantillaController")
@SessionScoped
public class PlantillaController implements Serializable{
    
    public void verificarSesion(){
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Usuario us = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
            if (us==null) {
                context.getExternalContext().redirect("index.xhtml");
            }
            
        } catch (Exception e) {
        }
    }
}
