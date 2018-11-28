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
import comfortpay.model.Wishlish;
import java.util.ArrayList;
import java.util.List;
import comfortpay.model.Orderlist;
import comfortpay.model.Products;
import comfortpay.model.Size1;
import comfortpay.model.controller.exceptions.NonexistentEntityException;
import comfortpay.model.controller.exceptions.RollbackFailureException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author Joknoi
 */
public class ProductsJpaController implements Serializable {

    public ProductsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Products products) throws RollbackFailureException, Exception {
        if (products.getWishlishList() == null) {
            products.setWishlishList(new ArrayList<Wishlish>());
        }
        if (products.getOrderlistList() == null) {
            products.setOrderlistList(new ArrayList<Orderlist>());
        }
        if (products.getSize1List() == null) {
            products.setSize1List(new ArrayList<Size1>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Wishlish> attachedWishlishList = new ArrayList<Wishlish>();
            for (Wishlish wishlishListWishlishToAttach : products.getWishlishList()) {
                wishlishListWishlishToAttach = em.getReference(wishlishListWishlishToAttach.getClass(), wishlishListWishlishToAttach.getWishlishid());
                attachedWishlishList.add(wishlishListWishlishToAttach);
            }
            products.setWishlishList(attachedWishlishList);
            List<Orderlist> attachedOrderlistList = new ArrayList<Orderlist>();
            for (Orderlist orderlistListOrderlistToAttach : products.getOrderlistList()) {
                orderlistListOrderlistToAttach = em.getReference(orderlistListOrderlistToAttach.getClass(), orderlistListOrderlistToAttach.getOrderlistid());
                attachedOrderlistList.add(orderlistListOrderlistToAttach);
            }
            products.setOrderlistList(attachedOrderlistList);
            List<Size1> attachedSize1List = new ArrayList<Size1>();
            for (Size1 size1ListSize1ToAttach : products.getSize1List()) {
                size1ListSize1ToAttach = em.getReference(size1ListSize1ToAttach.getClass(), size1ListSize1ToAttach.getSizeid());
                attachedSize1List.add(size1ListSize1ToAttach);
            }
            products.setSize1List(attachedSize1List);
            em.persist(products);
            for (Wishlish wishlishListWishlish : products.getWishlishList()) {
                Products oldProductidOfWishlishListWishlish = wishlishListWishlish.getProductid();
                wishlishListWishlish.setProductid(products);
                wishlishListWishlish = em.merge(wishlishListWishlish);
                if (oldProductidOfWishlishListWishlish != null) {
                    oldProductidOfWishlishListWishlish.getWishlishList().remove(wishlishListWishlish);
                    oldProductidOfWishlishListWishlish = em.merge(oldProductidOfWishlishListWishlish);
                }
            }
            for (Orderlist orderlistListOrderlist : products.getOrderlistList()) {
                Products oldProductidOfOrderlistListOrderlist = orderlistListOrderlist.getProductid();
                orderlistListOrderlist.setProductid(products);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
                if (oldProductidOfOrderlistListOrderlist != null) {
                    oldProductidOfOrderlistListOrderlist.getOrderlistList().remove(orderlistListOrderlist);
                    oldProductidOfOrderlistListOrderlist = em.merge(oldProductidOfOrderlistListOrderlist);
                }
            }
            for (Size1 size1ListSize1 : products.getSize1List()) {
                Products oldProductidOfSize1ListSize1 = size1ListSize1.getProductid();
                size1ListSize1.setProductid(products);
                size1ListSize1 = em.merge(size1ListSize1);
                if (oldProductidOfSize1ListSize1 != null) {
                    oldProductidOfSize1ListSize1.getSize1List().remove(size1ListSize1);
                    oldProductidOfSize1ListSize1 = em.merge(oldProductidOfSize1ListSize1);
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

    public void edit(Products products) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Products persistentProducts = em.find(Products.class, products.getProductid());
            List<Wishlish> wishlishListOld = persistentProducts.getWishlishList();
            List<Wishlish> wishlishListNew = products.getWishlishList();
            List<Orderlist> orderlistListOld = persistentProducts.getOrderlistList();
            List<Orderlist> orderlistListNew = products.getOrderlistList();
            List<Size1> size1ListOld = persistentProducts.getSize1List();
            List<Size1> size1ListNew = products.getSize1List();
            List<Wishlish> attachedWishlishListNew = new ArrayList<Wishlish>();
            for (Wishlish wishlishListNewWishlishToAttach : wishlishListNew) {
                wishlishListNewWishlishToAttach = em.getReference(wishlishListNewWishlishToAttach.getClass(), wishlishListNewWishlishToAttach.getWishlishid());
                attachedWishlishListNew.add(wishlishListNewWishlishToAttach);
            }
            wishlishListNew = attachedWishlishListNew;
            products.setWishlishList(wishlishListNew);
            List<Orderlist> attachedOrderlistListNew = new ArrayList<Orderlist>();
            for (Orderlist orderlistListNewOrderlistToAttach : orderlistListNew) {
                orderlistListNewOrderlistToAttach = em.getReference(orderlistListNewOrderlistToAttach.getClass(), orderlistListNewOrderlistToAttach.getOrderlistid());
                attachedOrderlistListNew.add(orderlistListNewOrderlistToAttach);
            }
            orderlistListNew = attachedOrderlistListNew;
            products.setOrderlistList(orderlistListNew);
            List<Size1> attachedSize1ListNew = new ArrayList<Size1>();
            for (Size1 size1ListNewSize1ToAttach : size1ListNew) {
                size1ListNewSize1ToAttach = em.getReference(size1ListNewSize1ToAttach.getClass(), size1ListNewSize1ToAttach.getSizeid());
                attachedSize1ListNew.add(size1ListNewSize1ToAttach);
            }
            size1ListNew = attachedSize1ListNew;
            products.setSize1List(size1ListNew);
            products = em.merge(products);
            for (Wishlish wishlishListOldWishlish : wishlishListOld) {
                if (!wishlishListNew.contains(wishlishListOldWishlish)) {
                    wishlishListOldWishlish.setProductid(null);
                    wishlishListOldWishlish = em.merge(wishlishListOldWishlish);
                }
            }
            for (Wishlish wishlishListNewWishlish : wishlishListNew) {
                if (!wishlishListOld.contains(wishlishListNewWishlish)) {
                    Products oldProductidOfWishlishListNewWishlish = wishlishListNewWishlish.getProductid();
                    wishlishListNewWishlish.setProductid(products);
                    wishlishListNewWishlish = em.merge(wishlishListNewWishlish);
                    if (oldProductidOfWishlishListNewWishlish != null && !oldProductidOfWishlishListNewWishlish.equals(products)) {
                        oldProductidOfWishlishListNewWishlish.getWishlishList().remove(wishlishListNewWishlish);
                        oldProductidOfWishlishListNewWishlish = em.merge(oldProductidOfWishlishListNewWishlish);
                    }
                }
            }
            for (Orderlist orderlistListOldOrderlist : orderlistListOld) {
                if (!orderlistListNew.contains(orderlistListOldOrderlist)) {
                    orderlistListOldOrderlist.setProductid(null);
                    orderlistListOldOrderlist = em.merge(orderlistListOldOrderlist);
                }
            }
            for (Orderlist orderlistListNewOrderlist : orderlistListNew) {
                if (!orderlistListOld.contains(orderlistListNewOrderlist)) {
                    Products oldProductidOfOrderlistListNewOrderlist = orderlistListNewOrderlist.getProductid();
                    orderlistListNewOrderlist.setProductid(products);
                    orderlistListNewOrderlist = em.merge(orderlistListNewOrderlist);
                    if (oldProductidOfOrderlistListNewOrderlist != null && !oldProductidOfOrderlistListNewOrderlist.equals(products)) {
                        oldProductidOfOrderlistListNewOrderlist.getOrderlistList().remove(orderlistListNewOrderlist);
                        oldProductidOfOrderlistListNewOrderlist = em.merge(oldProductidOfOrderlistListNewOrderlist);
                    }
                }
            }
            for (Size1 size1ListOldSize1 : size1ListOld) {
                if (!size1ListNew.contains(size1ListOldSize1)) {
                    size1ListOldSize1.setProductid(null);
                    size1ListOldSize1 = em.merge(size1ListOldSize1);
                }
            }
            for (Size1 size1ListNewSize1 : size1ListNew) {
                if (!size1ListOld.contains(size1ListNewSize1)) {
                    Products oldProductidOfSize1ListNewSize1 = size1ListNewSize1.getProductid();
                    size1ListNewSize1.setProductid(products);
                    size1ListNewSize1 = em.merge(size1ListNewSize1);
                    if (oldProductidOfSize1ListNewSize1 != null && !oldProductidOfSize1ListNewSize1.equals(products)) {
                        oldProductidOfSize1ListNewSize1.getSize1List().remove(size1ListNewSize1);
                        oldProductidOfSize1ListNewSize1 = em.merge(oldProductidOfSize1ListNewSize1);
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
                Integer id = products.getProductid();
                if (findProducts(id) == null) {
                    throw new NonexistentEntityException("The products with id " + id + " no longer exists.");
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
            Products products;
            try {
                products = em.getReference(Products.class, id);
                products.getProductid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The products with id " + id + " no longer exists.", enfe);
            }
            List<Wishlish> wishlishList = products.getWishlishList();
            for (Wishlish wishlishListWishlish : wishlishList) {
                wishlishListWishlish.setProductid(null);
                wishlishListWishlish = em.merge(wishlishListWishlish);
            }
            List<Orderlist> orderlistList = products.getOrderlistList();
            for (Orderlist orderlistListOrderlist : orderlistList) {
                orderlistListOrderlist.setProductid(null);
                orderlistListOrderlist = em.merge(orderlistListOrderlist);
            }
            List<Size1> size1List = products.getSize1List();
            for (Size1 size1ListSize1 : size1List) {
                size1ListSize1.setProductid(null);
                size1ListSize1 = em.merge(size1ListSize1);
            }
            em.remove(products);
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

    public List<Products> findProductsEntities() {
        return findProductsEntities(true, -1, -1);
    }

    public List<Products> findProductsEntities(int maxResults, int firstResult) {
        return findProductsEntities(false, maxResults, firstResult);
    }

    private List<Products> findProductsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Products.class));
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

    public Products findProducts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Products.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Products> rt = cq.from(Products.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Products> findByBrand(String brand) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByProductband");
            query.setParameter("productband", brand.toUpperCase());
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
}
