package edu.artificial.rhythmbeat.data;

import de.umass.lastfm.Track;

/**
 * Created by Mario on 1/3/2015.
 */
public class DataAccess {
    private void test() {
        String key = "3a0025823af70b20fdd099c319d2f623";
        String firstUser = "RJ";

   /*     Chart<Artist> chart = User.getWeeklyArtistChart(user, 10, key);
        DateFormat format = DateFormat.getDateInstance();
        String from = format.format(chart.getFrom());
        String to = format.format(chart.getTo());
        System.out.printf("Charts for %s for the week from %s to %s:%n", user, from, to);
        Collection<Artist> artists = chart.getEntries();
        for (Artist artist : artists) {
            System.out.println(artist.getName());
        }*/

    }

    public static void main(String[] args) {
        DataAccess a = new DataAccess();
        a.test();
    }
}
