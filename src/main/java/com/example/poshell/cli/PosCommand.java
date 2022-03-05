package com.example.poshell.cli;

import com.example.poshell.biz.PosService;
import com.example.poshell.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class PosCommand {

    private PosService posService;

    @Autowired
    public void setPosService(PosService posService) {
        this.posService = posService;
    }

    @ShellMethod(value = "List Products", key = "p")
    public String products() {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (Product product : posService.products()) {
            stringBuilder.append("\t").append(++i).append("\t").append(product).append("\n");
        }
        return stringBuilder.toString();
    }

    @ShellMethod(value = "New Cart", key = "n")
    public String newCart() {
        return posService.newCart() + " OK";
    }

    @ShellMethod(value = "Add a Product to Cart", key = "a")
    public String addToCart(String productId, int amount) {
        if (posService.add(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Delete a Product from Cart", key = "d")
    public String deleteFromCart(String productId) {
        if (posService.delete(productId)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Modify amount of an Item in Cart", key = "m")
    public String modifyCartItem(String productId, int amount) {
        if (posService.modify(productId, amount)) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Empty the Cart", key = "e")
    public String emptyCart() {
        if (posService.empty()) {
            return posService.getCart().toString();
        }
        return "ERROR";
    }

    @ShellMethod(value = "Show the Cart", key = "s")
    public String showCart() {
        return posService.getCart().toString();
    }

    @ShellMethod(value = "Show the Cart Total", key = "t")
    public String total() {
        return String.valueOf(posService.total(posService.getCart()));
    }

    @ShellMethod(value = "Checkout", key = "c")
    public String checkout() {
        return posService.checkout(posService.getCart());
    }
}
