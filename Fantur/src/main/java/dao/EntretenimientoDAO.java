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
import modelo.Entretenimiento;
import modelo.Entretenimiento_;

/**
 *
 * @author usuario
 */
@Stateless
public class EntretenimientoDAO extends DAO<Entretenimiento> implements EntretenimientoInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntretenimientoDAO() {
        super(Entretenimiento.class);
    }
    
    @Override
    public List<Entretenimiento> findByAlgo(String consulta){

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Entretenimiento> cq = cb.createQuery(Entretenimiento.class);

            Root<Entretenimiento> e = cq.from(Entretenimiento.class);
            
            cq.where(cb.or(cb.like(e.get(Entretenimiento_.nombre), consulta),
                           cb.like(e.get(Entretenimiento_.lugar), consulta)));

            Query query = em.createQuery(cq);
            
            return query.getResultList();
         
        } catch (Exception e) {
            throw e;
        }
    }
}
