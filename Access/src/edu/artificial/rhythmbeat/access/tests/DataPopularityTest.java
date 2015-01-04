package edu.artificial.rhythmbeat.access.tests;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import edu.artificial.rhythmbeat.access.TrackDataCollector;
import edu.artificial.rhythmbeat.access.UserCollector;
import org.junit.Test;

import java.util.*;

/**
 * Created by Mario on 1/3/2015.
 */
public class DataPopularityTest {
    // @Test
    public void testDataConvergence() {
        int tracksCount = 0;
        Map<String, Integer> popularTags = new TreeMap<>();
        Map<String, Integer> popularArtists = new TreeMap<>();
        Map<String, Integer> popularYears = new TreeMap<>();
        UserCollector collector = new UserCollector();
        List<User> users = collector.getRequiredUsers();
        for (User user : users) {
            PaginatedResult<Track> tracks = collector.getTrackHistory(user.getName());
            for (Track track : tracks) {
                tracksCount++;
                TrackDataCollector trackData = new TrackDataCollector(track);
                addEntry(popularArtists, trackData.getArtist());
                Collection<String> tags = trackData.getTags();
                String year = trackData.getYear();
                if (year != null) {
                    addEntry(popularYears, year);
                }
                for (String tag : tags) {
                    addEntry(popularTags, tag);
                }
            }
        }
        System.out.println("//////////ARTISTS//////////");
        printMostPopular(popularArtists, 10);
        System.out.println("//////////TAGS//////////");
        printMostPopular(popularTags, 5);
        System.out.println("//////////YEARS//////////");
        printMostPopular(popularYears, 5);
        System.out.println("//////////TRACKS COUNT//////////");
        System.out.println(tracksCount);
    }

    private void addEntry(Map<String, Integer> map, String entry) {
        Integer count = 1;
        if (map.containsKey(entry)) {
            count += map.get(entry);
        }
        map.put(entry, count);
    }

    private void printMostPopular(Map<String, Integer> map, int count) {
        for (String key : map.keySet()) {
            if (map.get(key) > count) {
                System.out.println(key + " " + map.get(key));
            }
        }
    }

    private void getArtistData() {
        TrackDataCollector collector = new TrackDataCollector(null);
        //     collector.getArtistData();
    }
}
