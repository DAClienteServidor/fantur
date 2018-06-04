/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Paquete;

/**
 *
 * @author usuario
 */
@Stateless
public class PaqueteDAO extends DAO<Paquete> implements PaqueteInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaqueteDAO() {
        super(Paquete.class);
    }
    
}
