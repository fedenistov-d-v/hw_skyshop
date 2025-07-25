package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.utils.SearchEngine;
import org.skypro.skyshop.utils.Searchable;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SimpleProduct apples = null;
        DiscountedProduct oranges = null;
        DiscountedProduct peaches = null;
        FixPriceProduct bananas = null;
        SimpleProduct pears = null;
        SimpleProduct watermelons = null;
        SimpleProduct melons = null;
        SimpleProduct plums = null;
        DiscountedProduct strawberry = null;

        try {
            apples = new SimpleProduct("Яблоки", 149);
            oranges = new DiscountedProduct("Апельсины", 300, 15);
            peaches = new DiscountedProduct("Персики", 500, 22);
            bananas = new FixPriceProduct("Бананы");
            pears = new SimpleProduct("Груши", 249);
            watermelons = new SimpleProduct("Арбузы", 25);
            melons = new SimpleProduct(" ", 25);
            plums = new SimpleProduct("Сливы", 0);
            strawberry = new DiscountedProduct("Клубника", 800, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("   !!! Исключение: " + e);
        }
        ProductBasket basket = new ProductBasket();

        basket.add(apples);
        basket.add(oranges);
        basket.add(peaches);
        basket.add(bananas);
        basket.add(pears);
        basket.add(watermelons);
        basket.add(oranges);

        basket.printContents();
        System.out.println();
        System.out.println(basket);
        System.out.printf("Общая стоимость товаров - %d%n", basket.getTotalCost());
        findInBasket(basket, "Персики");
        findInBasket(basket, "Арбузы");
//        Очистка корзины отключена для тестирования задания №5
//        basket.clear();
//        basket.printContents();
//        System.out.printf("Общая стоимость товаров - %d%n", basket.getTotalCost());
//        findInBasket(basket, "Яблоки");

        //Test work 3
        System.out.printf("%n3. Тест работы Полиморфизм и Интерфейсы.%n");
        SearchEngine searchEngine = new SearchEngine(10);
        Article articleApples = new Article("Яблоки",
                "Из яблок можно приготовить самое большое количество блюд, " +
                        "чем из других фруктов.");
        Article articleOrange = new Article("Апельсины",
                "Свежевыжатый апельсиновый сок самый востребованный сок в мире");
        Article articleCherry = new Article("Вишня",
                "Лучшие соки из нашей вишни. И самые вкусные пироги");
        Article articleLemon = new Article("Лимон",
                "Для лимонада в жаркий день");

        searchEngine.add(apples);
        searchEngine.add(oranges);
        searchEngine.add(peaches);
        searchEngine.add(bananas);
        searchEngine.add(pears);
        searchEngine.add(watermelons);
        searchEngine.add(articleCherry);
        searchEngine.add(articleLemon);
        searchEngine.add(articleApples);
        searchEngine.add(articleOrange);

        System.out.println(searchEngine);

        List<Searchable> list = searchEngine.search("Яблок");
        System.out.println(list);
        for (Searchable searchable : list) {
            if (searchable != null) {
                System.out.println(searchable.getStringRepresentation());
            }
        }
        System.out.println("   --- SearchEngine.searchForMostSuitable(\"о\") ---");
        try {
            System.out.println(searchEngine.searchForMostSuitable("о"));
        } catch (BestResultNotFound e) {
            System.out.println(e.toString());
        }
        System.out.println("   --- SearchEngine.searchForMostSuitable(\"Кукуруза\") ---");
        try {
            System.out.println(searchEngine.searchForMostSuitable("Кукуруза"));
        } catch (BestResultNotFound e) {
            System.out.println(e.toString());
        }

        // Test 5 - Java Collections Framework: List
        System.out.println();
        System.out.println("5. Тест --- Java Collections Framework: List ---");
        Product toBeRemoved = oranges;
        System.out.println("Удаляем существующий продукт из корзины: " +
                toBeRemoved.getName());
        List<Product> listRemovedProducts = basket.removeByName(toBeRemoved.getName());
        System.out.println("Список удалённы объектов:");
        printListProduct(listRemovedProducts);
        System.out.println(basket);
        System.out.println("Удаляем несуществующий продукт из корзины: " +
                toBeRemoved.getName());
        listRemovedProducts = basket.removeByName(toBeRemoved.getName());
        System.out.println("Список удалённы объектов:");
        printListProduct(listRemovedProducts);
        System.out.println(basket);
    }

    private static void printListProduct(List<Product> listProducts) {
        if (listProducts.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            System.out.println(listProducts);
        }
    }

    public static void findInBasket(ProductBasket basket, String name) {
        if (basket.findProduct(name)) {
            System.out.printf("Продукт %s есть в корзине.%n", name);
        } else {
            System.out.printf("%s - в корзине нет.%n", name);
        }
    }
}
