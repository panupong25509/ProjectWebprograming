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
import comfortpay.jpa.model.Account;
import comfortpay.jpa.model.Products;
import comfortpay.jpa.model.Wishlish;
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
public class WishlishJpaController implements Serializable {

    public WishlishJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Wishlish wishlish) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account accountid = wishlish.getAccountid();
            if (accountid != null) {
                accountid = em.getReference(accountid.getClass(), accountid.getAccountid());
                wishlish.setAccountid(accountid);
            }
            Products productid = wishlish.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                wishlish.setProductid(productid);
            }
            em.persist(wishlish);
            if (accountid != null) {
                accountid.getWishlishList().add(wishlish);
                accountid = em.merge(accountid);
            }
            if (productid != null) {
                productid.getWishlishList().add(wishlish);
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

    public void edit(Wishlish wishlish) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Wishlish persistentWishlish = em.find(Wishlish.class, wishlish.getWishlishid());
            Account accountidOld = persistentWishlish.getAccountid();
            Account accountidNew = wishlish.getAccountid();
            Products productidOld = persistentWishlish.getProductid();
            Products productidNew = wishlish.getProductid();
            if (accountidNew != null) {
                accountidNew = em.getReference(accountidNew.getClass(), accountidNew.getAccountid());
                wishlish.setAccountid(accountidNew);
            }
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                wishlish.setProductid(productidNew);
            }
            wishlish = em.merge(wishlish);
            if (accountidOld != null && !accountidOld.equals(accountidNew)) {
                accountidOld.getWishlishList().remove(wishlish);
                accountidOld = em.merge(accountidOld);
            }
            if (accountidNew != null && !accountidNew.equals(accountidOld)) {
                accountidNew.getWishlishList().add(wishlish);
                accountidNew = em.merge(accountidNew);
            }
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getWishlishList().remove(wishlish);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getWishlishList().add(wishlish);
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
                Integer id = wishlish.getWishlishid();
                if (findWishlish(id) == null) {
                    throw new NonexistentEntityException("The wishlish with id " + id + " no longer exists.");
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
            Wishlish wishlish;
            try {
                wishlish = em.getReference(Wishlish.class, id);
                wishlish.getWishlishid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The wishlish with id " + id + " no longer exists.", enfe);
            }
            Account accountid = wishlish.getAccountid();
            if (accountid != null) {
                accountid.getWishlishList().remove(wishlish);
                accountid = em.merge(accountid);
            }
            Products productid = wishlish.getProductid();
            if (productid != null) {
                productid.getWishlishList().remove(wishlish);
                productid = em.merge(productid);
            }
            em.remove(wishlish);
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

    public List<Wishlish> findWishlishEntities() {
        return findWishlishEntities(true, -1, -1);
    }

    public List<Wishlish> findWishlishEntities(int maxResults, int firstResult) {
        return findWishlishEntities(false, maxResults, firstResult);
    }

    private List<Wishlish> findWishlishEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Wishlish.class));
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

    public Wishlish findWishlish(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Wishlish.class, id);
        } finally {
            em.close();
        }
    }

    public int getWishlishCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Wishlish> rt = cq.from(Wishlish.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
