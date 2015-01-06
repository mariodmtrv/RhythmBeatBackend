package edu.artificial.rhythmbeat.recommend;

import edu.artificial.rhythmbeat.dal.Artist;
import edu.artificial.rhythmbeat.dal.Track;
import edu.artificial.rhythmbeat.dal.UserTrack;

/**
 * Created by Mario on 1/5/2015.
 */
public class ListenFeatureVector extends TrackFeatureVector {

    private UserTrack userTrack;

    public ListenFeatureVector(Track track, Artist artist, UserTrack userTrack) {
        super(track, artist);
        this.userTrack = userTrack;
    }


    public double getDependentVariable() {
        return userTrack.getPlaysCount();
    }

}
