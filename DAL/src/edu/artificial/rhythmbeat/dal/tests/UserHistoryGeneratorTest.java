package edu.artificial.rhythmbeat.dal.tests;

import de.umass.lastfm.Track;
import edu.artificial.rhythmbeat.access.UserCollector;
import edu.artificial.rhythmbeat.dal.UserHistoryGenerator;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by Mario on 1/5/2015.
 */
public class UserHistoryGeneratorTest {
    @Test
    public void testDataGeneration() {
        UserHistoryGenerator generator = new UserHistoryGenerator();
        generator.collectFullTrackHistory();
    }

    //  @Test
    public void testMainUserTrackRecord() {
        UserHistoryGenerator generator = new UserHistoryGenerator();
        UserCollector userCollector = new UserCollector();
        Collection<Track> listenedTracks = userCollector.getTrackHistory("RJ");
        for (Track track : listenedTracks) {
            System.out.println(track.getPlaycount());
            if (track.getPlaycount() >= 1) {
                generator.saveTrackHistory("1", track);
            }
        }
    }
}
