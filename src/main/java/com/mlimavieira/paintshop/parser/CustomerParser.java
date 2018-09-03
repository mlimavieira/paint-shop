package com.mlimavieira.paintshop.parser;

import com.mlimavieira.paintshop.exception.CustomerParseException;
import com.mlimavieira.paintshop.exception.NumOfColorsParseException;
import com.mlimavieira.paintshop.model.Color;
import com.mlimavieira.paintshop.model.Customer;
import com.mlimavieira.paintshop.model.Order;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Utility class to parse Customer from a specific file.
 *
 * @author marciovieira
 */
public final class CustomerParser {

    private static final Logger LOGGER = LogManager.getLogger(CustomerParser.class);

    /**
     * Create an order from file
     * <p>
     * First line the number of colors
     * other lines customer info batch and color (Gloss or Matte)
     * <p>
     * File simple format
     * 2
     * 1 G 2 M
     * 1 M
     *
     * @param file in a specific format
     * @return Order
     * @throws IOException
     */
    public static Order parseCustomerOrderFromFile(File file) throws IOException {

        LOGGER.debug("Starting parse customer from file");
        Order order = new Order();

        List<String> lines = FileUtils.readLines(file, Charset.defaultCharset());

        String firstLine = lines.get(0);

        Integer numOfColors = parseNumberOfColors(firstLine);
        order.setNumOfColors(numOfColors);

        List<Customer> customers = lines.stream().skip(1)
                                        .map(line -> parseCustomer(line))
                                        .collect(Collectors.toList());

        order.getCustomers().addAll(customers);

        return order;
    }

    private static Integer parseNumberOfColors(String line) {
        LOGGER.debug("Parsing number of colors. Line: {}", line);
        try {
            int numOfColors = Integer.parseInt(line.trim());
            LOGGER.debug("NumOfColors: {} ", numOfColors);
            return numOfColors;
        } catch (NumberFormatException e) {
            LOGGER.error("Invalid number of colors. Line: {}", line);
            throw new NumOfColorsParseException("Missing number of colors");
        }
    }

    private static Customer parseCustomer(String line) {

        if (StringUtils.isEmpty(line)) {
            LOGGER.debug("Invalid customer line. Empty line is not allowed ");
            throw new CustomerParseException("Invalid customer. Empty line is not allowed");
        }

        Customer customer = new Customer();

        String[] colors = line.split(" ");
        for (int i = 0; i < colors.length; i += 2) {

            try {
                Integer batch = Integer.parseInt(colors[i]);
                Color color = Color.getColor(colors[i + 1]);
                customer.addPaint(batch, color);
            } catch (NumberFormatException e) {
                LOGGER.error("Invalid Customer. Line: {}", line, e);
                throw new CustomerParseException("Invalid Customer {} ." + colors[i] + " Line: " + line);
            }

        }

        return customer;
    }

}
