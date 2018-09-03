package com.mlimavieira.paintshop;

import com.mlimavieira.paintshop.exception.NoSolutionException;
import com.mlimavieira.paintshop.model.Color;
import com.mlimavieira.paintshop.model.Customer;
import com.mlimavieira.paintshop.model.Order;
import com.mlimavieira.paintshop.model.Paint;
import com.mlimavieira.paintshop.parser.CustomerParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.rangeClosed;

/**
 * Class that represents the Paint Shop.
 *
 * @author marciovieira
 */
public class PaintShop {

    private static final Logger LOGGER = LogManager.getLogger(PaintShop.class);

    private final Map<Integer, Color> mapOfPaints = new HashMap<>();

    public String mixColors(File file) throws IOException {

        LOGGER.debug("Start Mixing colors");
        if (null == file) {
            LOGGER.error("File is required. ");
            throw new IllegalArgumentException("File is required.");
        }

        if (!file.exists()) {
            LOGGER.error("File does not exists.");
            throw new IllegalArgumentException("File is required.");
        }

        Order order = CustomerParser.parseCustomerOrderFromFile(file);

        order.getCustomers().sort(Comparator.comparing(Customer::getNumOfPaints));

        for (Customer customer : order.getCustomers()) {


            if (customer.getNumOfPaints() == 1) {

                Paint paint = getPaintByCustomer(customer);

                if (paint == null) {
                    throw new NoSolutionException();
                }

                mapOfPaints.put(paint.getBatch(), paint.getColor());

            } else {

                List<Paint> paintCandidates = getListOfPaintCandidates(customer);

                Paint paint = getPaintByCustomer(customer);

                if (paint != null) {

                    continue;
                } else if (paintCandidates.isEmpty()) {

                    throw new NoSolutionException();
                }

                Paint selectedPaint = paintCandidates.stream()
                        .filter(p -> p.getColor().equals(Color.GLOSS))
                        .findAny()
                        .orElse(paintCandidates.get(0));

                mapOfPaints.put(selectedPaint.getBatch(), selectedPaint.getColor());

            }
        }

        return generateOutput(mapOfPaints, order.getNumOfColors());
    }

    private List<Paint> getListOfPaintCandidates(Customer customer) {

            return customer.getListOfPaints()
                    .stream()
                    .filter(p -> mapOfPaints.get(p.getBatch()) == null)
                    .collect(Collectors.toList());

    }

    private Paint getPaintByCustomer(Customer customer) {

        for (Paint paint : customer.getListOfPaints()) {

            Color color = mapOfPaints.get(paint.getBatch());

            if (customer.getNumOfPaints() == 1) {
                if (color == null || color.equals(paint.getColor())) {

                    return paint;
                }

                return null;

            } else {

                if (color != null && color.equals(paint.getColor())) {

                    return paint;
                }

            }
        }

        return null;
    }

    private String generateOutput(Map<Integer, Color> mapOfColors, int numOfColors) {

        StringBuilder sb = new StringBuilder();
        rangeClosed(1, numOfColors).forEach(i -> {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            Color color = mapOfColors.get(i);
            sb.append(color == null ? Color.GLOSS.getAbbreviation() : color.getAbbreviation());
        });

        return sb.toString();
    }
}
