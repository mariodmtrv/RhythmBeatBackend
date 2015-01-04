package edu.artificial.rhythmbeat.access;

import de.umass.lastfm.PaginatedResult;
import de.umass.lastfm.Track;
import de.umass.lastfm.User;
import de.umass.lastfm.Period;

import java.util.*;

/**
 * Created by Mario on 1/3/2015.
 */
public class UserCollector {

    private String rootUser = "RJ";
    private static final int DESIRED_USERS_COUNT = 1000;

    private User getUser(String userName) {
        User userData = User.getInfo(userName, Configuration.getApiKey());
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
            if (collectedUsers >= DESIRED_USERS_COUNT) {
                break;
            }
            String currentUser = observableUsers.poll();
            User currentUserEntity = getUser(currentUser);
            if (currentUserEntity.getPlaycount() < 10000) {
                resultUsers.add(currentUserEntity);
            }
            observedUsers.add(currentUser);

            PaginatedResult<User> friends = accessUser.getFriends(currentUser, Configuration.getApiKey());
            for (User friend : friends) {

                String name = friend.getName();
                if (!observedUsers.contains(name)) {
                    observableUsers.add(name);
                    collectedUsers++;
                }

            }
        }
        return resultUsers;
    }

    public Collection<Track> getTrackHistory(String username) {
        Period period = Period.OVERALL;
        Collection<Track> topTracks = User.getTopTracks(username, Configuration.getApiKey());
        Collection<Track> recentTracks = User.getRecentTracks(username, 0, 200, Configuration.getApiKey()).getPageResults();
        topTracks.addAll(recentTracks);
        return topTracks;
    }

}
