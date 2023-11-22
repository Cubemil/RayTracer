package org.example;

import java.util.stream.IntStream;

public class Matrix {

    /*----------------------------------//fields//---------------------------------------*/

    private final int dimension;
    private final double[][] data;
    private double[][] inverse;

    /*-----------------------------//constructors//---------------------------------------*/

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

    // Pre-filled matrix without dimension
    public Matrix(double[][] data) {
        if (data.length != data[0].length)
            throw new RuntimeException("This Matrix's dimensions do not match");

        this.dimension = data.length;
        this.data = new double[dimension][dimension];

        IntStream.range(0, dimension).forEach(
                row -> System.arraycopy(data[row], 0, this.data[row], 0, dimension));
    }

    /*--------------------------//calculation functions//-------------------------------*/

    /**
     * @param scalar a double value
     * @return a multiplied matrix with a given value
     */
    public Matrix mult(double scalar) {
        Matrix result = new Matrix(this.dimension);
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                result.data[row][col] = this.data[row][col] * scalar;
            }
        } return result;
    }

    /**
     * @param other matrix to be multiplied with
     * @return result = this * other
     * row-th row of A gets multiplied with otherCol-th column of B
     * -> and then all are added up to be C(row, otherCol)
     * */
    public Matrix mult(Matrix other) {
        if (this.dimension != other.dimension) {
            throw new IllegalArgumentException("The matrices sizes do not match");
        }
        Matrix result = new Matrix(this.dimension);
        for (int row = 0; row < result.dimension; row++) {
            for (int col = 0; col < result.dimension; col++) {
                double curr = 0.0;
                for (int otherCol = 0; otherCol < this.dimension; otherCol++) {
                    curr += this.get(row, otherCol) * other.get(otherCol, col);
                }
                result.data[row][col] = curr;
            }
        } return result;
    }

    /**
     * @param point to be multiplied with
     * @return new Point = point as a column * matrix (from the right side)
     */
    public Point mult(Point point) {
        if (this.dimension != 4)
            throw new IllegalArgumentException("When multiplying a matrix with a point, both need to be 4-dimensional");

        double[] result = new double[4];
        double[] given = new double[]{
                point.x(),
                point.y(),
                point.z(),
                point.w()};

        for (int row = 0; row < 4; row++) {
            double curr = 0.0;
            for (int col = 0; col < 4; col++) {
                curr += this.data[row][col] * given[col];
            }
            result[row] = curr;
        }
        return new Point(result[0], result[1], result[2], result[3]);
    }

    /**
     * @param vector to be multiplied with
     * @return new Vector = vector as a column * matrix (from the right side)
     */
    public Vector mult(Vector vector) {
        if (this.dimension != 4)
            throw new IllegalArgumentException("When multiplying a matrix with a vector, both need to be 4-dimensional");

        double[] result = new double[4];
        double[] given = new double[]{
                vector.x(),
                vector.y(),
                vector.z(),
                vector.w()};

        for (int row = 0; row < 4; row++) {
            double curr = 0.0;
            for (int col = 0; col < 4; col++) {
                curr += this.data[row][col] * given[col];
            }
            result[row] = curr;
        }
        return new Vector(result[0], result[1], result[2], result[3]);
    }

    /**
     * @return a transposed matrix from the current one
     * m X n = A(row, col) -> n X m = AT(col, row)
     */
    public Matrix trans() {
        Matrix result = new Matrix(this.dimension);
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                result.data[row][col] = this.data[col][row];
            }
        }
        return result;
    }

    /**
     * @param skipRow skipRow-th row to discard in the new matrix
     * @param skipCol skipCol-th column to discard
     * @return submatrix of this
     * the submatrix is a new matrix with skipCol - 1 dimension,
     * made by removing skipRow,skipCol row/col from this
     */
    public Matrix subMatrix(int skipRow, int skipCol) {
        Matrix subMatrix = new Matrix(this.dimension - 1);
        int subRow = 0;
        int subCol;
        for (int row = 0; row < this.dimension; row++) {
            // Skip the skipRow-th row
            if (row == skipRow) continue;
            subCol = 0;
            for (int col = 0; col < this.dimension; col++) {
                // Skip the skipCol-th column
                if (col != skipCol) {
                    subMatrix.data[subRow][subCol] = this.data[row][col];
                    subCol++;
                }
            } subRow++;
        } return subMatrix;
    }

    /**
     * @param row row-th row of the Matrix
     * @param col col-th col of the Matrix
     * @return the cofactor from a matrix's minor
     */
    public double cofactor(int row, int col) {
        return Math.pow(-1, row + col) * minor(row, col);
    }

    /**
     * @param row row-th row of the matrix
     * @param col col-th column of the matrix
     * @return the "sub-determinant" coming from a submatrix
     */
    public double minor(int row, int col) {
        return subMatrix(row, col).det();
    }

    /**
     * @return the determinant of a matrix
     * steps:
     * 1. default-case from a 2x2 matrix
     * 2. getting the submatrix
     * 3. getting the minor from the submatrix (recursive)
     * 4. getting the cofactor from the previous data (recursive)
     * 5. return sum of all the products
     */
    public double det() {
        //Basisfall: det von 2x2-Matrix
        if (this.dimension == 2) {
            return  data[0][0] * data[1][1] -
                    data[0][1] * data[1][0];
        }
        double determinant = 0;
        for (int col = 0; col < dimension; col++) {
            determinant += data[0][col] * cofactor(0, col);
        }
        return determinant;
    }

    /**
     * @return the adjunct of a matrix
     * adj = transposed cofactor-matrix
     */
    public Matrix adj() {
        Matrix result = new Matrix(dimension);
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                result.data[row][col] = cofactor(row, col);
            }
        }
        return result.trans();
    }

    /**
     * @return inverted matrix
     */
    public Matrix inv() {
        if (Math.abs(det()) < 1e-10) throw new ArithmeticException("The determinant of the matrix is close to 0");
        // lazy evaluation
        if (this.inverse == null) {
            double d = 1 / det();
            inverse = adj().mult(d).data;
        }
        return new Matrix(this.inverse);
    }

    /*------------------------------//structure methods//---------------------------------------*/

    /**
     * @param matrix to be made into an identity matrix
     * @return an identity matrix of a given method
     * the data array is cleared and filled along the main diagonal with ones
     */
    public static Matrix identityMatrix(Matrix matrix) {
        for (int row = 0; row < matrix.dimension; row++) {
            for (int col = 0; col < matrix.dimension; col++) {
                matrix.data[row][col] = 0;
            }
            matrix.data[row][row] = 1;
        }
        return matrix;
    }

    public double get(int row, int col) {
        return this.data[row][col];
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Matrix other) ||
                this.dimension != other.dimension ||
                this.data.length != other.data.length ||
                this.data[0].length != other.data[0].length) {
            return false;
        }
        for (int row = 0; row < ((Matrix) obj).dimension; row++) {
            for (int col = 0; col < ((Matrix) obj).dimension; col++) {
                if (Math.abs(((Matrix) obj).get(row, col) - get(row, col)) > 1.0E-5)
                    return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                str.append(this.data[row][col]).append(", ");
            }
            str.append("\n");
        }
        return str.toString();
    }

}