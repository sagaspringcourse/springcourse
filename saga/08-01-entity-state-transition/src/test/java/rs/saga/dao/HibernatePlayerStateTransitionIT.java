package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.HibernateConfig;
import rs.saga.domain.Player;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class HibernatePlayerStateTransitionIT {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private IPlayerStateTransitionRepo playerStateTransitionRepo;

    @Test
    public void playerEntityStateTransitionTest() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        playerStateTransitionRepo.save(nino);

        System.out.println("Is Entity managed: " + playerStateTransitionRepo.isManaged(nino));

        // player is now detached
        playerStateTransitionRepo.remove(nino);
    }

    @Test
    public void playerGetFromDB() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(nino);
        assertThat(session.contains(nino), is(Boolean.TRUE));
        transaction.commit();
        // close persistence context
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Player player = session.get(Player.class, nino.getId());
        // assert that the player is already in the managed state
        assertThat(session.contains(player), is(Boolean.TRUE));
        assertThat(session.contains(nino), is(Boolean.FALSE));
        // just cleaning DB record
        session.remove(player);
        transaction.commit();
        // close persistence context
        session.close();
    }

    @Test
    public void testModifyPlayer() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(nino);
        assertThat(session.contains(nino), is(Boolean.TRUE));
        transaction.commit();
        // close persistence context
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Player player = session.get(Player.class, nino.getId());
        player.setAddress("Nino changed address");
        player.setFirstName("Marko");

        // just cleaning DB record
        session.remove(player);
        transaction.commit();
        // close persistence context
        session.close();
    }


    @Test
    public void testReattachPlayer() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(nino);
        assertThat(session.contains(nino), is(Boolean.TRUE));
        transaction.commit();
        // close persistence context
        session.close();

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        // assert detached
        assertThat(session.contains(nino), is(Boolean.FALSE));
        session.update(nino);
        // assert re-attached
        assertThat(session.contains(nino), is(Boolean.TRUE));
        nino.setFirstName("Marko");
        transaction.commit();
        // close persistence context
        session.close();
    }

    @Test
    public void testFlushPersistenceContext() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(nino);
        assertThat(session.contains(nino), is(Boolean.TRUE));
        nino.setFirstName("Changed to Marko");
        session.flush();

        nino.setAddress("Changed to Zemun");
        transaction.commit();
        // close persistence context
        session.close();
    }


    @Configuration
    @Import(HibernateConfig.class)
    static class TestConfig {

        @Bean
        public IPlayerStateTransitionRepo playerStateTransitionRepo(SessionFactory sessionFactory) {
            return new HibernatePlayerRepository(sessionFactory);
        }

    }
}
