package com.mlimavieira.paintshop.model;

import com.mlimavieira.paintshop.exception.ColorParseException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author marciovieira
 */
public enum Color {

    GLOSS("G"), MATTE("M");

    private final String abbreviation;

    Color(String abbr) {
        this.abbreviation = abbr;
    }

    public static Color getColor(String color) {

        if (StringUtils.isEmpty(color)) {
            throw new IllegalArgumentException("Color is a required argument");
        }

        if ("G".equalsIgnoreCase(color)) {
            return GLOSS;
        } else if ("M".equalsIgnoreCase(color)) {
            return MATTE;
        }

        throw new ColorParseException("Color must be G or M");

    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
