package org.skypro.skyshop.utils;

public interface Searchable {

    String getSearchTerm();

    String getTypeContent();

    String getNameObject();

    default String getStringRepresentation() {
        return String.format("%s - %s",
                getNameObject(),
                getTypeContent());
    }
}
