package org.example;

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

    // Standard identity matrix 4x4
    public Matrix(boolean identityMatrix) {
        this.dimension = 4;
        this.data = new double[dimension][dimension];
        if (identityMatrix) setIdentityMatrix();
    }

    // (n x n) - Matrix
    public Matrix(int dimension) {
        this.dimension = dimension;
        this.data = new double[dimension][dimension];
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
        if (identityMatrix) setIdentityMatrix();
    }

    /*--------------------------//calculation functions//-------------------------------*/

    /**
     * @param s a double value
     * @return a multiplied matrix with a given value
     */
    public Matrix mult(double s) {
        Matrix result = new Matrix(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                result.data[i][j] = this.data[i][j] * s;
            }
        } return result;
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
        } return result;
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
    public Matrix trans() {
        Matrix result = new Matrix(this.dimension);
        for (int i = 0; i < this.dimension; i++) {
            for (int j = 0; j < this.dimension; j++) {
                result.data[i][j] = this.data[j][i];
            }
        }
        return result;
    }

    /**
     * @param m m-th row to discard in the new matrix
     * @param n n-th column to discard
     * @return submatrix of this
     * the submatrix is a new matrix with n - 1 dimension,
     * made by removing m,n row/col from this
     */
    public Matrix subMatrix(int m, int n) {
        Matrix subM = new Matrix(this.dimension - 1);
        int subI = 0;
        int subJ;
        for (int i = 0; i < this.dimension; i++) {
            // Skip the m-th row
            if (i == m) continue;
            subJ = 0;
            for (int j = 0; j < this.dimension; j++) {
                // Skip the n-th column
                if (j != n) {
                    subM.data[subI][subJ] = this.data[i][j];
                    subJ++;
                }
            } subI++;
        } return subM;
    }

    /**
     * @param i i-th row of the Matrix
     * @param j j-th col of the Matrix
     * @return the cofactor from a matrix's minor
     */
    public double cofactor(int i, int j) {
        return Math.pow(-1, i + j) * minor(i, j);
    }

    /**
     * @param i i-th row of the matrix
     * @param j j-th column of the matrix
     * @return the "sub-determinant" coming from a submatrix
     */
    public double minor(int i, int j) {
        return subMatrix(i, j).det();
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
            return data[0][0] * data[1][1] -
                    data[0][1] * data[1][0];
        }
        double determinant = 0;
        for (int j = 0; j < dimension; j++) {
            determinant += data[0][j] * cofactor(0, j);
        }
        return determinant;
    }

    /**
     * @return the adjunct of a matrix
     * adj = transposed cofactor-matrix
     */
    public Matrix adj() {
        Matrix result = new Matrix(dimension);

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                result.data[i][j] = cofactor(i, j);
            }
        }
        return result.trans();
    }

    /**
     * @return inverted matrix
     */
    public Matrix inv() {
        if (Math.abs(det()) < 1e-10) throw new RuntimeException("The determinant of the matrix is close to 0");
        // lazy evaluation
        if (this.inverse == null) {
            double d = 1 / det();
            inverse = adj().mult(d).data;
        }
        return new Matrix(this.inverse);
    }

    /*------------------------------//structure methods//---------------------------------------*/

    private void setIdentityMatrix() {
        for (int i = 0; i < dimension; i++) {
            data[i][i] = 1;
        }
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
        for (int i = 0; i < ((Matrix) obj).dimension; i++) {
            for (int j = 0; j < ((Matrix) obj).dimension; j++) {
                if (Math.abs(((Matrix) obj).get(i, j) - get(i, j)) > 1.0E-5) {
                    return false;
                }
            }
        } return true;
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
