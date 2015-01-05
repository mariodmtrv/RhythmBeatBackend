package edu.artificial.rhythmbeat.access;

/**
 * Created by Mario on 1/3/2015.
 */
public class Configuration {
    private static final String LASTFM_API_KEY = "3a0025823af70b20fdd099c319d2f623";

    private static final String NEST_API_KEY = "EUAF8RR9RS5Y9VBFW";

    public static String getLastfmApiKey() {
        return LASTFM_API_KEY;
    }

    public static String getNestApiKey() {
        return NEST_API_KEY;
    }
}
