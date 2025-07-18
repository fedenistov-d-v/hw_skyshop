package org.skypro.skyshop.exceptions;

import java.io.IOException;

public class BestResultNotFound extends Exception {
    private String message;

    public BestResultNotFound(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BestResultNotFound{" + '\"' +
                message + '\"' + " - таких сочетаний не нашлось." +
                '}';
    }
}
