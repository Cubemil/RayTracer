package org.example;

public class Matrix {

    private int dimension;
    private double[][] data;

    // Standard-Matrix (4 x 4)
    public Matrix() {
        this.dimension = 4;
        this.data = new double[dimension][dimension];
    }

    // (n x n) - Matrix
    public Matrix(int dimension) {
        this.dimension = dimension;
        this.data = new double[dimension][dimension];
    }

    // Pre-filled matrix with dimension
    public Matrix(int dimension, double[][] data) {
        this.dimension = dimension;
        this.data = data;
        // checks if dimension matches each row/col of the passed array
        if (dimension != data[0].length || dimension != data.length) {
            throw new RuntimeException("Incorrectly sized data for this matrix's dimensions");
        }
    }

    // Pre-filled matrix without dimension
    public Matrix(double[][] data) {
        this.data = data;
        // checks if number of cols and rows match
        if (data.length != data[0].length) {
            throw new RuntimeException("This Matrix's dimensions do not match");
        }
    }

    public double get(int row, int col) {
        return this.data[row][col];
    }

    public void set(int row, int col, double value) {
        this.data[row][col] = value;
    }


}
