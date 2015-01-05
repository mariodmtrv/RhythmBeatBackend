package edu.artificial.rhythmbeat.access.tests;

import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Song;
import edu.artificial.rhythmbeat.access.MetadataCollector;
import org.junit.Test;

/**
 * Created by Mario on 1/5/2015.
 */
public class TrackDataTest {
    @Test
    public void collectTrackDataTest() {
        MetadataCollector collector = new MetadataCollector();
        Song topSong = collector.getTrack("Waka waka", "Shakira");
        try {

            topSong.getLoudness();
            topSong.getTempo();
            topSong.getMode();
            topSong.getSongHotttnesss();
            topSong.getDanceability();
            System.out.println("energy" + topSong.getEnergy() + " loudness" + topSong.getLoudness() + " tempo" + topSong.getTempo() + " mode" + topSong.getMode() + " hotttness" + topSong.getSongHotttnesss() + " danceability" + topSong.getDanceability());
        } catch (EchoNestException e) {
            e.printStackTrace();
        }

    }
}
