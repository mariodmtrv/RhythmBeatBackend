package edu.artificial.rhythmbeat.recommend;

/**
 * Created by Mario on 1/4/2015.
 */
public class LinearRegression {
    double[][] leastSquaresVarCovar;            // Least squares and var/covar matrix
    public double[] coefficients;      // Coefficients
    public double[] coefficientsStandardError;    // Std Error of coefficients
    double multipleCorrelationCoefficient;            // Multiple correlation coefficient
    double standardErrorDeviation;             // Standard deviation of errors
    public double FReg;            // Fisher F statistic for regression
    double[] yCalculated;         // Calculated values of Y
    double[] yResidual;            // Residual values of Y

    public double[][] getLeastSquaresVarCovar() {
        return leastSquaresVarCovar;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public double[] getCoefficientsStandardError() {
        return coefficientsStandardError;
    }

    public double getMultipleCorrelationCoefficient() {
        return multipleCorrelationCoefficient;
    }

    public double getStandardErrorDeviation() {
        return standardErrorDeviation;
    }

    public double getFReg() {
        return FReg;
    }

    public double[] getyCalculated() {
        return yCalculated;
    }

    public double[] getyResidual() {
        return yResidual;
    }

    public boolean Regress(double[] dataPoints, double[][] independentVariables, double[] weightValues) {
        int dataPointsCount = dataPoints.length;
        int linearTermsCount = independentVariables[0].length;
        int degreeOfFreedom = dataPointsCount - linearTermsCount;
        yCalculated = new double[dataPointsCount];
        yResidual = new double[dataPointsCount];
        // If not enough data, don't attempt regression
        if (degreeOfFreedom < 1) {
            return false;
        }
        leastSquaresVarCovar = new double[linearTermsCount][linearTermsCount];
        coefficients = new double[linearTermsCount];
        coefficientsStandardError = new double[linearTermsCount];
        double[] leastSquaresVector = new double[linearTermsCount];   // Vector for LSQ

        // clear the matrices to start out
        for (int i = 0; i < linearTermsCount; i++)
            for (int j = 0; j < linearTermsCount; j++)
                leastSquaresVarCovar[i][j] = 0;

        // form Least Squares Matrix
        for (int i = 0; i < linearTermsCount; i++) {
            for (int j = 0; j < linearTermsCount; j++) {
                leastSquaresVarCovar[i][j] = 0;
                for (int k = 0; k < dataPointsCount; k++)
                    leastSquaresVarCovar[i][j] = leastSquaresVarCovar[i][j] + weightValues[k] * independentVariables[i][k] * independentVariables[j][k];
            }
            leastSquaresVector[i] = 0;
            for (int k = 0; k < dataPointsCount; k++)
                leastSquaresVector[i] = leastSquaresVector[i] + weightValues[k] * independentVariables[i][k] * dataPoints[k];
        }
        // leastSquaresVarCovar now contains the raw least squares matrix
        if (!MatrixInverter.invertMatrix(leastSquaresVarCovar)) {
            return false;
        }
        // leastSquaresVarCovar now contains the inverted least square matrix
        // Matrix multpily to get coefficients coefficients = VB
        for (int i = 0; i < linearTermsCount; i++) {
            coefficients[i] = 0;
            for (int j = 0; j < linearTermsCount; j++)
                coefficients[i] = coefficients[i] + leastSquaresVarCovar[i][j] * leastSquaresVector[j];
        }

        // Calculate statistics
        double TSS = 0;
        double RSS = 0;
        double YBAR = 0;
        double WSUM = 0;
        for (int k = 0; k < dataPointsCount; k++) {
            YBAR = YBAR + weightValues[k] * dataPoints[k];
            WSUM = WSUM + weightValues[k];
        }
        YBAR = YBAR / WSUM;
        for (int k = 0; k < dataPointsCount; k++) {
            yCalculated[k] = 0;
            for (int i = 0; i < linearTermsCount; i++)
                yCalculated[k] = yCalculated[k] + coefficients[i] * independentVariables[i][k];
            yResidual[k] = yCalculated[k] - dataPoints[k];
            TSS = TSS + weightValues[k] * (dataPoints[k] - YBAR) * (dataPoints[k] - YBAR);
            RSS = RSS + weightValues[k] * yResidual[k] * yResidual[k];
        }
        double SSQ = RSS / degreeOfFreedom;
        multipleCorrelationCoefficient = 1 - RSS / TSS;
        FReg = 9999999;
        if (multipleCorrelationCoefficient < 0.9999999)
            FReg = multipleCorrelationCoefficient / (1 - multipleCorrelationCoefficient) * degreeOfFreedom / (linearTermsCount - 1);
        standardErrorDeviation = Math.sqrt(SSQ);

        // Calculate var-covar matrix and std error of coefficients
        for (int i = 0; i < linearTermsCount; i++) {
            for (int j = 0; j < linearTermsCount; j++)
                leastSquaresVarCovar[i][j] = leastSquaresVarCovar[i][j] * SSQ;
            coefficientsStandardError[i] = Math.sqrt(leastSquaresVarCovar[i][i]);
        }
        return true;
    }
}