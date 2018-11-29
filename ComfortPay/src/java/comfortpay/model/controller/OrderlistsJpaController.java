/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model.controller;

import comfortpay.model.Orderlists;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import comfortpay.model.Orders;
import comfortpay.model.Products;
import comfortpay.model.Sizes;
import comfortpay.model.controller.exceptions.NonexistentEntityException;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class OrderlistsJpaController implements Serializable {

    public OrderlistsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orderlists orderlists) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders orderid = orderlists.getOrderid();
            if (orderid != null) {
                orderid = em.getReference(orderid.getClass(), orderid.getOrderid());
                orderlists.setOrderid(orderid);
            }
            Products productid = orderlists.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                orderlists.setProductid(productid);
            }
            Sizes sizeid = orderlists.getSizeid();
            if (sizeid != null) {
                sizeid = em.getReference(sizeid.getClass(), sizeid.getSizeid());
                orderlists.setSizeid(sizeid);
            }
            em.persist(orderlists);
            if (orderid != null) {
                orderid.getOrderlistsList().add(orderlists);
                orderid = em.merge(orderid);
            }
            if (productid != null) {
                productid.getOrderlistsList().add(orderlists);
                productid = em.merge(productid);
            }
            if (sizeid != null) {
                sizeid.getOrderlistsList().add(orderlists);
                sizeid = em.merge(sizeid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orderlists orderlists) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderlists persistentOrderlists = em.find(Orderlists.class, orderlists.getOrderlistid());
            Orders orderidOld = persistentOrderlists.getOrderid();
            Orders orderidNew = orderlists.getOrderid();
            Products productidOld = persistentOrderlists.getProductid();
            Products productidNew = orderlists.getProductid();
            Sizes sizeidOld = persistentOrderlists.getSizeid();
            Sizes sizeidNew = orderlists.getSizeid();
            if (orderidNew != null) {
                orderidNew = em.getReference(orderidNew.getClass(), orderidNew.getOrderid());
                orderlists.setOrderid(orderidNew);
            }
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                orderlists.setProductid(productidNew);
            }
            if (sizeidNew != null) {
                sizeidNew = em.getReference(sizeidNew.getClass(), sizeidNew.getSizeid());
                orderlists.setSizeid(sizeidNew);
            }
            orderlists = em.merge(orderlists);
            if (orderidOld != null && !orderidOld.equals(orderidNew)) {
                orderidOld.getOrderlistsList().remove(orderlists);
                orderidOld = em.merge(orderidOld);
            }
            if (orderidNew != null && !orderidNew.equals(orderidOld)) {
                orderidNew.getOrderlistsList().add(orderlists);
                orderidNew = em.merge(orderidNew);
            }
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getOrderlistsList().remove(orderlists);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getOrderlistsList().add(orderlists);
                productidNew = em.merge(productidNew);
            }
            if (sizeidOld != null && !sizeidOld.equals(sizeidNew)) {
                sizeidOld.getOrderlistsList().remove(orderlists);
                sizeidOld = em.merge(sizeidOld);
            }
            if (sizeidNew != null && !sizeidNew.equals(sizeidOld)) {
                sizeidNew.getOrderlistsList().add(orderlists);
                sizeidNew = em.merge(sizeidNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orderlists.getOrderlistid();
                if (findOrderlists(id) == null) {
                    throw new NonexistentEntityException("The orderlists with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderlists orderlists;
            try {
                orderlists = em.getReference(Orderlists.class, id);
                orderlists.getOrderlistid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderlists with id " + id + " no longer exists.", enfe);
            }
            Orders orderid = orderlists.getOrderid();
            if (orderid != null) {
                orderid.getOrderlistsList().remove(orderlists);
                orderid = em.merge(orderid);
            }
            Products productid = orderlists.getProductid();
            if (productid != null) {
                productid.getOrderlistsList().remove(orderlists);
                productid = em.merge(productid);
            }
            Sizes sizeid = orderlists.getSizeid();
            if (sizeid != null) {
                sizeid.getOrderlistsList().remove(orderlists);
                sizeid = em.merge(sizeid);
            }
            em.remove(orderlists);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orderlists> findOrderlistsEntities() {
        return findOrderlistsEntities(true, -1, -1);
    }

    public List<Orderlists> findOrderlistsEntities(int maxResults, int firstResult) {
        return findOrderlistsEntities(false, maxResults, firstResult);
    }

    private List<Orderlists> findOrderlistsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orderlists.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Orderlists findOrderlists(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orderlists.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderlistsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orderlists> rt = cq.from(Orderlists.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
