/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Joknoi
 */
public class Cart {

    private Map<Integer, ProductLine> cart;

    public Cart() {
        cart = new HashMap();
    }

    public void add(Products p, Sizes s) {
        ProductLine line = cart.get(s.getSizeid());
        if (line == null) {
            cart.put(s.getSizeid(), new ProductLine(p, s));
        } else {
            line.setQuantity(line.getQuantity() + 1);
        }
//        line.TotalPrice();
    }

    public void reduce(Sizes s) {
        ProductLine line = cart.get(s.getSizeid());
        line.setQuantity(line.getQuantity() - 1);
        if (line.getQuantity() == 0) {
            remove(s);
        }
    }

    public void remove(Sizes s) {
        cart.remove(s.getSizeid());
    }

    public int getTotalQuantity() {
        int all = 0;
        Collection<ProductLine> productLine = this.cart.values();
        for (ProductLine productline : productLine) {
            all += productline.getQuantity();
        }
        return all;
    }
    
    public Double getTotalPrice() {
        double all = 0;
        Collection<ProductLine> productLine = this.cart.values();
        for (ProductLine productline : productLine) {
            all += productline.TotalPrice();
        }
        return all;
    }

    public Map<Integer, ProductLine> getCart() {
        return cart;
    }

    public void setCart(Map<Integer, ProductLine> cart) {
        this.cart = cart;
    }

    public List<ProductLine> getProductLine() {
        return new ArrayList(cart.values());
    }
}
