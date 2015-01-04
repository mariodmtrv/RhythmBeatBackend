package edu.artificial.rhythmbeat.datause;

import de.umass.lastfm.Artist;
import de.umass.lastfm.Track;
import edu.artificial.rhythmbeat.data.Configuration;

import java.io.*;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Mario on 1/3/2015.
 */
public class CollectData {
    BufferedReader reader;

    void readFile() {
        int count = 0;
        int successfulCount = 0, noIdFailCount = 0;
        try {
            reader = new BufferedReader(new FileReader(new File("C:\\Users\\Mario\\Documents\\University\\AI\\lastfm-dataset-1K\\userid-timestamp-artid-artname-traid-traname.tsv")));
            reader.readLine();

            while (true) {
                String line = reader.readLine();

                StringTokenizer st = new StringTokenizer(line);
                count++;

                String userId = st.nextToken();
                boolean hasLong = false;
                while (st.hasMoreTokens()) {
                    if (st.nextToken().length() > 15) {
                        hasLong = true;
                    }
                }
                String artistName = null, trackName = null;
                if (hasLong) {
                    successfulCount++;
                }
                System.out.println(line);

                //   System.out.println(userId + artistName + trackName);
                if (count > 1000000) {
                    break;
                }
             /*
               Artist artist = Artist.getInfo(artistId, Configuration.getApiKey());
                Track track = Track.getInfo(artistId, trackId, Configuration.getApiKey());
                if (artist != null && track != null) {
                    System.out.println(artist.getName() + "  result  " + track.getArtist());
                    successfulCount++;
                }
                else {
                    System.out.println("Failed     //////" +line);
                }*/
                if (count % 10 == 0) {
                    System.out.println("Count " + count);
                }
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successful" + (double) successfulCount / (double) count + "No id fail" + (double) noIdFailCount / (double) count);

    }

    public static void main(String[] args) {
        CollectData data = new CollectData();
        data.readFile();
    }
}
