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
import comfortpay.model.Orderlists;
import comfortpay.model.Orders;
import comfortpay.model.controller.exceptions.NonexistentEntityException;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class OrdersJpaController implements Serializable {

    public OrdersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders orders) throws RollbackFailureException, Exception {
        if (orders.getOrderlistsList() == null) {
            orders.setOrderlistsList(new ArrayList<Orderlists>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account accountid = orders.getAccountid();
            if (accountid != null) {
                accountid = em.getReference(accountid.getClass(), accountid.getAccountid());
                orders.setAccountid(accountid);
            }
            List<Orderlists> attachedOrderlistsList = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListOrderlistsToAttach : orders.getOrderlistsList()) {
                orderlistsListOrderlistsToAttach = em.getReference(orderlistsListOrderlistsToAttach.getClass(), orderlistsListOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsList.add(orderlistsListOrderlistsToAttach);
            }
            orders.setOrderlistsList(attachedOrderlistsList);
            em.persist(orders);
            if (accountid != null) {
                accountid.getOrdersList().add(orders);
                accountid = em.merge(accountid);
            }
            for (Orderlists orderlistsListOrderlists : orders.getOrderlistsList()) {
                Orders oldOrderidOfOrderlistsListOrderlists = orderlistsListOrderlists.getOrderid();
                orderlistsListOrderlists.setOrderid(orders);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
                if (oldOrderidOfOrderlistsListOrderlists != null) {
                    oldOrderidOfOrderlistsListOrderlists.getOrderlistsList().remove(orderlistsListOrderlists);
                    oldOrderidOfOrderlistsListOrderlists = em.merge(oldOrderidOfOrderlistsListOrderlists);
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

    public void edit(Orders orders) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders persistentOrders = em.find(Orders.class, orders.getOrderid());
            Account accountidOld = persistentOrders.getAccountid();
            Account accountidNew = orders.getAccountid();
            List<Orderlists> orderlistsListOld = persistentOrders.getOrderlistsList();
            List<Orderlists> orderlistsListNew = orders.getOrderlistsList();
            if (accountidNew != null) {
                accountidNew = em.getReference(accountidNew.getClass(), accountidNew.getAccountid());
                orders.setAccountid(accountidNew);
            }
            List<Orderlists> attachedOrderlistsListNew = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListNewOrderlistsToAttach : orderlistsListNew) {
                orderlistsListNewOrderlistsToAttach = em.getReference(orderlistsListNewOrderlistsToAttach.getClass(), orderlistsListNewOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsListNew.add(orderlistsListNewOrderlistsToAttach);
            }
            orderlistsListNew = attachedOrderlistsListNew;
            orders.setOrderlistsList(orderlistsListNew);
            orders = em.merge(orders);
            if (accountidOld != null && !accountidOld.equals(accountidNew)) {
                accountidOld.getOrdersList().remove(orders);
                accountidOld = em.merge(accountidOld);
            }
            if (accountidNew != null && !accountidNew.equals(accountidOld)) {
                accountidNew.getOrdersList().add(orders);
                accountidNew = em.merge(accountidNew);
            }
            for (Orderlists orderlistsListOldOrderlists : orderlistsListOld) {
                if (!orderlistsListNew.contains(orderlistsListOldOrderlists)) {
                    orderlistsListOldOrderlists.setOrderid(null);
                    orderlistsListOldOrderlists = em.merge(orderlistsListOldOrderlists);
                }
            }
            for (Orderlists orderlistsListNewOrderlists : orderlistsListNew) {
                if (!orderlistsListOld.contains(orderlistsListNewOrderlists)) {
                    Orders oldOrderidOfOrderlistsListNewOrderlists = orderlistsListNewOrderlists.getOrderid();
                    orderlistsListNewOrderlists.setOrderid(orders);
                    orderlistsListNewOrderlists = em.merge(orderlistsListNewOrderlists);
                    if (oldOrderidOfOrderlistsListNewOrderlists != null && !oldOrderidOfOrderlistsListNewOrderlists.equals(orders)) {
                        oldOrderidOfOrderlistsListNewOrderlists.getOrderlistsList().remove(orderlistsListNewOrderlists);
                        oldOrderidOfOrderlistsListNewOrderlists = em.merge(oldOrderidOfOrderlistsListNewOrderlists);
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
                Integer id = orders.getOrderid();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getOrderid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            Account accountid = orders.getAccountid();
            if (accountid != null) {
                accountid.getOrdersList().remove(orders);
                accountid = em.merge(accountid);
            }
            List<Orderlists> orderlistsList = orders.getOrderlistsList();
            for (Orderlists orderlistsListOrderlists : orderlistsList) {
                orderlistsListOrderlists.setOrderid(null);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
            }
            em.remove(orders);
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

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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

    public Orders findOrders(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Orders findByOrderCode(String code) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Orders.findByOrdercode");
            query.setParameter("ordercode", code);
            return (Orders) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

}
