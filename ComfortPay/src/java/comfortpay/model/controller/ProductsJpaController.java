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
import comfortpay.model.Wishlishs;
import java.util.ArrayList;
import java.util.List;
import comfortpay.model.Orderlists;
import comfortpay.model.Products;
import comfortpay.model.Sizes;
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
        if (products.getWishlishsList() == null) {
            products.setWishlishsList(new ArrayList<Wishlishs>());
        }
        if (products.getOrderlistsList() == null) {
            products.setOrderlistsList(new ArrayList<Orderlists>());
        }
        if (products.getSizesList() == null) {
            products.setSizesList(new ArrayList<Sizes>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Wishlishs> attachedWishlishsList = new ArrayList<Wishlishs>();
            for (Wishlishs wishlishsListWishlishsToAttach : products.getWishlishsList()) {
                wishlishsListWishlishsToAttach = em.getReference(wishlishsListWishlishsToAttach.getClass(), wishlishsListWishlishsToAttach.getWishlishid());
                attachedWishlishsList.add(wishlishsListWishlishsToAttach);
            }
            products.setWishlishsList(attachedWishlishsList);
            List<Orderlists> attachedOrderlistsList = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListOrderlistsToAttach : products.getOrderlistsList()) {
                orderlistsListOrderlistsToAttach = em.getReference(orderlistsListOrderlistsToAttach.getClass(), orderlistsListOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsList.add(orderlistsListOrderlistsToAttach);
            }
            products.setOrderlistsList(attachedOrderlistsList);
            List<Sizes> attachedSizesList = new ArrayList<Sizes>();
            for (Sizes sizesListSizesToAttach : products.getSizesList()) {
                sizesListSizesToAttach = em.getReference(sizesListSizesToAttach.getClass(), sizesListSizesToAttach.getSizeid());
                attachedSizesList.add(sizesListSizesToAttach);
            }
            products.setSizesList(attachedSizesList);
            em.persist(products);
            for (Wishlishs wishlishsListWishlishs : products.getWishlishsList()) {
                Products oldProductidOfWishlishsListWishlishs = wishlishsListWishlishs.getProductid();
                wishlishsListWishlishs.setProductid(products);
                wishlishsListWishlishs = em.merge(wishlishsListWishlishs);
                if (oldProductidOfWishlishsListWishlishs != null) {
                    oldProductidOfWishlishsListWishlishs.getWishlishsList().remove(wishlishsListWishlishs);
                    oldProductidOfWishlishsListWishlishs = em.merge(oldProductidOfWishlishsListWishlishs);
                }
            }
            for (Orderlists orderlistsListOrderlists : products.getOrderlistsList()) {
                Products oldProductidOfOrderlistsListOrderlists = orderlistsListOrderlists.getProductid();
                orderlistsListOrderlists.setProductid(products);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
                if (oldProductidOfOrderlistsListOrderlists != null) {
                    oldProductidOfOrderlistsListOrderlists.getOrderlistsList().remove(orderlistsListOrderlists);
                    oldProductidOfOrderlistsListOrderlists = em.merge(oldProductidOfOrderlistsListOrderlists);
                }
            }
            for (Sizes sizesListSizes : products.getSizesList()) {
                Products oldProductidOfSizesListSizes = sizesListSizes.getProductid();
                sizesListSizes.setProductid(products);
                sizesListSizes = em.merge(sizesListSizes);
                if (oldProductidOfSizesListSizes != null) {
                    oldProductidOfSizesListSizes.getSizesList().remove(sizesListSizes);
                    oldProductidOfSizesListSizes = em.merge(oldProductidOfSizesListSizes);
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
            List<Wishlishs> wishlishsListOld = persistentProducts.getWishlishsList();
            List<Wishlishs> wishlishsListNew = products.getWishlishsList();
            List<Orderlists> orderlistsListOld = persistentProducts.getOrderlistsList();
            List<Orderlists> orderlistsListNew = products.getOrderlistsList();
            List<Sizes> sizesListOld = persistentProducts.getSizesList();
            List<Sizes> sizesListNew = products.getSizesList();
            List<Wishlishs> attachedWishlishsListNew = new ArrayList<Wishlishs>();
            for (Wishlishs wishlishsListNewWishlishsToAttach : wishlishsListNew) {
                wishlishsListNewWishlishsToAttach = em.getReference(wishlishsListNewWishlishsToAttach.getClass(), wishlishsListNewWishlishsToAttach.getWishlishid());
                attachedWishlishsListNew.add(wishlishsListNewWishlishsToAttach);
            }
            wishlishsListNew = attachedWishlishsListNew;
            products.setWishlishsList(wishlishsListNew);
            List<Orderlists> attachedOrderlistsListNew = new ArrayList<Orderlists>();
            for (Orderlists orderlistsListNewOrderlistsToAttach : orderlistsListNew) {
                orderlistsListNewOrderlistsToAttach = em.getReference(orderlistsListNewOrderlistsToAttach.getClass(), orderlistsListNewOrderlistsToAttach.getOrderlistid());
                attachedOrderlistsListNew.add(orderlistsListNewOrderlistsToAttach);
            }
            orderlistsListNew = attachedOrderlistsListNew;
            products.setOrderlistsList(orderlistsListNew);
            List<Sizes> attachedSizesListNew = new ArrayList<Sizes>();
            for (Sizes sizesListNewSizesToAttach : sizesListNew) {
                sizesListNewSizesToAttach = em.getReference(sizesListNewSizesToAttach.getClass(), sizesListNewSizesToAttach.getSizeid());
                attachedSizesListNew.add(sizesListNewSizesToAttach);
            }
            sizesListNew = attachedSizesListNew;
            products.setSizesList(sizesListNew);
            products = em.merge(products);
            for (Wishlishs wishlishsListOldWishlishs : wishlishsListOld) {
                if (!wishlishsListNew.contains(wishlishsListOldWishlishs)) {
                    wishlishsListOldWishlishs.setProductid(null);
                    wishlishsListOldWishlishs = em.merge(wishlishsListOldWishlishs);
                }
            }
            for (Wishlishs wishlishsListNewWishlishs : wishlishsListNew) {
                if (!wishlishsListOld.contains(wishlishsListNewWishlishs)) {
                    Products oldProductidOfWishlishsListNewWishlishs = wishlishsListNewWishlishs.getProductid();
                    wishlishsListNewWishlishs.setProductid(products);
                    wishlishsListNewWishlishs = em.merge(wishlishsListNewWishlishs);
                    if (oldProductidOfWishlishsListNewWishlishs != null && !oldProductidOfWishlishsListNewWishlishs.equals(products)) {
                        oldProductidOfWishlishsListNewWishlishs.getWishlishsList().remove(wishlishsListNewWishlishs);
                        oldProductidOfWishlishsListNewWishlishs = em.merge(oldProductidOfWishlishsListNewWishlishs);
                    }
                }
            }
            for (Orderlists orderlistsListOldOrderlists : orderlistsListOld) {
                if (!orderlistsListNew.contains(orderlistsListOldOrderlists)) {
                    orderlistsListOldOrderlists.setProductid(null);
                    orderlistsListOldOrderlists = em.merge(orderlistsListOldOrderlists);
                }
            }
            for (Orderlists orderlistsListNewOrderlists : orderlistsListNew) {
                if (!orderlistsListOld.contains(orderlistsListNewOrderlists)) {
                    Products oldProductidOfOrderlistsListNewOrderlists = orderlistsListNewOrderlists.getProductid();
                    orderlistsListNewOrderlists.setProductid(products);
                    orderlistsListNewOrderlists = em.merge(orderlistsListNewOrderlists);
                    if (oldProductidOfOrderlistsListNewOrderlists != null && !oldProductidOfOrderlistsListNewOrderlists.equals(products)) {
                        oldProductidOfOrderlistsListNewOrderlists.getOrderlistsList().remove(orderlistsListNewOrderlists);
                        oldProductidOfOrderlistsListNewOrderlists = em.merge(oldProductidOfOrderlistsListNewOrderlists);
                    }
                }
            }
            for (Sizes sizesListOldSizes : sizesListOld) {
                if (!sizesListNew.contains(sizesListOldSizes)) {
                    sizesListOldSizes.setProductid(null);
                    sizesListOldSizes = em.merge(sizesListOldSizes);
                }
            }
            for (Sizes sizesListNewSizes : sizesListNew) {
                if (!sizesListOld.contains(sizesListNewSizes)) {
                    Products oldProductidOfSizesListNewSizes = sizesListNewSizes.getProductid();
                    sizesListNewSizes.setProductid(products);
                    sizesListNewSizes = em.merge(sizesListNewSizes);
                    if (oldProductidOfSizesListNewSizes != null && !oldProductidOfSizesListNewSizes.equals(products)) {
                        oldProductidOfSizesListNewSizes.getSizesList().remove(sizesListNewSizes);
                        oldProductidOfSizesListNewSizes = em.merge(oldProductidOfSizesListNewSizes);
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
            List<Wishlishs> wishlishsList = products.getWishlishsList();
            for (Wishlishs wishlishsListWishlishs : wishlishsList) {
                wishlishsListWishlishs.setProductid(null);
                wishlishsListWishlishs = em.merge(wishlishsListWishlishs);
            }
            List<Orderlists> orderlistsList = products.getOrderlistsList();
            for (Orderlists orderlistsListOrderlists : orderlistsList) {
                orderlistsListOrderlists.setProductid(null);
                orderlistsListOrderlists = em.merge(orderlistsListOrderlists);
            }
            List<Sizes> sizesList = products.getSizesList();
            for (Sizes sizesListSizes : sizesList) {
                sizesListSizes.setProductid(null);
                sizesListSizes = em.merge(sizesListSizes);
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

    public List<Products> findByBrand(String search) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByProductband");
            query.setParameter("productband", search);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByColor(String search) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByColor");
            query.setParameter("color", search);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByCloth() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByCloth");
            query.setParameter("shirt", "SHIRT");
            query.setParameter("bra", "BRA");
            query.setParameter("hood", "HOOD");
            query.setParameter("pant", "PANT");
            query.setParameter("short", "SHORT");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByShoes() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByShoes");
            query.setParameter("sneaker", "SNEAKER");
            query.setParameter("slipper", "SLIPPER");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByType(String search) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByProducttype");
            query.setParameter("producttype", search);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByProductName(String search) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByProductname");
            query.setParameter("productname", "%" + search + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Products> findByFilter(String search, String brand, String type, String color, String price) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Products.findByFilter");
            query.setParameter("productname", "%" + search + "%");
            query.setParameter("productbrand", "%" + brand + "%");
            query.setParameter("producttype", "%" + type + "%");
            query.setParameter("color", "%" + color + "%");
            double start = Double.parseDouble(price.substring(0, 4));
            double end = Double.parseDouble(price.substring(5, 9));
            query.setParameter("start", start);
            query.setParameter("end", end);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
