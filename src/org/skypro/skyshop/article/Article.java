package org.skypro.skyshop.article;

import org.skypro.skyshop.utils.Searchable;

import java.util.Objects;

public final class Article implements Searchable {
    private final String headline;
    private final String text;

    public Article(String headline, String text) {
        this.headline = headline;
        this.text = text;
    }

    public String getHeadline() {
        return headline;
    }

    public String getText() {
        return text;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String getNameObject() {
        return headline;
    }

    @Override
    public String toString() {
        return String.format("%s - %s",
                headline, text);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(headline, article.headline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(headline);
    }
}
