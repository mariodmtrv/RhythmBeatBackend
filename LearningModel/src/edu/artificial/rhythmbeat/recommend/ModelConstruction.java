package edu.artificial.rhythmbeat.recommend;

import edu.artificial.rhythmbeat.dal.ModelDataExtraction;

import java.util.*;

/**
 * Created by Mario on 1/5/2015.
 */
public class ModelConstruction {
    public double trainModel(List<ListenFeatureVector> userListeningHistory) {
        int examplesCount = userListeningHistory.size();
        int featuresCount = ListenFeatureVector.FEATURES_COUNT;

        int index = 0;

        double[] dependentVariables = new double[examplesCount];
        double[][] independentVariables = new double[examplesCount][featuresCount];
        for (ListenFeatureVector historyItem : userListeningHistory) {

            independentVariables[index] = historyItem.getIndependentVariables();
            dependentVariables[index++] = historyItem.getDependentVariable();
        }
        SparsityDecreasedMatrix resultMatrix = removeMeaninglessVariables(independentVariables);
        independentVariables = resultMatrix.matrix;
        MultipleLinearRegression regression;
        try {
            regression = new MultipleLinearRegression(independentVariables, dependentVariables);
        } catch (Exception ex) {
            return 0.0;
        }
 /*    for (index = 0; index < featuresCount; index++) {
            System.out.print(regression.beta(index) + "    ");
        }*/
        return regression.R2();
    }

    public static void main(String[] args) {
           }

    private SparsityDecreasedMatrix removeMeaninglessVariables(double[][] matrix) {
        double[][] result = null;
        SparsityDecreasedMatrix resultMatrix;
        double EPS = 0.000001;
        List<Integer> toRemove = new LinkedList<>();
        for (int colIndex = 0; colIndex < matrix[0].length; colIndex++) {
            boolean shouldRemove = true;
            for (int rowIndex = 0; rowIndex < matrix.length; rowIndex++) {
                if (!(-EPS < matrix[rowIndex][colIndex] && matrix[rowIndex][colIndex] < EPS)) {
                    shouldRemove = false;
                    break;
                }
            }
            if (shouldRemove) {
                toRemove.add(colIndex);
            }
        }
        if (toRemove.size() > 0) {
            result = new double[matrix.length][matrix[0].length - toRemove.size()];
            for (int exampleIndex = 0; exampleIndex < matrix.length; exampleIndex++) {
                Iterator<Integer> iter = toRemove.iterator();

                int toRemoveColumn = iter.next();
                int count = 0;
                for (int colIndex = 0; colIndex < matrix[0].length; colIndex++) {
                    if (toRemoveColumn == colIndex) {
                        if (iter.hasNext()) {
                            toRemoveColumn = iter.next();
                            count++;
                        } else {
                            break;
                        }
                        continue;
                    }
                    result[exampleIndex][colIndex - count] = matrix[exampleIndex][colIndex];
                }
            }
            resultMatrix = new SparsityDecreasedMatrix(result, toRemove);
            return resultMatrix;
        }
        // otherwise no processing done
        resultMatrix = new SparsityDecreasedMatrix(matrix, toRemove);
        return resultMatrix;
    }

    class SparsityDecreasedMatrix {
        double[][] matrix;
        List<Integer> removed;

        public SparsityDecreasedMatrix(double[][] matrix, List<Integer> removed) {
            this.matrix = matrix;
            this.removed = removed;
        }
    }
    class ModelResult {

    }
}
