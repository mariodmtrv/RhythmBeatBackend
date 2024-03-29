package edu.artificial.rhythmbeat.recommend; /**
 * Created by Mario on 1/4/2015.
 */

import Jama.Matrix;
import Jama.QRDecomposition;

public class MultipleLinearRegression {
    private final int N;        // number of
    private final int p;        // number of dependent variables
    private final Matrix beta;  // regression coefficients
    private double SSE;         // sum of squared
    private double SST;         // sum of squared

    public MultipleLinearRegression(double[][] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("dimensions don't agree");
        N = y.length;
        p = x[0].length;

        Matrix X = new Matrix(x);

        // create matrix from vector
        Matrix Y = new Matrix(y, N);

        // find least squares solution
        QRDecomposition qr = new QRDecomposition(X);
        beta = qr.solve(Y);


        // mean of y[] values
        double sum = 0.0;
        for (int i = 0; i < N; i++)
            sum += y[i];
        double mean = sum / N;

        // total variation to be accounted for
        for (int i = 0; i < N; i++) {
            double dev = y[i] - mean;
            SST += dev * dev;
        }

        // variation not accounted for
        Matrix residuals = X.times(beta).minus(Y);
        SSE = residuals.norm2() * residuals.norm2();

    }

    public double beta(int j) {
        return beta.get(j, 0);
    }

    public double[] getBeta() {
        double[] betaVector = new double[N];
        for (int index = 0; index < N; index++) {
            betaVector[index] = beta.get(index, 0);
        }
        return betaVector;
    }

    public double R2() {
        return 1.0 - SSE / SST;
    }

}


