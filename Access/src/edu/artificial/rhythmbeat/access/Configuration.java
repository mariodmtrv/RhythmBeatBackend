package edu.artificial.rhythmbeat.access;

import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;

/**
 * Created by Mario on 1/3/2015.
 */
public class Configuration {
    private static final String API_KEY = "3a0025823af70b20fdd099c319d2f623";



    public static String getApiKey() {
        return API_KEY;
    }
}
