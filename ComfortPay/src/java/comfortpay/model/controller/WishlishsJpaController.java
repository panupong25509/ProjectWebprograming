/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import comfortpay.model.Account;
import comfortpay.model.Products;
import comfortpay.model.Wishlishs;
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
public class WishlishsJpaController implements Serializable {

    public WishlishsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Wishlishs wishlishs) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account accountid = wishlishs.getAccountid();
            if (accountid != null) {
                accountid = em.getReference(accountid.getClass(), accountid.getAccountid());
                wishlishs.setAccountid(accountid);
            }
            Products productid = wishlishs.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                wishlishs.setProductid(productid);
            }
            em.persist(wishlishs);
            if (accountid != null) {
                accountid.getWishlishsList().add(wishlishs);
                accountid = em.merge(accountid);
            }
            if (productid != null) {
                productid.getWishlishsList().add(wishlishs);
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

    public void edit(Wishlishs wishlishs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Wishlishs persistentWishlishs = em.find(Wishlishs.class, wishlishs.getWishlishid());
            Account accountidOld = persistentWishlishs.getAccountid();
            Account accountidNew = wishlishs.getAccountid();
            Products productidOld = persistentWishlishs.getProductid();
            Products productidNew = wishlishs.getProductid();
            if (accountidNew != null) {
                accountidNew = em.getReference(accountidNew.getClass(), accountidNew.getAccountid());
                wishlishs.setAccountid(accountidNew);
            }
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                wishlishs.setProductid(productidNew);
            }
            wishlishs = em.merge(wishlishs);
            if (accountidOld != null && !accountidOld.equals(accountidNew)) {
                accountidOld.getWishlishsList().remove(wishlishs);
                accountidOld = em.merge(accountidOld);
            }
            if (accountidNew != null && !accountidNew.equals(accountidOld)) {
                accountidNew.getWishlishsList().add(wishlishs);
                accountidNew = em.merge(accountidNew);
            }
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getWishlishsList().remove(wishlishs);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getWishlishsList().add(wishlishs);
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
                Integer id = wishlishs.getWishlishid();
                if (findWishlishs(id) == null) {
                    throw new NonexistentEntityException("The wishlishs with id " + id + " no longer exists.");
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
            Wishlishs wishlishs;
            try {
                wishlishs = em.getReference(Wishlishs.class, id);
                wishlishs.getWishlishid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wishlishs with id " + id + " no longer exists.", enfe);
            }
            Account accountid = wishlishs.getAccountid();
            if (accountid != null) {
                accountid.getWishlishsList().remove(wishlishs);
                accountid = em.merge(accountid);
            }
            Products productid = wishlishs.getProductid();
            if (productid != null) {
                productid.getWishlishsList().remove(wishlishs);
                productid = em.merge(productid);
            }
            em.remove(wishlishs);
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

    public List<Wishlishs> findWishlishsEntities() {
        return findWishlishsEntities(true, -1, -1);
    }

    public List<Wishlishs> findWishlishsEntities(int maxResults, int firstResult) {
        return findWishlishsEntities(false, maxResults, firstResult);
    }

    private List<Wishlishs> findWishlishsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Wishlishs.class));
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

    public Wishlishs findWishlishs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Wishlishs.class, id);
        } finally {
            em.close();
        }
    }

    public int getWishlishsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Wishlishs> rt = cq.from(Wishlishs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
