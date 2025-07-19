package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.exceptions.BestResultNotFound;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.utils.SearchEngine;
import org.skypro.skyshop.utils.Searchable;

import java.util.Arrays;

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
//            e.printStackTrace();
        }
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

        Searchable[] arr = searchEngine.search("Яблок");
        System.out.println(Arrays.toString(arr));
        System.out.println();
        for (Searchable searchable : arr) {
            if (searchable != null) {
                System.out.println(searchable.getStringRepresentation());
            }
        }
        System.out.println("   --- SearchEngine.searchForMostSuitable(\"о\") ---");
        try {
            System.out.println(searchEngine.searchForMostSuitable("о"));
        } catch (BestResultNotFound e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        System.out.println("   --- SearchEngine.searchForMostSuitable(\"Кукуруза\") ---");
        try {
            System.out.println(searchEngine.searchForMostSuitable("Кукуруза"));
        } catch (BestResultNotFound e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        System.out.println("Завершение проверки кода.");
    }

    public static void findInBasket(ProductBasket basket, String name) {
        if (basket.findProduct(name)) {
            System.out.printf("Продукт %s есть в корзине.%n", name);
        } else {
            System.out.printf("%s - в корзине нет.%n", name);
        }
    }
}
