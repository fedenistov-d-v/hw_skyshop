package org.skypro.skyshop.utils;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SearchEngine {
    private static final int NUMBER_OF_FOUND = 5;
    private Searchable[] contents;

    public SearchEngine(int size) {
        this.contents = new Searchable[size];
    }

    public Searchable[] search(String term) {
        int count = 0;
        Searchable[] foundContents = new Searchable[NUMBER_OF_FOUND];
        for (Searchable content : contents) {
            if (content.getSearchTerm().contains(term)) {
                foundContents[count] = content;
                count++;
            }
            if (count == NUMBER_OF_FOUND) break;
        }
        return foundContents;
    }

    public void add(Searchable object) {
        for (int i = 0; i < contents.length; i++) {
            if (contents[i] == null) {
                contents[i] = object;
                return;
            }
        }
        System.out.println("Переполнение массива, объукт не добавлен");
    }

    @Override
    public String toString() {
        StringBuilder printBasket = new StringBuilder();
        for (final Searchable searchable : contents) {
            if (searchable != null)
                printBasket.append(searchable.getStringRepresentation()).append('\n');
        }
        return printBasket.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SearchEngine that = (SearchEngine) o;
        return Objects.deepEquals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(contents);
    }
}
