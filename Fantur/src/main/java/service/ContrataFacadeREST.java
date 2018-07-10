/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.PathSegment;
import modelo.Contrata;
import modelo.ContrataPK;
import modelo.ContrataPK_;
import modelo.Contrata_;

/**
 *
 * @author maria
 */
@Stateless
@Path("modelo.contrata")
public class ContrataFacadeREST extends AbstractFacade<Contrata> {

    @PersistenceContext(unitName = "com.mycompany_Fantur_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private ContrataPK getPrimaryKey(PathSegment pathSegment) {
        /*
         * pathSemgent represents a URI path segment and any associated matrix parameters.
         * URI path part is supposed to be in form of 'somePath;usuario=usuarioValue;paquete=paqueteValue'.
         * Here 'somePath' is a result of getPath() method invocation and
         * it is ignored in the following code.
         * Matrix parameters are used as field names to build a primary key instance.
         */
        modelo.ContrataPK key = new modelo.ContrataPK();
        javax.ws.rs.core.MultivaluedMap<String, String> map = pathSegment.getMatrixParameters();
        java.util.List<String> usuario = map.get("usuario");
        if (usuario != null && !usuario.isEmpty()) {
            key.setUsuario(usuario.get(0));
        }
        java.util.List<String> paquete = map.get("paquete");
        if (paquete != null && !paquete.isEmpty()) {
            key.setPaquete(new java.lang.Integer(paquete.get(0)));
        }
        return key;
    }

    public ContrataFacadeREST() {
        super(Contrata.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Contrata entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") PathSegment id, Contrata entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") PathSegment id) {
        modelo.ContrataPK key = getPrimaryKey(id);
        super.remove(super.find(key));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Contrata find(@PathParam("id") PathSegment id) {
        modelo.ContrataPK key = getPrimaryKey(id);
        return super.find(key);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Contrata> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Contrata> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
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
