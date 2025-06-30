package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.Arrays;
import java.util.Objects;

public class ProductBasket {
    private final Product[] content;
    private final int SIZE = 5;

    public ProductBasket() {
        this.content = new Product[SIZE];
    }

    public void add(Product newProduct) {
        boolean productAdded = false;
        for (int i = 0; i < content.length; i++) {
            if (content[i] == null) {
                content[i] = newProduct;
                productAdded = true;
                break;
            }
        }
        if (!productAdded) {
            System.out.println("Невозможно добавить продукт! Корзина переполнена.");
        }
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (final Product product : content) {
            if (product != null) totalCost += product.getPrice();
        }
        return totalCost;
    }

    public int countNumberSpecial(){
        int count = 0;
        for (final Product product : content) {
            if (product.isSpecial()) count += 1;
        }
        return count;
    }

    public void printContents() {
        boolean contentEmpty = true;
        for (final Product product : content) {
            if (product != null) {
                System.out.print(product);
                contentEmpty = false;
            }
            ;
        }
        if (contentEmpty) {
            System.out.println("В корзине пусто.");
        } else {
            System.out.printf("Итого: %d%n", getTotalCost());
            System.out.printf("Специальных товаров: %d%n", countNumberSpecial());
        }
    }

    public boolean findProduct(String name) {
        for (final Product product : content) {
            if (product != null && product.getName().equals(name))
                return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < content.length; i++)
            content[i] = null;
    }

    @Override
    public String toString() {
        StringBuilder printBasket = new StringBuilder();
        for (final Product product : content) {
            if (product != null) printBasket.append(product);
        }
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
        return Arrays.hashCode(content);
    }
}
