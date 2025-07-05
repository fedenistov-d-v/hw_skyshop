package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        final Product apples = new SimpleProduct("Яблоки", 149);
        final Product oranges = new DiscountedProduct("Апельсины", 300, 15);
        final Product peaches = new DiscountedProduct("Персики", 500, 22);
        final Product bananas = new FixPriceProduct("Бананы");
        final Product pears = new SimpleProduct("Груши", 249);
        final Product watermelons = new SimpleProduct("Арбузы", 25);

        ProductBasket firstBasket = new ProductBasket();

        firstBasket.add(apples);
        firstBasket.add(oranges);
        firstBasket.add(peaches);
        firstBasket.add(bananas);
        firstBasket.add(pears);
        firstBasket.add(watermelons);

        firstBasket.printContents();
        System.out.println();
        System.out.print(firstBasket);
        System.out.printf("Общая стоимость товаров - %d%n", firstBasket.getTotalCost());
        findInBasket(firstBasket, "Персики");
        findInBasket(firstBasket, "Арбузы");
        firstBasket.clear();
        firstBasket.printContents();
        System.out.printf("Общая стоимость товаров - %d%n", firstBasket.getTotalCost());
        findInBasket(firstBasket, "Яблоки");
    }
    public static void findInBasket(ProductBasket basket, String name) {
        if (basket.findProduct(name)) {
            System.out.printf("Продукт %s есть в корзине.%n", name);
        } else {
            System.out.printf("%s - в корзине нет.%n", name);
        }
    }
}
