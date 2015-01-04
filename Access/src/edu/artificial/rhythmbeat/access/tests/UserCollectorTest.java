package edu.artificial.rhythmbeat.access.tests;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import edu.artificial.rhythmbeat.data.Configuration;
import edu.artificial.rhythmbeat.data.UserCollector;
import org.junit.Test;

import java.util.List;

/**
 * Created by Mario on 1/3/2015.
 */
public class UserCollectorTest {
    //@Test
    public void testUserCollection() {
        UserCollector collector = new UserCollector();
        List<User> users = collector.getRequiredUsers();
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

    @Test
    public void testUserTracks() {
        UserCollector collector = new UserCollector();
        PaginatedResult<Track> tracks = collector.getTrackHistory("RJ");
        for (Track track : tracks) {

            System.out.println(track.getName() + track.getPlaycount());

        }
    }
}
