package edu.artificial.rhythmbeat.processing;

import edu.artificial.rhythmbeat.dal.Artist;
import edu.artificial.rhythmbeat.dal.Track;
import edu.artificial.rhythmbeat.dal.UserTrack;
import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Mario on 1/4/2015.
 */
public class Test {
    public static void main(String[] args) {
        PersistenceProvider provider = new HibernatePersistence();
        EntityManagerFactory factory = provider.createEntityManagerFactory("RhythmBeatPersistence", new HashMap());
        EntityManager manager = factory.createEntityManager();
        List<UserTrack> x = manager.createQuery("from UserTrack where playsCount=:playsCount", UserTrack.class).setParameter("playsCount", 2).getResultList();
        if (x.size() > 0) {
            System.out.print(x.get(0));
        }
        Track track = new Track();
        track.setName("Hello");
        track.setTrackId("123456");
        track.setArtistId("123");
        Artist artist = new Artist();
        artist.setArtistId("123");
        artist.setArtistName("Ivan");
        artist.setListeners(5);
        artist.setPlays(25);
        manager.getTransaction().begin();
        manager.persist(artist);
        manager.persist(track);
        manager.getTransaction().commit();
    }
}
