package edu.artificial.rhythmbeat.access;

import de.umass.lastfm.Track;
import de.umass.lastfm.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Mario on 1/5/2015.
 */
public class UserHistoryGenerator {

    public void collectFullTrackHistory() {
        UserCollector userCollector = new UserCollector();
        List<User> users = userCollector.getRequiredUsers();
        for (User user : users) {
            Collection<Track> listenedTracks = userCollector.getTrackHistory(user.getName());
            for (Track track : listenedTracks) {
                if (track.getPlaycount() >= 1) {

                }

            }
        }

    }

    private void saveTrackHistory() {

    }
}
