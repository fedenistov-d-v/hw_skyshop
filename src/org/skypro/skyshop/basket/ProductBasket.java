package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private List<Product> content;

    public ProductBasket() {
        this.content = new LinkedList<>();
    }

    public void add(Product newProduct) {
        content.add(newProduct);
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (final Product product : content) {
            totalCost += product.getPrice();
        }
        return totalCost;
    }

    public int countNumberSpecial() {
        int count = 0;
        for (final Product product : content) {
            if (product.isSpecial()) count += 1;
        }
        return count;
    }

    public void printContents() {
        if (content.isEmpty()) {
            System.out.println("В корзине пусто.");
            return;
        }
        for (final Product product : content) {
            System.out.print(product);
            System.out.println();
        }
        System.out.printf("Итого: %d%n", getTotalCost());
        System.out.printf("Специальных товаров: %d%n", countNumberSpecial());
    }

    public boolean findProduct(String name) {
        for (final Product product : content) {
            if (product.getName().equals(name))
                return true;
        }
        return false;
    }

    public List<Product> removeByName(String name) {
        List<Product> removed = new LinkedList<>();
        Iterator<Product> iterator = content.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();
            if (product.getName().equals(name)) {
                removed.add(product);
                iterator.remove();
            }
        }
        return removed;
    }

    public void clear() {
        content.clear();
    }

    @Override
    public String toString() {
        StringBuilder printBasket = new StringBuilder("Содержание корзины:" + '\n');
        for (final Product product : content) {
            printBasket.append(product);
            printBasket.append('\n');
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
