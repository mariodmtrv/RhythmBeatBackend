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
    private static final int DESIRED_USERS_COUNT = 10;
    private static final int MAX_PLAYCOUNTS = 100000;

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
        Set<String> observedUsers = new HashSet<>();
        while (!observableUsers.isEmpty()) {

            String currentUser = observableUsers.poll();
            User currentUserEntity = getUser(currentUser);
            if (currentUserEntity.getPlaycount() < MAX_PLAYCOUNTS) {
                resultUsers.add(currentUserEntity);

            }
            observedUsers.add(currentUser);
            if (collectedUsers < DESIRED_USERS_COUNT) {

                PaginatedResult<User> friends = accessUser.getFriends(currentUser, Configuration.getLastfmApiKey());
                for (User friend : friends) {

                    String name = friend.getName();
                    if (!observedUsers.contains(name)) {
                        observableUsers.add(name);

                        if (friend.getPlaycount() < MAX_PLAYCOUNTS) {
                            collectedUsers++;
                        }
                    }

                }
            }
        }
        return resultUsers;
    }

    public Collection<Track> getTrackHistory(String username) {
        Collection<Track> topTracks = User.getTopTracks(username, Configuration.getLastfmApiKey());
        Collection<Track> recentTracks = User.getRecentTracks(username, 0, 80, Configuration.getLastfmApiKey()).getPageResults();
        Map<String, Track> trackHistory = new HashMap<>();
        for (Track track : topTracks) {
            trackHistory.put(track.getMbid(), track);
        }
        for (Track track : recentTracks) {
            trackHistory.put(track.getMbid(), track);
        }

        return trackHistory.values();
    }

}
