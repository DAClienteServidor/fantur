/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ContrataInterface;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.Contrata;

/**
 *
 * @author usuario
 */
@ManagedBean(name = "ConController")
@SessionScoped
public class ContratosController {
    @EJB
    private ContrataInterface ejbCon;
    private List<Contrata> contrata;
    
    @PostConstruct
    public void init(){
        contrata = ejbCon.findAll();
    }
    
    public List<Contrata> getContrato() {
        return contrata;
    }

    public void setContrato(List<Contrata> con) {
        this.contrata = con;
    }
    
}
