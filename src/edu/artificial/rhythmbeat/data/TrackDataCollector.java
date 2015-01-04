package edu.artificial.rhythmbeat.data;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Mario on 1/3/2015.
 */
public class TrackDataCollector {
    private Track track;

    public TrackDataCollector(Track track) {
        this.track = track;

        //  String albumName = track.getAlbum();
        // Album albumInfo = Album.getInfo(artistName, albumName, Configuration.getApiKey());
        // albumInfo.getReleaseDate();
        //Artist artist = Artist.getInfo(artistName, Configuration.getApiKey());

    }

    public String getArtistData(String id) {
        Artist artist = Artist.getInfo(id, Configuration.getApiKey());
        return artist.getName();
    }

    public String getArtist() {
        String artistName = track.getArtist();
        return artistName;
    }

    public Collection<String> getTags() {
        return track.getTags();
    }

    public String getYear() {
        String artistName = track.getArtist();
        String albumName = track.getAlbum();
        if (albumName != null) {
            Album albumInfo = Album.getInfo(artistName, albumName, Configuration.getApiKey());
            if (albumInfo != null) {
                Date released = albumInfo.getReleaseDate();
                if (released != null) {
                    return ((Integer) albumInfo.getReleaseDate().getYear()).toString();
                }
            }
        }
        return null;
    }
}
