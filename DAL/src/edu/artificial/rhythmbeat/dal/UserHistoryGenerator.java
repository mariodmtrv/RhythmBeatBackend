package edu.artificial.rhythmbeat.dal;

import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Song;
import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import edu.artificial.rhythmbeat.access.Configuration;
import edu.artificial.rhythmbeat.access.MetadataCollector;
import edu.artificial.rhythmbeat.access.UserCollector;
import edu.artificial.rhythmbeat.dal.UserTrack;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Mario on 1/5/2015.
 */
public class UserHistoryGenerator {
    private static EntityManager entityManager = PersistenceConfiguration.getEntityManager();

    public void collectFullTrackHistory() {

        UserCollector userCollector = new UserCollector();
        List<User> users = userCollector.getRequiredUsers();
        for (User user : users) {

            try {
                String userId = user.getId();

                Collection<Track> listenedTracks = userCollector.getTrackHistory(user.getName());
                int tracksCount = listenedTracks.size();
                int fake = 0;
                for (Track track : listenedTracks) {
                    if (track != null) {
                        try {
                            saveTrackHistory(userId, track);
                        } catch (Exception ex) {
                            //   ex.printStackTrace();
                        }


                    }
                    System.out.println("USER" + user.getName() + "FAKE" + (double) fake / tracksCount + " COUNT " + tracksCount);
                }
            } catch (Exception ex) {
                // ex.printStackTrace();
            }
        }

    }

    public void saveTrackHistory(String userId, Track track) {
        try {
            String trackId = track.getMbid();
            if (trackId != null) {
                UserTrack userTrack = getUserTrack(userId, track, trackId);
                edu.artificial.rhythmbeat.dal.Track resultTrack = null;
                //track is contained
                List<edu.artificial.rhythmbeat.dal.Track> tracks = entityManager.createQuery("from Track where trackId=:trackId").setParameter("trackId", trackId).getResultList();
                if (tracks.size() == 0) {
                    String artistId = track.getArtistMbid();
                    MetadataCollector metadataCollector = new MetadataCollector();
                    resultTrack = createTrack(track, trackId, metadataCollector);
                    if (resultTrack != null) {
                        //artist is contained
                        List<edu.artificial.rhythmbeat.dal.Artist> artists = entityManager.createQuery("from Artist where artistId=:artistId").setParameter("artistId", artistId).getResultList();
                        if (artists.size() == 0) {
                            edu.artificial.rhythmbeat.dal.Artist resultArtist = getArtist(artistId, metadataCollector);
                            entityManager.getTransaction().begin();
                            entityManager.persist(resultArtist);
                            entityManager.getTransaction().commit();
                        }
                        entityManager.getTransaction().begin();
                        entityManager.persist(resultTrack);
                        entityManager.getTransaction().commit();
                    }
                }
                if (tracks.size() > 0 || resultTrack != null) {
                    entityManager.getTransaction().begin();
                    entityManager.persist(userTrack);
                    entityManager.getTransaction().commit();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private UserTrack getUserTrack(String userId, Track track, String trackId) {
        UserTrack userTrack = new UserTrack();
        userTrack.setTrackId(trackId);
        userTrack.setUserId(userId);
        userTrack.setPlaysCount(track.getUserPlaycount());

        return userTrack;
    }

    private edu.artificial.rhythmbeat.dal.Artist getArtist(String artistId, MetadataCollector metadataCollector) {
        Artist artist = Artist.getInfo(artistId, Configuration.getLastfmApiKey());
        edu.artificial.rhythmbeat.dal.Artist resultArtist = new edu.artificial.rhythmbeat.dal.Artist();
        resultArtist.setArtistId(artistId);
        resultArtist.setPlays(artist.getPlaycount());
        resultArtist.setListeners(artist.getListeners());
        resultArtist.setArtistName(artist.getName());
        String tagVector = metadataCollector.collectTagVector(artist);
        resultArtist.setTagVector(tagVector);
        return resultArtist;
    }

    private edu.artificial.rhythmbeat.dal.Track createTrack(Track track, String trackId, MetadataCollector metadataCollector) {
        edu.artificial.rhythmbeat.dal.Track resultTrack = new edu.artificial.rhythmbeat.dal.Track();

        String trackName = track.getName();
        String artistName = track.getArtist();
        String artistId = track.getArtistMbid();
        resultTrack.setTrackId(trackId);
        resultTrack.setArtistId(artistId);

        resultTrack.setName(trackName);
        Song trackMetadata = metadataCollector.getTrack(trackName, artistName);
        if (trackMetadata != null) {
            try {

                resultTrack.setEnergy(trackMetadata.getEnergy());
                resultTrack.setHotttness(trackMetadata.getSongHotttnesss());
                resultTrack.setLoudness(trackMetadata.getLoudness());
                resultTrack.setMode((double) trackMetadata.getMode());
                resultTrack.setTempo(trackMetadata.getTempo());
                double danceability = trackMetadata.getDanceability();
                resultTrack.setDanceability(danceability);
            } catch (EchoNestException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultTrack;
    }
}
