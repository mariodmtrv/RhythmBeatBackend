package edu.artificial.rhythmbeat.recommend;

/**
 * Created by Mario on 1/4/2015.
 */
public class MatrixInverter {
    /**
     * Inverts a square matrix
     */
    public static boolean invertMatrix(double[][] matrix) {

        int size = matrix.length;
        double[] t = new double[size];
        double[] q = new double[size];
        double[] r = new double[size];
        double ab;
        int k, l, m;

        // Invert a symetric matrix in leastSquaresVarCovar
        for (m = 0; m < size; m++) {
            r[m] = 1;
        }
        k = 0;
        for (m = 0; m < size; m++) {
            double Big = 0;
            for (l = 0; l < size; l++) {
                ab = Math.abs(matrix[l][l]);
                if ((ab > Big) && (r[l] != 0)) {
                    Big = ab;
                    k = l;
                }
            }
            if (Big == 0) {
                return false;
            }
            r[k] = 0;
            q[k] = 1 / matrix[k][k];
            t[k] = 1;
            matrix[k][k] = 0;
            if (k != 0) {
                for (l = 0; l < k; l++) {
                    t[l] = matrix[l][k];
                    if (r[l] == 0) {
                        q[l] = matrix[l][k] * q[k];
                    } else {
                        q[l] = -matrix[l][k] * q[k];
                    }
                    matrix[l][k] = 0;
                }
            }
            if ((k + 1) < size) {
                for (l = k + 1; l < size; l++) {
                    if (r[l] != 0) {
                        t[l] = matrix[k][l];
                    } else {
                        t[l] = -matrix[k][l];
                    }
                    q[l] = -matrix[k][l] * q[k];
                    matrix[k][l] = 0;
                }
            }
            for (l = 0; l < size; l++) {
                for (k = l; k < size; k++) {
                    matrix[l][k] = matrix[l][k] + t[l] * q[k];
                }
            }
        }
        m = size;
        l = size - 1;
        for (k = 1; k < size; k++) {
            m = m - 1;
            l = l - 1;
            for (int j = 0; j <= l; j++) {
                matrix[m][j] = matrix[j][m];
            }
        }
        return true;
    }
}
