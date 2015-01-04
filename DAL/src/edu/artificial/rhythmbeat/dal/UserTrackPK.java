package edu.artificial.rhythmbeat.dal;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Mario on 1/5/2015.
 */
public class UserTrackPK implements Serializable {
    private String userId;
    private String trackId;

    @Column(name = "userId")
    @Id
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "trackId")
    @Id
    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTrackPK that = (UserTrackPK) o;

        if (trackId != null ? !trackId.equals(that.trackId) : that.trackId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (trackId != null ? trackId.hashCode() : 0);
        return result;
    }
}
