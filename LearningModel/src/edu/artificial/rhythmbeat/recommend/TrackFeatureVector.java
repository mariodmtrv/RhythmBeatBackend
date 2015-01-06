package edu.artificial.rhythmbeat.recommend;

import edu.artificial.rhythmbeat.dal.Artist;
import edu.artificial.rhythmbeat.dal.Track;

/**
 * Created by Mario on 1/5/2015.
 */
public class TrackFeatureVector {
    private Track track;
    private Artist artist;
    private double[] featureVector = null;
    public static final int TRACK_FEATURES_COUNT = 6;
    public static final int ARTIST_FEATURES_COUNT = 22;
    public static final int FEATURES_COUNT = TRACK_FEATURES_COUNT + ARTIST_FEATURES_COUNT;


    public TrackFeatureVector(Track track, Artist artist) {
        this.track = track;
        this.artist = artist;

    }

    public double[] getIndependentVariables() {
        if (this.featureVector == null) {
            this.featureVector = new double[FEATURES_COUNT];
            if (track.getTempo() == null) {
                return featureVector;
            }
            generateTrackVector();
            generateArtistVector();
        }
        return featureVector;

    }

    private void generateTrackVector() {
        //tempo

        featureVector[0] = track.getTempo();
        //mode
        featureVector[1] = track.getMode();
        // danceability
        featureVector[2] = track.getDanceability();
        //energy
        featureVector[3] = track.getEnergy();
        // loudness
        featureVector[4] = track.getLoudness();
        //hotttness
        featureVector[5] = track.getHotttness();
    }

    private void generateArtistVector() {
        String data = artist.getTagVector();
        int index = TRACK_FEATURES_COUNT;
        // index = 0;
        featureVector[index++] = artist.getListeners();
        featureVector[index++] = artist.getPlays();
        for (char value : data.toCharArray()) {
            if (index >= FEATURES_COUNT) {
                break;
            }
            featureVector[index++] = (value == '1' ? 1 : 0);
        }

    }
}
