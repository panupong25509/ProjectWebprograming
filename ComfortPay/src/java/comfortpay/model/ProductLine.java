/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comfortpay.model;

/**
 *
 * @author Joknoi
 */
public class ProductLine {

    private Products product;
    private int quantity;
    private double totalPrice;

    public ProductLine() {
    }

    public ProductLine(Products product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        TotalPrice();
    }

    ProductLine(Products product) {
        this.product = product;
        this.quantity++;
    }

    public double TotalPrice() {
        this.totalPrice = product.getPrice() * this.quantity;
        return this.totalPrice;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
