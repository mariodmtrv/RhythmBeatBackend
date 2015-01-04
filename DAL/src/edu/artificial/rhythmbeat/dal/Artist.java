package edu.artificial.rhythmbeat.dal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Mario on 1/4/2015.
 */
@Entity
public class Artist {
    private String artistId;
    private String artistName;
    private String tag1;
    private String tag2;
    private String tag3;
    private Integer listeners;
    private Integer plays;

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
    @Column(name = "tag1")
    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    @Basic
    @Column(name = "tag2")
    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    @Basic
    @Column(name = "tag3")
    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artist artist = (Artist) o;

        if (artistId != null ? !artistId.equals(artist.artistId) : artist.artistId != null) return false;
        if (artistName != null ? !artistName.equals(artist.artistName) : artist.artistName != null) return false;
        if (listeners != null ? !listeners.equals(artist.listeners) : artist.listeners != null) return false;
        if (plays != null ? !plays.equals(artist.plays) : artist.plays != null) return false;
        if (tag1 != null ? !tag1.equals(artist.tag1) : artist.tag1 != null) return false;
        if (tag2 != null ? !tag2.equals(artist.tag2) : artist.tag2 != null) return false;
        if (tag3 != null ? !tag3.equals(artist.tag3) : artist.tag3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artistId != null ? artistId.hashCode() : 0;
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        result = 31 * result + (tag1 != null ? tag1.hashCode() : 0);
        result = 31 * result + (tag2 != null ? tag2.hashCode() : 0);
        result = 31 * result + (tag3 != null ? tag3.hashCode() : 0);
        result = 31 * result + (listeners != null ? listeners.hashCode() : 0);
        result = 31 * result + (plays != null ? plays.hashCode() : 0);
        return result;
    }
}
