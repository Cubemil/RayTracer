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
        this.dimension = data.length;
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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix other) ||
                this.dimension != other.dimension ||
                this.data.length != other.data.length ||
                this.data[0].length != other.data[0].length) {
            return false;
        }
        double epsilon = 0.00001;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (!(Math.abs(this.get(i, j) - other.get(i, j)) < epsilon)) {
                    return false;
                }
            }
        }
        return true;
    }
}
