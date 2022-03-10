/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shopping;

import java.util.HashMap;
import java.util.Map;
import product.Product;

/**
 *
 * @author markhipz
 */
public class Cart {

    private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public void add(Product product) {
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }

        if (this.cart.containsKey(product.getProductID())) {
            int quantity = this.cart.get(product.getProductID()).getQuantity();
            product.setQuantity(1 + quantity);
            update(product);
            return;
        }

        this.cart.put(product.getProductID(), product);
    }

    public void delete(String productID) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(productID)) {
            this.cart.remove(productID);
        }
    }

    public void update(Product product) {
        if (this.cart == null) {
            return;
        }
        if (this.cart.containsKey(product.getProductID())) {
            this.cart.replace(product.getProductID(), product);
        }
    }

}
