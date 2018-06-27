/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    
       
    @Override
    public Paquete findById(int id){
        Paquete paquete = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Paquete> cq = cb.createQuery(Paquete.class);

            Root<Paquete> e = cq.from(Paquete.class);
            
            cq.where(cb.and(cb.equal(e.get("idpaquete"), id)));

            Query query = em.createQuery(cq);
            
            List<Paquete> lista = query.getResultList();
            if (!lista.isEmpty()) {
                paquete = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return paquete;
    };
}
