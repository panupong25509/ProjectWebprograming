/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.jpa.model.controller;

import comfortpay.jpa.model.Productcloth;
import comfortpay.jpa.model.controller.exceptions.NonexistentEntityException;
import comfortpay.jpa.model.controller.exceptions.PreexistingEntityException;
import comfortpay.jpa.model.controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class ProductclothJpaController implements Serializable {

    public ProductclothJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productcloth productcloth) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(productcloth);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductcloth(productcloth.getProductcode()) != null) {
                throw new PreexistingEntityException("Productcloth " + productcloth + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Productcloth productcloth) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            productcloth = em.merge(productcloth);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = productcloth.getProductcode();
                if (findProductcloth(id) == null) {
                    throw new NonexistentEntityException("The productcloth with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Productcloth productcloth;
            try {
                productcloth = em.getReference(Productcloth.class, id);
                productcloth.getProductcode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productcloth with id " + id + " no longer exists.", enfe);
            }
            em.remove(productcloth);
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

    public List<Productcloth> findProductclothEntities() {
        return findProductclothEntities(true, -1, -1);
    }

    public List<Productcloth> findProductclothEntities(int maxResults, int firstResult) {
        return findProductclothEntities(false, maxResults, firstResult);
    }

    private List<Productcloth> findProductclothEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productcloth.class));
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

    public Productcloth findProductcloth(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productcloth.class, id);
        } finally {
            em.close();
        }
    }
    
    public List<Productcloth> findByProductclothType(String productType){
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Productcloth.findByProducttype");
            query.setParameter("producttype",productType.toUpperCase());
            return query.getResultList();
        } finally {
            em.close();
        }
    } 

    public int getProductclothCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productcloth> rt = cq.from(Productcloth.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
