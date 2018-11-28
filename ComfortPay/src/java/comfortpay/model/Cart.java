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
    private Map<String, ProductLine> cart;

    public Cart() {
        cart = new HashMap();
    }

    public void add(Products p) {
        ProductLine line = cart.get(p.getProductcode());
        if (line == null) {
            cart.put(p.getProductcode(), new ProductLine(p));
        } else {
            line.setQuantity(line.getQuantity()+1);
        }
    }

    public void reduce(Products p) {
        ProductLine line = cart.get(p.getProductcode());
        line.setQuantity(line.getQuantity() - 1);
        if (line.getQuantity() == 0) {
            remove(p);
        }
    }

    public void remove(Products p) {
        cart.remove(p.getProductcode());
    }

    public int getTotalQuantity() {
        int all = 0;
        Collection<ProductLine> productLine = this.cart.values();
        for (ProductLine productline : productLine) {
            all += productline.getQuantity();
        }
        return all;
    }

    public Map<String, ProductLine> getCart() {
        return cart;
    }

    public void setCart(Map<String, ProductLine> cart) {
        this.cart = cart;
    }

    public List<ProductLine> getProductLine() {
        return new ArrayList(cart.values());
    }
}
