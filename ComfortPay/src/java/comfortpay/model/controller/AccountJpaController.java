/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model.controller;

import comfortpay.model.Account;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import comfortpay.model.Wishlishs;
import java.util.ArrayList;
import java.util.List;
import comfortpay.model.Address;
import comfortpay.model.Orders;
import comfortpay.model.controller.exceptions.NonexistentEntityException;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class AccountJpaController implements Serializable {

    public AccountJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Account account) throws RollbackFailureException, Exception {
        if (account.getWishlishsList() == null) {
            account.setWishlishsList(new ArrayList<Wishlishs>());
        }
        if (account.getAddressList() == null) {
            account.setAddressList(new ArrayList<Address>());
        }
        if (account.getOrdersList() == null) {
            account.setOrdersList(new ArrayList<Orders>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Wishlishs> attachedWishlishsList = new ArrayList<Wishlishs>();
            for (Wishlishs wishlishsListWishlishsToAttach : account.getWishlishsList()) {
                wishlishsListWishlishsToAttach = em.getReference(wishlishsListWishlishsToAttach.getClass(), wishlishsListWishlishsToAttach.getWishlishid());
                attachedWishlishsList.add(wishlishsListWishlishsToAttach);
            }
            account.setWishlishsList(attachedWishlishsList);
            List<Address> attachedAddressList = new ArrayList<Address>();
            for (Address addressListAddressToAttach : account.getAddressList()) {
                addressListAddressToAttach = em.getReference(addressListAddressToAttach.getClass(), addressListAddressToAttach.getAddressid());
                attachedAddressList.add(addressListAddressToAttach);
            }
            account.setAddressList(attachedAddressList);
            List<Orders> attachedOrdersList = new ArrayList<Orders>();
            for (Orders ordersListOrdersToAttach : account.getOrdersList()) {
                ordersListOrdersToAttach = em.getReference(ordersListOrdersToAttach.getClass(), ordersListOrdersToAttach.getOrderid());
                attachedOrdersList.add(ordersListOrdersToAttach);
            }
            account.setOrdersList(attachedOrdersList);
            em.persist(account);
            for (Wishlishs wishlishsListWishlishs : account.getWishlishsList()) {
                Account oldAccountidOfWishlishsListWishlishs = wishlishsListWishlishs.getAccountid();
                wishlishsListWishlishs.setAccountid(account);
                wishlishsListWishlishs = em.merge(wishlishsListWishlishs);
                if (oldAccountidOfWishlishsListWishlishs != null) {
                    oldAccountidOfWishlishsListWishlishs.getWishlishsList().remove(wishlishsListWishlishs);
                    oldAccountidOfWishlishsListWishlishs = em.merge(oldAccountidOfWishlishsListWishlishs);
                }
            }
            for (Address addressListAddress : account.getAddressList()) {
                Account oldAccountidOfAddressListAddress = addressListAddress.getAccountid();
                addressListAddress.setAccountid(account);
                addressListAddress = em.merge(addressListAddress);
                if (oldAccountidOfAddressListAddress != null) {
                    oldAccountidOfAddressListAddress.getAddressList().remove(addressListAddress);
                    oldAccountidOfAddressListAddress = em.merge(oldAccountidOfAddressListAddress);
                }
            }
            for (Orders ordersListOrders : account.getOrdersList()) {
                Account oldAccountidOfOrdersListOrders = ordersListOrders.getAccountid();
                ordersListOrders.setAccountid(account);
                ordersListOrders = em.merge(ordersListOrders);
                if (oldAccountidOfOrdersListOrders != null) {
                    oldAccountidOfOrdersListOrders.getOrdersList().remove(ordersListOrders);
                    oldAccountidOfOrdersListOrders = em.merge(oldAccountidOfOrdersListOrders);
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

    public void edit(Account account) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account persistentAccount = em.find(Account.class, account.getAccountid());
            List<Wishlishs> wishlishsListOld = persistentAccount.getWishlishsList();
            List<Wishlishs> wishlishsListNew = account.getWishlishsList();
            List<Address> addressListOld = persistentAccount.getAddressList();
            List<Address> addressListNew = account.getAddressList();
            List<Orders> ordersListOld = persistentAccount.getOrdersList();
            List<Orders> ordersListNew = account.getOrdersList();
            List<Wishlishs> attachedWishlishsListNew = new ArrayList<Wishlishs>();
            for (Wishlishs wishlishsListNewWishlishsToAttach : wishlishsListNew) {
                wishlishsListNewWishlishsToAttach = em.getReference(wishlishsListNewWishlishsToAttach.getClass(), wishlishsListNewWishlishsToAttach.getWishlishid());
                attachedWishlishsListNew.add(wishlishsListNewWishlishsToAttach);
            }
            wishlishsListNew = attachedWishlishsListNew;
            account.setWishlishsList(wishlishsListNew);
            List<Address> attachedAddressListNew = new ArrayList<Address>();
            for (Address addressListNewAddressToAttach : addressListNew) {
                addressListNewAddressToAttach = em.getReference(addressListNewAddressToAttach.getClass(), addressListNewAddressToAttach.getAddressid());
                attachedAddressListNew.add(addressListNewAddressToAttach);
            }
            addressListNew = attachedAddressListNew;
            account.setAddressList(addressListNew);
            List<Orders> attachedOrdersListNew = new ArrayList<Orders>();
            for (Orders ordersListNewOrdersToAttach : ordersListNew) {
                ordersListNewOrdersToAttach = em.getReference(ordersListNewOrdersToAttach.getClass(), ordersListNewOrdersToAttach.getOrderid());
                attachedOrdersListNew.add(ordersListNewOrdersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            account.setOrdersList(ordersListNew);
            account = em.merge(account);
            for (Wishlishs wishlishsListOldWishlishs : wishlishsListOld) {
                if (!wishlishsListNew.contains(wishlishsListOldWishlishs)) {
                    wishlishsListOldWishlishs.setAccountid(null);
                    wishlishsListOldWishlishs = em.merge(wishlishsListOldWishlishs);
                }
            }
            for (Wishlishs wishlishsListNewWishlishs : wishlishsListNew) {
                if (!wishlishsListOld.contains(wishlishsListNewWishlishs)) {
                    Account oldAccountidOfWishlishsListNewWishlishs = wishlishsListNewWishlishs.getAccountid();
                    wishlishsListNewWishlishs.setAccountid(account);
                    wishlishsListNewWishlishs = em.merge(wishlishsListNewWishlishs);
                    if (oldAccountidOfWishlishsListNewWishlishs != null && !oldAccountidOfWishlishsListNewWishlishs.equals(account)) {
                        oldAccountidOfWishlishsListNewWishlishs.getWishlishsList().remove(wishlishsListNewWishlishs);
                        oldAccountidOfWishlishsListNewWishlishs = em.merge(oldAccountidOfWishlishsListNewWishlishs);
                    }
                }
            }
            for (Address addressListOldAddress : addressListOld) {
                if (!addressListNew.contains(addressListOldAddress)) {
                    addressListOldAddress.setAccountid(null);
                    addressListOldAddress = em.merge(addressListOldAddress);
                }
            }
            for (Address addressListNewAddress : addressListNew) {
                if (!addressListOld.contains(addressListNewAddress)) {
                    Account oldAccountidOfAddressListNewAddress = addressListNewAddress.getAccountid();
                    addressListNewAddress.setAccountid(account);
                    addressListNewAddress = em.merge(addressListNewAddress);
                    if (oldAccountidOfAddressListNewAddress != null && !oldAccountidOfAddressListNewAddress.equals(account)) {
                        oldAccountidOfAddressListNewAddress.getAddressList().remove(addressListNewAddress);
                        oldAccountidOfAddressListNewAddress = em.merge(oldAccountidOfAddressListNewAddress);
                    }
                }
            }
            for (Orders ordersListOldOrders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldOrders)) {
                    ordersListOldOrders.setAccountid(null);
                    ordersListOldOrders = em.merge(ordersListOldOrders);
                }
            }
            for (Orders ordersListNewOrders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNewOrders)) {
                    Account oldAccountidOfOrdersListNewOrders = ordersListNewOrders.getAccountid();
                    ordersListNewOrders.setAccountid(account);
                    ordersListNewOrders = em.merge(ordersListNewOrders);
                    if (oldAccountidOfOrdersListNewOrders != null && !oldAccountidOfOrdersListNewOrders.equals(account)) {
                        oldAccountidOfOrdersListNewOrders.getOrdersList().remove(ordersListNewOrders);
                        oldAccountidOfOrdersListNewOrders = em.merge(oldAccountidOfOrdersListNewOrders);
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
                Integer id = account.getAccountid();
                if (findAccount(id) == null) {
                    throw new NonexistentEntityException("The account with id " + id + " no longer exists.");
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
            Account account;
            try {
                account = em.getReference(Account.class, id);
                account.getAccountid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The account with id " + id + " no longer exists.", enfe);
            }
            List<Wishlishs> wishlishsList = account.getWishlishsList();
            for (Wishlishs wishlishsListWishlishs : wishlishsList) {
                wishlishsListWishlishs.setAccountid(null);
                wishlishsListWishlishs = em.merge(wishlishsListWishlishs);
            }
            List<Address> addressList = account.getAddressList();
            for (Address addressListAddress : addressList) {
                addressListAddress.setAccountid(null);
                addressListAddress = em.merge(addressListAddress);
            }
            List<Orders> ordersList = account.getOrdersList();
            for (Orders ordersListOrders : ordersList) {
                ordersListOrders.setAccountid(null);
                ordersListOrders = em.merge(ordersListOrders);
            }
            em.remove(account);
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

    public List<Account> findAccountEntities() {
        return findAccountEntities(true, -1, -1);
    }

    public List<Account> findAccountEntities(int maxResults, int firstResult) {
        return findAccountEntities(false, maxResults, firstResult);
    }

    private List<Account> findAccountEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Account.class));
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

    public Account findAccount(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Account.class, id);
        } finally {
            em.close();
        }
    }

    public int getAccountCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Account> rt = cq.from(Account.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public Account findByUsername(String username) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Account.findByUsername");
            query.setParameter("username", username.toUpperCase());
            return (Account) query.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

}
