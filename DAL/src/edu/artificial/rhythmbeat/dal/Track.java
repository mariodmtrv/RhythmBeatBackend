package edu.artificial.rhythmbeat.dal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Mario on 1/6/2015.
 */
@Entity
public class Track {
    private String name;
    private String trackId;
    private String artistId;
    private Double energy;
    private Double loudness;
    private Double tempo;
    private Double hotttness;
    private Double danceability;
    private Double mode;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "trackId")
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @Basic
    @Column(name = "artistId")
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "energy")
    public Double getEnergy() {
        return energy;
    }

    public void setEnergy(Double energy) {
        this.energy = energy;
    }

    @Basic
    @Column(name = "loudness")
    public Double getLoudness() {
        return loudness;
    }

    public void setLoudness(Double loudness) {
        this.loudness = loudness;
    }

    @Basic
    @Column(name = "tempo")
    public Double getTempo() {
        return tempo;
    }

    public void setTempo(Double tempo) {
        this.tempo = tempo;
    }

    @Basic
    @Column(name = "hotttness")
    public Double getHotttness() {
        return hotttness;
    }

    public void setHotttness(Double hotttness) {
        this.hotttness = hotttness;
    }

    @Basic
    @Column(name = "danceability")
    public Double getDanceability() {
        return danceability;
    }

    public void setDanceability(Double danceability) {
        this.danceability = danceability;
    }

    @Basic
    @Column(name = "mode")
    public Double getMode() {
        return mode;
    }

    public void setMode(Double mode) {
        this.mode = mode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (artistId != null ? !artistId.equals(track.artistId) : track.artistId != null) return false;
        if (danceability != null ? !danceability.equals(track.danceability) : track.danceability != null) return false;
        if (energy != null ? !energy.equals(track.energy) : track.energy != null) return false;
        if (hotttness != null ? !hotttness.equals(track.hotttness) : track.hotttness != null) return false;
        if (loudness != null ? !loudness.equals(track.loudness) : track.loudness != null) return false;
        if (mode != null ? !mode.equals(track.mode) : track.mode != null) return false;
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (tempo != null ? !tempo.equals(track.tempo) : track.tempo != null) return false;
        if (trackId != null ? !trackId.equals(track.trackId) : track.trackId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        result = 31 * result + (artistId != null ? artistId.hashCode() : 0);
        result = 31 * result + (energy != null ? energy.hashCode() : 0);
        result = 31 * result + (loudness != null ? loudness.hashCode() : 0);
        result = 31 * result + (tempo != null ? tempo.hashCode() : 0);
        result = 31 * result + (hotttness != null ? hotttness.hashCode() : 0);
        result = 31 * result + (danceability != null ? danceability.hashCode() : 0);
        result = 31 * result + (mode != null ? mode.hashCode() : 0);
        return result;
    }
}
