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
import modelo.Pasaje;
import modelo.Pasaje_;

/**
 *
 * @author usuario
 */
@Stateless
public class PasajeDAO extends DAO<Pasaje> implements PasajeInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasajeDAO() {
        super(Pasaje.class);
    }
    
    @Override
    public List<Pasaje> findByAlgo(String consulta){

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Pasaje> cq = cb.createQuery(Pasaje.class);

            Root<Pasaje> e = cq.from(Pasaje.class);
            
            cq.where(cb.or(cb.like(e.get(Pasaje_.destino), consulta),
                           cb.like(e.get(Pasaje_.origen), consulta)));

            Query query = em.createQuery(cq);
            
            return query.getResultList();
         
        } catch (Exception e) {
            throw e;
        }
    }
}
