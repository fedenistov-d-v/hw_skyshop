package org.skypro.skyshop.utils;

import org.skypro.skyshop.exceptions.BestResultNotFound;

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

    public Searchable searchForMostSuitable(String search) throws BestResultNotFound {
        Searchable result = null;
        int numberOfMatches = 0;
        for (Searchable content : contents) {
            int number = сountingMatches(content.getSearchTerm(), search);
            if (number > numberOfMatches) {
                result = content;
                numberOfMatches = number;
            }
        }
        if (result == null) {
            throw new BestResultNotFound(search);
        }
        return result;
    }

    private int сountingMatches(String str, String substring) {
        int number = 0;
        int index = 0;
        int indexSubstring = str.indexOf(substring, index);

        while (indexSubstring != -1) {
            number++;
            index = indexSubstring + substring.length();
            indexSubstring = str.indexOf(substring, index);
        }
        return number;
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
