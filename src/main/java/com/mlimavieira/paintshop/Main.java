package com.mlimavieira.paintshop;

import com.mlimavieira.paintshop.exception.NoSolutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        LOGGER.info("Starting application");
        if (args.length != 1) {
            LOGGER.error("Invalid Arguments");
            System.err.println("Invalid arguments. Provide the input path ");
            return;
        }

        File file = new File(args[0]);

        PaintShop paintShop = new PaintShop();
        try {
            String output = paintShop.mixColors(file);

            System.out.println(output);

        } catch (IllegalArgumentException e) {
            LOGGER.error("Illegal argument. {}", e.getMessage());
            System.err.println(e.getMessage());
        } catch (NoSolutionException e) {
            LOGGER.info(e.getMessage());
            System.out.println("No solution exists");
        } catch (IOException e) {
            LOGGER.error("Something went wrong", e);
            System.err.println("Something went wrong");
        }

    }

}
