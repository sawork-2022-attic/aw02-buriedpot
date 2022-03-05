package com.example.poshell.biz;

import com.example.poshell.model.Cart;
import com.example.poshell.model.Product;

import java.util.List;

public interface PosService {
    public Cart getCart();

    public Cart newCart();

    public String checkout(Cart cart);

    public String total(Cart cart);

    public boolean add(Product product, int amount);

    public boolean add(String productId, int amount);

    public boolean delete(String productId); // delete an item in the cart by productId

    public boolean modify(String productId, int amount); // modify amount of some item in the cart

    public boolean empty();// empty the cart


    public List<Product> products();
}
