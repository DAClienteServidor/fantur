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
import modelo.Usuario;

/**
 *
 * @author usuario
 */
@Stateless
public class UsuarioDAO extends DAO<Usuario> implements UsuarioInterface {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public Usuario iniciarSesion(String usu, String pas) throws Exception {
        Usuario usuario = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

            Root<Usuario> e = cq.from(Usuario.class);
            
            
            cq.where(cb.and((cb.equal(e.get("usuario"), usu)), (cb.equal(e.get("contrasena"), pas))));

            Query query = em.createQuery(cq);
            
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }
    
    @Override
    public Usuario findByUsuario(String username){
        Usuario usuario = null;
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

            Root<Usuario> e = cq.from(Usuario.class);
            
            cq.where(cb.and(cb.equal(e.get("usuario"), username)));

            Query query = em.createQuery(cq);
            
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    };
}
