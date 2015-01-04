package edu.artificial.rhythmbeat.dal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Mario on 1/5/2015.
 */
@Entity
public class Track {
    private String name;
    private String trackId;
    private String artistId;
    private String tag1;
    private String tag2;
    private String tag3;
    private Integer year;

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
    @Column(name = "year")
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (artistId != null ? !artistId.equals(track.artistId) : track.artistId != null) return false;
        if (name != null ? !name.equals(track.name) : track.name != null) return false;
        if (tag1 != null ? !tag1.equals(track.tag1) : track.tag1 != null) return false;
        if (tag2 != null ? !tag2.equals(track.tag2) : track.tag2 != null) return false;
        if (tag3 != null ? !tag3.equals(track.tag3) : track.tag3 != null) return false;
        if (trackId != null ? !trackId.equals(track.trackId) : track.trackId != null) return false;
        if (year != null ? !year.equals(track.year) : track.year != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        result = 31 * result + (artistId != null ? artistId.hashCode() : 0);
        result = 31 * result + (tag1 != null ? tag1.hashCode() : 0);
        result = 31 * result + (tag2 != null ? tag2.hashCode() : 0);
        result = 31 * result + (tag3 != null ? tag3.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        return result;
    }
}
