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
import comfortpay.model.Products;
import comfortpay.model.Orderlists;
import comfortpay.model.Sizes;
import comfortpay.model.controller.exceptions.NonexistentEntityException;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class SizesJpaController implements Serializable {

    public SizesJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sizes sizes) throws RollbackFailureException, Exception {
        if (sizes.getOrderlistsList() == null) {
            sizes.setOrderlistsList(new ArrayList<Orderlists>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Products productid = sizes.getProductid();
            if (productid != null) {
                productid = em.getReference(productid.getClass(), productid.getProductid());
                sizes.setProductid(productid);
            }
            List<Orderlists> attachedOrderlistsList = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListOrderlistsToAttach : sizes.getOrderlistsList()) {
                orderlistsListOrderlistsToAttach = em.getReference(orderlistsListOrderlistsToAttach.getClass(), orderlistsListOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsList.add(orderlistsListOrderlistsToAttach);
            }
            sizes.setOrderlistsList(attachedOrderlistsList);
            em.persist(sizes);
            if (productid != null) {
                productid.getSizesList().add(sizes);
                productid = em.merge(productid);
            }
            for (Orderlists orderlistsListOrderlists : sizes.getOrderlistsList()) {
                Sizes oldSizeidOfOrderlistsListOrderlists = orderlistsListOrderlists.getSizeid();
                orderlistsListOrderlists.setSizeid(sizes);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
                if (oldSizeidOfOrderlistsListOrderlists != null) {
                    oldSizeidOfOrderlistsListOrderlists.getOrderlistsList().remove(orderlistsListOrderlists);
                    oldSizeidOfOrderlistsListOrderlists = em.merge(oldSizeidOfOrderlistsListOrderlists);
                }
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

    public void edit(Sizes sizes) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Sizes persistentSizes = em.find(Sizes.class, sizes.getSizeid());
            Products productidOld = persistentSizes.getProductid();
            Products productidNew = sizes.getProductid();
            List<Orderlists> orderlistsListOld = persistentSizes.getOrderlistsList();
            List<Orderlists> orderlistsListNew = sizes.getOrderlistsList();
            if (productidNew != null) {
                productidNew = em.getReference(productidNew.getClass(), productidNew.getProductid());
                sizes.setProductid(productidNew);
            }
            List<Orderlists> attachedOrderlistsListNew = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListNewOrderlistsToAttach : orderlistsListNew) {
                orderlistsListNewOrderlistsToAttach = em.getReference(orderlistsListNewOrderlistsToAttach.getClass(), orderlistsListNewOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsListNew.add(orderlistsListNewOrderlistsToAttach);
            }
            orderlistsListNew = attachedOrderlistsListNew;
            sizes.setOrderlistsList(orderlistsListNew);
            sizes = em.merge(sizes);
            if (productidOld != null && !productidOld.equals(productidNew)) {
                productidOld.getSizesList().remove(sizes);
                productidOld = em.merge(productidOld);
            }
            if (productidNew != null && !productidNew.equals(productidOld)) {
                productidNew.getSizesList().add(sizes);
                productidNew = em.merge(productidNew);
            }
            for (Orderlists orderlistsListOldOrderlists : orderlistsListOld) {
                if (!orderlistsListNew.contains(orderlistsListOldOrderlists)) {
                    orderlistsListOldOrderlists.setSizeid(null);
                    orderlistsListOldOrderlists = em.merge(orderlistsListOldOrderlists);
                }
            }
            for (Orderlists orderlistsListNewOrderlists : orderlistsListNew) {
                if (!orderlistsListOld.contains(orderlistsListNewOrderlists)) {
                    Sizes oldSizeidOfOrderlistsListNewOrderlists = orderlistsListNewOrderlists.getSizeid();
                    orderlistsListNewOrderlists.setSizeid(sizes);
                    orderlistsListNewOrderlists = em.merge(orderlistsListNewOrderlists);
                    if (oldSizeidOfOrderlistsListNewOrderlists != null && !oldSizeidOfOrderlistsListNewOrderlists.equals(sizes)) {
                        oldSizeidOfOrderlistsListNewOrderlists.getOrderlistsList().remove(orderlistsListNewOrderlists);
                        oldSizeidOfOrderlistsListNewOrderlists = em.merge(oldSizeidOfOrderlistsListNewOrderlists);
                    }
                }
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
                Integer id = sizes.getSizeid();
                if (findSizes(id) == null) {
                    throw new NonexistentEntityException("The sizes with id " + id + " no longer exists.");
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
            Sizes sizes;
            try {
                sizes = em.getReference(Sizes.class, id);
                sizes.getSizeid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sizes with id " + id + " no longer exists.", enfe);
            }
            Products productid = sizes.getProductid();
            if (productid != null) {
                productid.getSizesList().remove(sizes);
                productid = em.merge(productid);
            }
            List<Orderlists> orderlistsList = sizes.getOrderlistsList();
            for (Orderlists orderlistsListOrderlists : orderlistsList) {
                orderlistsListOrderlists.setSizeid(null);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
            }
            em.remove(sizes);
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

    public List<Sizes> findSizesEntities() {
        return findSizesEntities(true, -1, -1);
    }

    public List<Sizes> findSizesEntities(int maxResults, int firstResult) {
        return findSizesEntities(false, maxResults, firstResult);
    }

    private List<Sizes> findSizesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sizes.class));
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

    public Sizes findSizes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sizes.class, id);
        } finally {
            em.close();
        }
    }

    public int getSizesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sizes> rt = cq.from(Sizes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
