package edu.artificial.rhythmbeat.dal;

import org.hibernate.ejb.HibernatePersistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceProvider;
import java.util.HashMap;

/**
 * Created by Mario on 1/5/2015.
 */
public class PersistenceConfiguration {
    private static final EntityManager manager;

    static {
        PersistenceProvider provider = new HibernatePersistence();
        EntityManagerFactory factory = provider.createEntityManagerFactory("RhythmBeatPersistence", new HashMap());
        manager = factory.createEntityManager();
    }

    public static EntityManager getEntityManager() {
        return manager;
    }

}
