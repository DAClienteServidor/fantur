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
import modelo.Alojamiento;
import modelo.Alojamiento_;

@Stateless
public class AlojamientoDAO extends DAO<Alojamiento> implements AlojamientoInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlojamientoDAO() {
        super(Alojamiento.class);
    }
    
    @Override
    public List<Alojamiento> findByAlgo(String consulta){

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Alojamiento> cq = cb.createQuery(Alojamiento.class);

            Root<Alojamiento> e = cq.from(Alojamiento.class);
            
            cq.where(cb.or(cb.like(e.get(Alojamiento_.nombre), consulta),
                           cb.like(e.get(Alojamiento_.tipo), consulta)));

            Query query = em.createQuery(cq);
            
            return query.getResultList();
         
        } catch (Exception e) {
            throw e;
        }
    }
    
}
