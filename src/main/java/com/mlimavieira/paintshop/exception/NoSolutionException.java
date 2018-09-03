package com.mlimavieira.paintshop.exception;

public class NoSolutionException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public NoSolutionException() {
        super("No solution exists");
    }
}
