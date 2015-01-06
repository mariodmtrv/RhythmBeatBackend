package edu.artificial.rhythmbeat.recommend.tests;

import edu.artificial.rhythmbeat.recommend.MatrixInverter;
import org.junit.Test;

/**
 * Created by Mario on 1/4/2015.
 */
public class MatrixInversionTest {
    @Test
    public void testMatrixInversion() {
        double[][] matrix = new double[][]{{1, 2}, {3, 4}};
        print(matrix);
        MatrixInverter.invertMatrix(matrix);
        print(matrix);
    }

    private void print(double[][] matrix) {
        for (double[] row : matrix) {
            for (double value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
