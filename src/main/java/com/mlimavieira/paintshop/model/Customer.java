package com.mlimavieira.paintshop.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final List<Paint> listOfPaints = new ArrayList<>();

    public void addPaint(Integer batch, Color color) {
        listOfPaints.add(new Paint(batch, color));
    }

    public Integer getNumOfPaints() {
        return listOfPaints.size();
    }

    public List<Paint> getListOfPaints() {
        return listOfPaints;
    }

}
