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
import comfortpay.model.Orderlist;
import comfortpay.model.Orders;
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
        if (orders.getOrderlistList() == null) {
            orders.setOrderlistList(new ArrayList<Orderlist>());
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
            List<Orderlist> attachedOrderlistList = new ArrayList<Orderlist>();
            for (Orderlist orderlistListOrderlistToAttach : orders.getOrderlistList()) {
                orderlistListOrderlistToAttach = em.getReference(orderlistListOrderlistToAttach.getClass(), orderlistListOrderlistToAttach.getOrderlistid());
                attachedOrderlistList.add(orderlistListOrderlistToAttach);
            }
            orders.setOrderlistList(attachedOrderlistList);
            em.persist(orders);
            if (accountid != null) {
                accountid.getOrdersList().add(orders);
                accountid = em.merge(accountid);
            }
            for (Orderlist orderlistListOrderlist : orders.getOrderlistList()) {
                Orders oldOrderidOfOrderlistListOrderlist = orderlistListOrderlist.getOrderid();
                orderlistListOrderlist.setOrderid(orders);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
                if (oldOrderidOfOrderlistListOrderlist != null) {
                    oldOrderidOfOrderlistListOrderlist.getOrderlistList().remove(orderlistListOrderlist);
                    oldOrderidOfOrderlistListOrderlist = em.merge(oldOrderidOfOrderlistListOrderlist);
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
            List<Orderlist> orderlistListOld = persistentOrders.getOrderlistList();
            List<Orderlist> orderlistListNew = orders.getOrderlistList();
            if (accountidNew != null) {
                accountidNew = em.getReference(accountidNew.getClass(), accountidNew.getAccountid());
                orders.setAccountid(accountidNew);
            }
            List<Orderlist> attachedOrderlistListNew = new ArrayList<Orderlist>();
            for (Orderlist orderlistListNewOrderlistToAttach : orderlistListNew) {
                orderlistListNewOrderlistToAttach = em.getReference(orderlistListNewOrderlistToAttach.getClass(), orderlistListNewOrderlistToAttach.getOrderlistid());
                attachedOrderlistListNew.add(orderlistListNewOrderlistToAttach);
            }
            orderlistListNew = attachedOrderlistListNew;
            orders.setOrderlistList(orderlistListNew);
            orders = em.merge(orders);
            if (accountidOld != null && !accountidOld.equals(accountidNew)) {
                accountidOld.getOrdersList().remove(orders);
                accountidOld = em.merge(accountidOld);
            }
            if (accountidNew != null && !accountidNew.equals(accountidOld)) {
                accountidNew.getOrdersList().add(orders);
                accountidNew = em.merge(accountidNew);
            }
            for (Orderlist orderlistListOldOrderlist : orderlistListOld) {
                if (!orderlistListNew.contains(orderlistListOldOrderlist)) {
                    orderlistListOldOrderlist.setOrderid(null);
                    orderlistListOldOrderlist = em.merge(orderlistListOldOrderlist);
                }
            }
            for (Orderlist orderlistListNewOrderlist : orderlistListNew) {
                if (!orderlistListOld.contains(orderlistListNewOrderlist)) {
                    Orders oldOrderidOfOrderlistListNewOrderlist = orderlistListNewOrderlist.getOrderid();
                    orderlistListNewOrderlist.setOrderid(orders);
                    orderlistListNewOrderlist = em.merge(orderlistListNewOrderlist);
                    if (oldOrderidOfOrderlistListNewOrderlist != null && !oldOrderidOfOrderlistListNewOrderlist.equals(orders)) {
                        oldOrderidOfOrderlistListNewOrderlist.getOrderlistList().remove(orderlistListNewOrderlist);
                        oldOrderidOfOrderlistListNewOrderlist = em.merge(oldOrderidOfOrderlistListNewOrderlist);
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
            List<Orderlist> orderlistList = orders.getOrderlistList();
            for (Orderlist orderlistListOrderlist : orderlistList) {
                orderlistListOrderlist.setOrderid(null);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
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
    
}
