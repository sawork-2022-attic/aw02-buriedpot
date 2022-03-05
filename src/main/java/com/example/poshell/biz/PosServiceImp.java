package com.example.poshell.biz;

import com.example.poshell.db.PosDB;
import com.example.poshell.model.Cart;
import com.example.poshell.model.Item;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class PosServiceImp implements PosService {

    private PosDB posDB;

    @Autowired
    public void setPosDB(PosDB posDB) {
        this.posDB = posDB;
    }

    @Override
    public Cart getCart() {
        return posDB.getCart();
    }

    @Override
    public Cart newCart() {
        return posDB.saveCart(new Cart());
    }

    @Override
    public String checkout(Cart cart) {
        String ret = "Checkout cash: " + String.valueOf(this.getCart().total());
        this.getCart().empty();
        return ret;
    }

    @Override
    public String total(Cart cart) {
        return String.valueOf(this.getCart().total());
    }

    @Override
    public boolean add(Product product, int amount) {
        return false;
    }

    @Override
    public boolean add(String productId, int amount) {

        Product product = posDB.getProduct(productId);
        if (product == null) return false;

        this.getCart().addItem(new Item(product, amount));
        return true;
    }

    @Override
    public boolean delete(String productId) {
        return this.getCart().deleteItem(productId);
    }

    @Override
    public boolean modify(String productId, int amount) {
        return this.getCart().modifyItem(productId, amount);
    }

    @Override
    public boolean empty() {
        return this.getCart().empty();
    }

    @Override
    public List<Product> products() {
        return posDB.getProducts();
    }
}
