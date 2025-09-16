package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private Map<String, List<Product>> content;

    public ProductBasket() {
        this.content = new LinkedHashMap<>();
    }

    public void add(Product newProduct) {
        content.computeIfAbsent(newProduct.getName(), k -> new LinkedList<>()).add(newProduct);
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (Map.Entry<String, List<Product>> prods : content.entrySet()) {
            for (final Product product : prods.getValue()) {
                totalCost += product.getPrice();
            }
        }
        return totalCost;
    }

    public int countNumberSpecial() {
        int count = 0;
        for (Map.Entry<String, List<Product>> prods : content.entrySet()) {
            for (final Product product : prods.getValue()) {
                if (product.isSpecial()) count += 1;
            }
        }
        return count;
    }

    public void printContents() {
        if (content.isEmpty()) {
            System.out.println("В корзине пусто.");
            return;
        }
        System.out.printf("Итого: %d%n", getTotalCost());
        for (Map.Entry<String, List<Product>> prods : content.entrySet()) {
            for (final Product product : prods.getValue()) {
                System.out.print(product);
                System.out.println();
            }
        }
        System.out.printf("Итого: %d%n", getTotalCost());
        System.out.printf("Специальных товаров: %d%n", countNumberSpecial());
    }

    public boolean findProduct(String name) {
        return content.containsKey(name);
    }

    public List<Product> removeByName(String name) {
        List<Product> removed = new LinkedList<>();
        if (content.containsKey(name)) {
            removed = content.get(name);
            content.remove(name);
        }
        return removed;
    }

    public void clear() {
        content.clear();
    }

    @Override
    public String toString() {
        StringBuilder printBasket = new StringBuilder("Содержание корзины:" + '\n');
        for (Map.Entry<String, List<Product>> prods : content.entrySet()) {
            for (final Product product : prods.getValue()) {
                printBasket.append(product);
                printBasket.append('\n');
            }
        }
        printBasket.deleteCharAt(printBasket.length() - 1);
        return printBasket.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProductBasket that = (ProductBasket) o;
        return Objects.deepEquals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

}
