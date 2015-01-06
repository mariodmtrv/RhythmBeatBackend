package edu.artificial.rhythmbeat.recommend;

import edu.artificial.rhythmbeat.dal.ModelDataExtraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mario on 1/7/2015.
 */
public class QualityEvaluation {
    public void computeModelQuality() {
        ModelConstruction construction = new ModelConstruction();
        ModelDataExtraction extraction = new ModelDataExtraction();
        double cumulativeRSquared = 0.0;
        int usersCount = 0;
        List<String> users = extraction.getUsers();
        for (String userId : users) {
            Random r = new Random();
            List<ListenFeatureVector> listeningHistory = extraction.getUserPreferences(userId);
            List<ListenFeatureVector> trainSet = new ArrayList<>();
            List<ListenFeatureVector> testSet = new ArrayList<>();

            for (ListenFeatureVector historyIte : listeningHistory) {
                trainSet.add(historyIte);
            }
            double res = construction.trainModel(trainSet);
            if (res > 0.0) {
                usersCount++;
                cumulativeRSquared += res;
            }

        }
        System.out.println("R squared " + cumulativeRSquared / usersCount + "users" + usersCount);
    }
}
