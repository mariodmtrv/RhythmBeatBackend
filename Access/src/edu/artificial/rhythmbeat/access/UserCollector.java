package edu.artificial.rhythmbeat.access;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;

import java.util.*;

/**
 * Created by Mario on 1/3/2015.
 */
public class UserCollector {

    private String rootUser = "RJ";
    private static final int DESIRED_USERS_COUNT = 5;
    private static final int MAX_OVERALL_PLAYCOUNT = 2000;
    private static final int MIN_OVERALL_PLAYCOUNT = 200;
    private static final int MIN_TRACK_PLAYCOUNT = 2;

    private User getUser(String userName) {
        User userData = User.getInfo(userName, Configuration.getLastfmApiKey());
        return userData;
    }

    public List<User> getRequiredUsers() {
        User accessUser = null;
        List<User> resultUsers = new ArrayList<>();
        Queue<String> observableUsers = new LinkedList<>();
        User initial = getUser(rootUser);
        observableUsers.add(rootUser);
        int collectedUsers = 0;
        int collectableUsers = 0;
        Set<String> observedUsers = new HashSet<>();
        while (!observableUsers.isEmpty()) {

            String currentUser = observableUsers.poll();
            User currentUserEntity = getUser(currentUser);
            int currentUserPlayCount = currentUserEntity.getPlaycount();
            if ((MIN_OVERALL_PLAYCOUNT < currentUserPlayCount) && (currentUserPlayCount < MAX_OVERALL_PLAYCOUNT)) {
                resultUsers.add(currentUserEntity);
                collectedUsers++;
            }
            if (collectedUsers >= DESIRED_USERS_COUNT) {
                break;
            }
            observedUsers.add(currentUser);
            if (collectableUsers < DESIRED_USERS_COUNT) {

                PaginatedResult<User> friends = accessUser.getFriends(currentUser, Configuration.getLastfmApiKey());
                for (User friend : friends) {

                    String name = friend.getName();
                    if (!observedUsers.contains(name)) {
                        observableUsers.add(name);

                        if ((MIN_OVERALL_PLAYCOUNT < currentUserPlayCount) && (currentUserPlayCount < MAX_OVERALL_PLAYCOUNT)) {
                            collectableUsers++;
                        }
                    }

                }
            }
        }
        return resultUsers;
    }

    public Collection<Track> getTrackHistory(String username) {
        Collection<Track> topTracks = User.getTopTracks(username, Configuration.getLastfmApiKey());
        Map<String, Track> trackHistory = new HashMap<>();
        for (Track track : topTracks) {
            Track topTrack = Track.getInfo(track.getArtist(), track.getMbid(), null, username, Configuration.getLastfmApiKey());
            trackHistory.put(track.getMbid(), topTrack);
        }
        Collection<Track> recentTracks = null;
        for (int pageIndex = 0; pageIndex <= 10; pageIndex++) {
            recentTracks = User.getRecentTracks(username, pageIndex, 100, Configuration.getLastfmApiKey()).getPageResults();

            for (Track track : recentTracks) {
                if (trackHistory.containsKey(track.getMbid())) {
                    continue;
                } else {

                    Track recentTrack = Track.getInfo(track.getArtist(), track.getMbid(), null, username, Configuration.getLastfmApiKey());
                    if (recentTrack != null) {
                        if (recentTrack.getUserPlaycount() >= MIN_TRACK_PLAYCOUNT) {
                            trackHistory.put(track.getMbid(), recentTrack);
                        }
                    }
                }
            }
        }

        return trackHistory.values();
    }

}
