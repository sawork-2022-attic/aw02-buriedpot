package com.example.poshell.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {

    private List<Item> items = new ArrayList<>();

    public boolean addItem(Item item) {

        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item1 = it.next();
            if (item1.getProduct().getId().equals(item.getProduct().getId())) {
                int newAmount = item1.getAmount() + item.getAmount();
                if (newAmount <= 0) {
                    it.remove();
                }
                else {
                    item1.setAmount(newAmount);
                }
                return true;
            }
        }
        if (item.getAmount() <= 0) return false;
        return items.add(item);
    }

    public boolean deleteItem(String productId) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getProduct().getId().equals(productId)) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public boolean modifyItem(String productId, int amount) {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getProduct().getId().equals(productId)) {
                if (amount > 0) {
                    item.setAmount(amount);
                }
                else {
                    it.remove();
                }
                return true;
            }
        }
        return false;
    }

    public boolean empty() {
        items = new ArrayList<>();
        return true;
    }

    public double total() {
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < items.size(); i++) {
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }

        stringBuilder.append("Total...\t\t\t" + total );
        return total;
    }

    @Override
    public String toString() {
        if (items.size() ==0){
            return "Empty Cart";
        }
        double total = 0;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Cart -----------------\n"  );

        for (int i = 0; i < items.size(); i++) {
            stringBuilder.append(items.get(i).toString()).append("\n");
            total += items.get(i).getAmount() * items.get(i).getProduct().getPrice();
        }
        stringBuilder.append("----------------------\n"  );

        stringBuilder.append("Total...\t\t\t" + total );

        return stringBuilder.toString();
    }
}
