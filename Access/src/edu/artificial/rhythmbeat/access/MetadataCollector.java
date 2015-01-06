package edu.artificial.rhythmbeat.access;

import com.echonest.api.v4.*;
import de.umass.lastfm.Artist;

import java.util.*;

/**
 * Created by Mario on 1/5/2015.
 */
public class MetadataCollector {
    static List<String> topTags;
    static Map<String, Integer> topTagsPositions;
    static final int TAGS_COUNT = 50;

    static {
        topTags = new ArrayList<>(Arrays.asList(

                new String[]{"indie", "electronic", "rock", "female vocalists",
                        "singer-songwriter", "pop", "folk", "indie rock", "alternative",
                        "classic rock", "80s", "hip-hop", "alternative rock", "experimental",
                        "indie pop", "soul", "rap", "hard rock", "ambient", "new wave", "acoustic",
                        "dance", "british", "trip-hop", "punk", "jazz", "post-rock", "french", "blues",
                        "lo-fi", "psychedelic", "post-punk", "chillout", "60s",
                        "metal", "industrial", "funk", "electro", "britpop", "rnb", "instrumental",
                        "dubstep", "punk rock", "shoegaze", "downtempo", "house", "synthpop", "dream pop", "heavy metal", "idm"}));

        topTagsPositions = new HashMap<>();
        int index = 0;
        for (String tag : topTags) {
            topTagsPositions.put(tag, index);
            index++;
        }
    }

    public Song getTrack(String songTitle, String author) {
        EchoNestAPI echoNest = new EchoNestAPI(Configuration.getNestApiKey());
        SongParams songParams = new SongParams();
        songParams.setTitle(songTitle);
        songParams.setArtist(author);
        songParams.includeSongHotttnesss();
        songParams.sortBy("song_hotttnesss", false);
        Song topSong = null;
        try {
            List<Song> songs = echoNest.searchSongs(songParams);
            if (songs != null && songs.size() > 0) {
                topSong = songs.get(0);
            }

        } catch (EchoNestException e) {
            e.printStackTrace();
        }
        return topSong;
    }

    public String collectTagVector(Artist artist) {
        Collection<String> tags = artist.getTags();
        char[] featureVector = new char[TAGS_COUNT];
        for (int pos = 0; pos < featureVector.length; pos++) {
            featureVector[pos] = '0';
        }
        if (tags != null && tags.size() > 0) {

            for (String tagName : tags) {
                if (topTagsPositions.containsKey(tagName)) {
                    int position = topTagsPositions.get(tagName);
                    featureVector[position] = '1';
                }
            }
        }

        return String.valueOf(featureVector);
    }
}
