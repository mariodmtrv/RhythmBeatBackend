package edu.artificial.rhythmbeat.access.tests;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;
import edu.artificial.rhythmbeat.access.Configuration;
import edu.artificial.rhythmbeat.access.MetadataCollector;
import edu.artificial.rhythmbeat.access.UserCollector;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by Mario on 1/5/2015.
 */
public class MetadataCollectorTest {
    @Test
    public void testMetadataCollection() {
        UserCollector collector = new UserCollector();
        Collection<Track> tracks = collector.getTrackHistory("RJ");
        MetadataCollector metadataC = new MetadataCollector();
        System.out.println(tracks.size());
        for (Track track : tracks) {
            Artist artist = Artist.getInfo(track.getArtistMbid(), Configuration.getLastfmApiKey());
            String result = metadataC.collectTagVector(artist);
            System.out.println(track.getArtist() + "  " + result);
        }
    }
}
