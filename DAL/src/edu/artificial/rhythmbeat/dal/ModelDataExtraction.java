package edu.artificial.rhythmbeat.dal;

import edu.artificial.rhythmbeat.recommend.ListenFeatureVector;
import edu.artificial.rhythmbeat.recommend.TrackFeatureVector;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mario on 1/5/2015.
 */
public class ModelDataExtraction {
    private EntityManager em = PersistenceConfiguration.getEntityManager();
    private Query userPreferences = em.createQuery("SELECT track FROM  UserTrack track WHERE  track.userId=:userId");
    private Query matchingTrack = em.createQuery("SELECT track FROM Track track where trackId=:trackId");
    private Query matchingArtist = em.createQuery("SELECT artist FROM Artist artist where artistId=:artistId");
    private Query collectTracks = em.createQuery("SELECT track FROM Track track ");

    public List<String> getUsers() {
        return em.createQuery("SELECT DISTINCT userTrack.userId FROM UserTrack userTrack ").getResultList();
    }

    /*
    *
    * @setParameter isTest true you want to collect test data false you want train data
    * */
    public List<ListenFeatureVector> getUserPreferences(String userId) {
        List<ListenFeatureVector> userPreferences = new ArrayList<>();

        Collection<UserTrack> userTrackListens = (Collection<UserTrack>) this.userPreferences.setParameter("userId", userId).getResultList();
        for (UserTrack userTrack : userTrackListens) {
            Track track = (Track) matchingTrack.setParameter("trackId", userTrack.getTrackId()).getResultList().get(0);
            Artist artist = (Artist) matchingArtist.setParameter("artistId", track.getArtistId()).getResultList().get(0);

            ListenFeatureVector userListen = new ListenFeatureVector(track, artist, userTrack);
            userPreferences.add(userListen);
        }
        return userPreferences;
    }

    public List<TrackFeatureVector> getAllTracks() {
        List<TrackFeatureVector> tracks = new ArrayList<>();
        List<Track> collectedTracks = collectTracks.getResultList();
        for (Track track : collectedTracks) {
            Artist artist = (Artist) matchingArtist.setParameter("artistId", track.getArtistId()).getResultList().get(0);
            TrackFeatureVector resultTrack = new TrackFeatureVector(track, artist);
            tracks.add(resultTrack);
        }
        return tracks;
    }
}
