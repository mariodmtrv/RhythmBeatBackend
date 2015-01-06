package edu.artificial.rhythmbeat.recommend.tests;

import edu.artificial.rhythmbeat.recommend.LinearRegression;
import org.junit.Test;

/**
 * Created by Mario on 1/4/2015.
 */
public class LinearRegressionTest {
    @Test
    public void test() {
        LinearRegression regression = new LinearRegression();
        double[][] indep = new double[][]{{1, 1, 1}, {1, 1, 0}, {0, 0, 0}};
        double[] dep = new double[]{4, 2, 0};
        double[] imp = new double[]{1, 1, 1};
        regression.Regress(dep, indep, imp);
        for (int ind = 0; ind < 1; ind++) {
            System.out.print(regression.coefficients[ind]);
        }

    }
}
