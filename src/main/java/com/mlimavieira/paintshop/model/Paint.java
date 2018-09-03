package com.mlimavieira.paintshop.model;

public class Paint {

    private final int batch;
    private final Color color;

    public Paint(int batch, Color color) {
        super();
        this.batch = batch;
        this.color = color;
    }

    public int getBatch() {
        return batch;
    }

    public Color getColor() {
        return color;
    }
}
