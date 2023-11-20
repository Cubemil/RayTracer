package org.example;

public class Matrix {

    /*----------------------------------fields---------------------------------------*/

    private final int dimension;
    private final double[][] data;

    /*-----------------------------constructors---------------------------------------*/

    // Standard-Matrix (4 x 4)
    public Matrix() {
        this.dimension = 4;
        this.data = new double[dimension][dimension];
    }

    // Standard identity matrix 4x4
    public Matrix(boolean identityMatrix) {
        this.dimension = 4;
        this.data = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            this.data[i][i] = 1;
        }
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

    /*--------------------------calculation functions-------------------------------*/

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

    /**
     * @param point to be multiplied with
     * @return new Point = point as a column * matrix (from the right side)
     */
    public Point mult(Point point) {
        if (this.dimension != 4)
            throw new RuntimeException("When multiplying a matrix with a point, both need to be 4-dimensional");

        double[] result = new double[4];
        double[] given = new double[]{
                point.x(),
                point.y(),
                point.z(),
                point.w()};

        for (int i = 0; i < 4; i++) {
            double curr = 0.0;
            for (int j = 0; j < 4; j++) {
                curr += this.data[i][j] * given[j];
            }
            result[i] = curr;
        }

        return new Point(result[0], result[1], result[2], result[3]);
    }

    /**
     * @param vector to be multiplied with
     * @return new Vector = vector as a column * matrix (from the right side)
     */
    public Vector mult(Vector vector) {
        if (this.dimension != 4)
            throw new RuntimeException("When multiplying a matrix with a vector, both need to be 4-dimensional");

        double[] result = new double[4];
        double[] given = new double[]{
                vector.x(),
                vector.y(),
                vector.z(),
                vector.w()};

        for (int i = 0; i < 4; i++) {
            double curr = 0.0;
            for (int j = 0; j < 4; j++) {
                curr += this.data[i][j] * given[j];
            }
            result[i] = curr;
        }

        return new Vector(result[0], result[1], result[2], result[3]);
    }

    /**
     * @return a transposed matrix from the current one
     * m X n = A(ij) -> n X m = AT(ji)
     */
    public Matrix transpose() {
        Matrix result = new Matrix(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }

    /*------------------------------structure methods---------------------------------------*/

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
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                str.append(this.data[i][j]).append(", ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}
