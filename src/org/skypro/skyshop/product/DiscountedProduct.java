package org.skypro.skyshop.product;

import java.util.Objects;

public class DiscountedProduct extends Product {

    protected final int price;
    protected final int discountInPercent;

    public DiscountedProduct(String name, int price, int discountInPercent) {
        super(name);
        this.price = price;
        this.discountInPercent = discountInPercent;
    }

    @Override
    public int getPrice() {
        return (int)(price * (1.0 - discountInPercent / 100.0));
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s: %d (%d%%)%n",
                name, getPrice(), discountInPercent);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountedProduct that = (DiscountedProduct) o;
        return name.equals(that.name) &&
                price == that.price &&
                discountInPercent == that.discountInPercent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), price, discountInPercent);
    }
}
