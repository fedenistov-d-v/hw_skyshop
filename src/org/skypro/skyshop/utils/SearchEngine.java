package org.skypro.skyshop.utils;

import org.skypro.skyshop.exceptions.BestResultNotFound;

import java.util.*;

public class SearchEngine {
    private Set<Searchable> contents;

    public SearchEngine(int size) {
        this.contents = new HashSet<>();
    }

    public Set<Searchable> search(String term) {
        TreeSet<Searchable> foundContents = new TreeSet<>(new SearchComparator());
        for (final Searchable content : contents) {
            if (content.getSearchTerm().contains(term)) foundContents.add(content);
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
        contents.add(object);
    }

    @Override
    public String toString() {
        StringBuilder printBasket = new StringBuilder();
        for (final Searchable searchable : contents) {
            if (searchable != null)
                printBasket.append(searchable.getStringRepresentation()).append('\n');
        }
        printBasket.deleteCharAt(printBasket.length() - 1);
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
        return Objects.hash(contents);
    }
}
