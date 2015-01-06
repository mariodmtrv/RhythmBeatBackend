package edu.artificial.rhythmbeat.dal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Mario on 1/6/2015.
 */
@Entity
public class Artist {
    private String artistId;
    private String artistName;
    private Integer listeners;
    private Integer plays;
    private String tagVector;

    @Id
    @Column(name = "artistId")
    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "artistName")
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Basic
    @Column(name = "listeners")
    public Integer getListeners() {
        return listeners;
    }

    public void setListeners(Integer listeners) {
        this.listeners = listeners;
    }

    @Basic
    @Column(name = "plays")
    public Integer getPlays() {
        return plays;
    }

    public void setPlays(Integer plays) {
        this.plays = plays;
    }

    @Basic
    @Column(name = "tagVector")
    public String getTagVector() {
        return tagVector;
    }

    public void setTagVector(String tagVector) {
        this.tagVector = tagVector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (artistId != null ? !artistId.equals(artist.artistId) : artist.artistId != null) return false;
        if (artistName != null ? !artistName.equals(artist.artistName) : artist.artistName != null) return false;
        if (listeners != null ? !listeners.equals(artist.listeners) : artist.listeners != null) return false;
        if (plays != null ? !plays.equals(artist.plays) : artist.plays != null) return false;
        if (tagVector != null ? !tagVector.equals(artist.tagVector) : artist.tagVector != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artistId != null ? artistId.hashCode() : 0;
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        result = 31 * result + (listeners != null ? listeners.hashCode() : 0);
        result = 31 * result + (plays != null ? plays.hashCode() : 0);
        result = 31 * result + (tagVector != null ? tagVector.hashCode() : 0);
        return result;
    }
}
