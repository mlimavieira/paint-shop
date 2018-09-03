package com.mlimavieira.paintshop;

import com.mlimavieira.paintshop.exception.ColorParseException;
import com.mlimavieira.paintshop.exception.CustomerParseException;
import com.mlimavieira.paintshop.exception.NoSolutionException;
import com.mlimavieira.paintshop.exception.NumOfColorsParseException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PaintShopTest extends BaseTest {

    @Test
    public void test5Colors3Customers() throws IOException {

        File file = getFile("test_5colors_3customers");
        String output = new PaintShop().mixColors(file);

        assertEquals("G G G G M", output);
    }

    @Test
    public void test5Colors14Customers() throws IOException {

        File file = getFile("test_5colors14customers");
        String output = new PaintShop().mixColors(file);

        assertEquals("G M G M G", output);
    }

    @Test
    public void test2Colors2Customers() throws IOException {

        File file = getFile("test_2colors_2customers");
        String output = new PaintShop().mixColors(file);

        assertEquals("M M", output);
    }

    @Test
    public void test5Colors2Customers() throws IOException {

        File file = getFile("test_5colors_2customers");
        String output = new PaintShop().mixColors(file);

        assertEquals("G G G M G", output);
    }

    @Test(expected = CustomerParseException.class)
    public void testEmptyLineInInputFile() throws IOException {

        File file = getFile("emptyLine_invalid");
        new PaintShop().mixColors(file);
    }

    @Test(expected = CustomerParseException.class)
    public void testMalformedCustomers() throws IOException {

        File file = getFile("invalid_customer");
        new PaintShop().mixColors(file);
    }

    @Test(expected = ColorParseException.class)
    public void invalidColorTest() throws IOException {

        File file = getFile("invalid_color");
        new PaintShop().mixColors(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullFileTest() throws IOException {
        new PaintShop().mixColors(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidFileTest() throws IOException {
        new PaintShop().mixColors(new File(""));
    }

    @Test(expected = NumOfColorsParseException.class)
    public void testMissingNumberOfColors() throws IOException {
        File file = getFile("missing_num_of_colors");
        new PaintShop().mixColors(file);
    }

    @Test(expected = NoSolutionException.class)
    public void test1Color2CustomersNoSolution() throws IOException {

        File file = getFile("invalid_1color_2customers");
        new PaintShop().mixColors(file);
    }
}