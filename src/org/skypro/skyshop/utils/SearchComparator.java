package org.skypro.skyshop.utils;

import java.util.Comparator;

public class SearchComparator implements Comparator<Searchable> {
    @Override
    public int compare(Searchable o1, Searchable o2) {
        int result = Integer.compare(o2.getNameObject().length(), o1.getNameObject().length());
        if (result == 0) return o1.getNameObject().compareTo(o2.getNameObject());
        return result;
    }
}
