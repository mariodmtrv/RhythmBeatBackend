package edu.artificial.rhythmbeat.dal;

import javax.persistence.*;

/**
 * Created by Mario on 1/6/2015.
 */
@Entity
@IdClass(UserTrackPK.class)
public class UserTrack {
    private String userId;
    private String trackId;
    private int playsCount;

    @Id
    @Column(name = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
    @Column(name = "playsCount")
    public int getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrack userTrack = (UserTrack) o;

        if (playsCount != userTrack.playsCount) return false;
        if (trackId != null ? !trackId.equals(userTrack.trackId) : userTrack.trackId != null) return false;
        if (userId != null ? !userId.equals(userTrack.userId) : userTrack.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        result = 31 * result + playsCount;
        return result;
    }
}
