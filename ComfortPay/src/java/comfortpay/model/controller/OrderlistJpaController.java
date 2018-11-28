/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model.controller;

import comfortpay.model.Orderlist;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import comfortpay.model.Orders;
import comfortpay.model.Products;
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
public class OrderlistJpaController implements Serializable {

    public OrderlistJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orderlist orderlist) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders orderid = orderlist.getOrderid();
            if (orderid != null) {
                orderid = em.getReference(orderid.getClass(), orderid.getOrderid());
                orderlist.setOrderid(orderid);
            }
            Products productid = orderlist.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                orderlist.setProductid(productid);
            }
            em.persist(orderlist);
            if (orderid != null) {
                orderid.getOrderlistList().add(orderlist);
                orderid = em.merge(orderid);
            }
            if (productid != null) {
                productid.getOrderlistList().add(orderlist);
                productid = em.merge(productid);
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

    public void edit(Orderlist orderlist) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orderlist persistentOrderlist = em.find(Orderlist.class, orderlist.getOrderlistid());
            Orders orderidOld = persistentOrderlist.getOrderid();
            Orders orderidNew = orderlist.getOrderid();
            Products productidOld = persistentOrderlist.getProductid();
            Products productidNew = orderlist.getProductid();
            if (orderidNew != null) {
                orderidNew = em.getReference(orderidNew.getClass(), orderidNew.getOrderid());
                orderlist.setOrderid(orderidNew);
            }
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                orderlist.setProductid(productidNew);
            }
            orderlist = em.merge(orderlist);
            if (orderidOld != null && !orderidOld.equals(orderidNew)) {
                orderidOld.getOrderlistList().remove(orderlist);
                orderidOld = em.merge(orderidOld);
            }
            if (orderidNew != null && !orderidNew.equals(orderidOld)) {
                orderidNew.getOrderlistList().add(orderlist);
                orderidNew = em.merge(orderidNew);
            }
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getOrderlistList().remove(orderlist);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getOrderlistList().add(orderlist);
                productidNew = em.merge(productidNew);
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
                Integer id = orderlist.getOrderlistid();
                if (findOrderlist(id) == null) {
                    throw new NonexistentEntityException("The orderlist with id " + id + " no longer exists.");
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
            Orderlist orderlist;
            try {
                orderlist = em.getReference(Orderlist.class, id);
                orderlist.getOrderlistid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderlist with id " + id + " no longer exists.", enfe);
            }
            Orders orderid = orderlist.getOrderid();
            if (orderid != null) {
                orderid.getOrderlistList().remove(orderlist);
                orderid = em.merge(orderid);
            }
            Products productid = orderlist.getProductid();
            if (productid != null) {
                productid.getOrderlistList().remove(orderlist);
                productid = em.merge(productid);
            }
            em.remove(orderlist);
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

    public List<Orderlist> findOrderlistEntities() {
        return findOrderlistEntities(true, -1, -1);
    }

    public List<Orderlist> findOrderlistEntities(int maxResults, int firstResult) {
        return findOrderlistEntities(false, maxResults, firstResult);
    }

    private List<Orderlist> findOrderlistEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orderlist.class));
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

    public Orderlist findOrderlist(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orderlist.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderlistCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orderlist> rt = cq.from(Orderlist.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
