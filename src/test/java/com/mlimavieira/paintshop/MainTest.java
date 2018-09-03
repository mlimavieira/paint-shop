package com.mlimavieira.paintshop;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class MainTest extends BaseTest {

    @Test
    public void testValidOutput() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File file = getFile("test_5colors_3customers");
        String[] args = {file.getAbsolutePath()};
        Main.main(args);

        String output = outContent.toString();

        assertTrue(output.contains("G G G G M"));
    }

    @Test
    public void noSolutionFound() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        File file = getFile("invalid_1color_2customers");
        String[] args = {file.getAbsolutePath()};
        Main.main(args);

        String output = outContent.toString();

        assertTrue(output.contains("No solution exists"));
    }

    @Test
    public void testInvalidInput() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        String[] args = {};
        Main.main(args);

        String output = outContent.toString();

        assertTrue(output.contains("Invalid arguments. Provide the input path"));
    }

    @Test
    public void testInvalidFileInput() {

        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outContent));

        String[] args = {""};
        Main.main(args);

        String output = outContent.toString();

        assertTrue(output.contains("File is required."));
    }

}
