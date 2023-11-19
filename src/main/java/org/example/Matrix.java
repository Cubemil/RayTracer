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

    // identity matrix
    public Matrix(int dimension, boolean identityMatrix) {
        this.dimension = dimension;
        this.data = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            this.data[i][i] = 1;
        }
    }

    /**
     * @param other matrix to be multiplied with
     * @return C = A * B
     * i-th row of A gets multiplied with k-th column of B
     * -> and then all are added up to be C(i,k)
     * */
    public Matrix mult(Matrix other) {
        if (this.dimension != other.dimension) {
            throw new RuntimeException("The matrices sizes do not match");
        }
        Matrix result = new Matrix(this.dimension);
        for (int i = 0; i < result.dimension; i++) {
            for (int k = 0; k < result.dimension; k++) {
                double curr = 0.0;
                for (int j = 0; j < this.dimension; j++) {
                    curr += this.get(i, j) * other.get(j, k);
                }
                result.data[i][k] = curr;
            }
        }
        return result;
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

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                str += this.data[i][j] + ", ";
            }
            str += "\n";
        }
        return str;
    }
}
