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
import modelo.Contrata;
import modelo.ContrataPK_;
import modelo.Contrata_;
import modelo.Usuario_;

/**
 *
 * @author usuario
 */
@Stateless
public class ContrataDAO extends DAO<Contrata> implements ContrataInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContrataDAO() {
        super(Contrata.class);
    }
    
    @Override
    public List<Contrata> findByUsu(String dni){
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Contrata> cq = cb.createQuery(Contrata.class);

            Root<Contrata> e = cq.from(Contrata.class);
            
            cq.where(cb.equal(e.get(Contrata_.contrataPK).get(ContrataPK_.usuario), dni));
            
            //cq.where(cb.equal(e.get("contrataPK").get("usuario"), dni));

            Query query = em.createQuery(cq);
            
            List<Contrata> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            throw e;
        }
    }
    
}
