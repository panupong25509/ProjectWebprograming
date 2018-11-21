/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.jpa.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import comfortpay.jpa.model.Products;
import comfortpay.jpa.model.Size1;
import comfortpay.jpa.model.controller.exceptions.NonexistentEntityException;
import comfortpay.jpa.model.controller.exceptions.RollbackFailureException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class Size1JpaController implements Serializable {

    public Size1JpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Size1 size1) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Products productid = size1.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                size1.setProductid(productid);
            }
            em.persist(size1);
            if (productid != null) {
                productid.getSize1List().add(size1);
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

    public void edit(Size1 size1) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Size1 persistentSize1 = em.find(Size1.class, size1.getSizeid());
            Products productidOld = persistentSize1.getProductid();
            Products productidNew = size1.getProductid();
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                size1.setProductid(productidNew);
            }
            size1 = em.merge(size1);
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getSize1List().remove(size1);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getSize1List().add(size1);
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
                Integer id = size1.getSizeid();
                if (findSize1(id) == null) {
                    throw new NonexistentEntityException("The size1 with id " + id + " no longer exists.");
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
            Size1 size1;
            try {
                size1 = em.getReference(Size1.class, id);
                size1.getSizeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The size1 with id " + id + " no longer exists.", enfe);
            }
            Products productid = size1.getProductid();
            if (productid != null) {
                productid.getSize1List().remove(size1);
                productid = em.merge(productid);
            }
            em.remove(size1);
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

    public List<Size1> findSize1Entities() {
        return findSize1Entities(true, -1, -1);
    }

    public List<Size1> findSize1Entities(int maxResults, int firstResult) {
        return findSize1Entities(false, maxResults, firstResult);
    }

    private List<Size1> findSize1Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Size1.class));
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

    public Size1 findSize1(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Size1.class, id);
        } finally {
            em.close();
        }
    }

    public int getSize1Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Size1> rt = cq.from(Size1.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
