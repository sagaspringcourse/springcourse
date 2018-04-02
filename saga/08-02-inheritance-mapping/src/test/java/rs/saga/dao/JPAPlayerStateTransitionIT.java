package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.JPAConfig;
import rs.saga.domain.Player;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class JPAPlayerStateTransitionIT {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private IPlayerStateTransitionRepo playerStateTransitionRepo;

    @Test(expected = IllegalArgumentException.class)
    public void playerEntityStateTransitionTest() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        playerStateTransitionRepo.save(nino);

        System.out.println("Is Entity managed: " + playerStateTransitionRepo.isManaged(nino));

        // player is now detached
        playerStateTransitionRepo.remove(nino);
    }

    @Test
    public void playerFindFromDB() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(nino);
        assertThat(entityManager.contains(nino), is(Boolean.TRUE));
        transaction.commit();
        // close persistence context
        entityManager.close();

        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        Player player = entityManager.find(Player.class, nino.getId());
        // assert that the player is already in the managed state
        assertThat(entityManager.contains(player), is(Boolean.TRUE));
        assertThat(entityManager.contains(nino), is(Boolean.FALSE));
        // just cleaning DB record
        entityManager.remove(player);
        transaction.commit();
        // close persistence context
        entityManager.close();
    }

    @Test
    public void testModifyPlayer() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(nino);
        assertThat(entityManager.contains(nino), is(Boolean.TRUE));
        transaction.commit();
        // close persistence context
        entityManager.close();

        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        Player player = entityManager.find(Player.class, nino.getId());
        player.setAddress("Changed address");

        // just cleaning DB record
        entityManager.remove(player);
        transaction.commit();
        // close persistence context
        entityManager.close();
    }

    @Test
    public void testMergeDetachedPlayer() {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        //open persistence context
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(nino);
        entityManager.clear();
        assertThat(entityManager.contains(nino), is(Boolean.FALSE));
        transaction.commit();
        // close persistence context
        entityManager.close();


        entityManager = emf.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();

        assertThat(entityManager.contains(nino), is(Boolean.FALSE));
        nino = entityManager.merge(nino);
        assertThat(entityManager.contains(nino), is(Boolean.TRUE));

        entityManager.detach(nino);
        nino.setAddress("Changed Address");
        // just cleaning DB record
//        entityManager.remove(nino);
        transaction.commit();
        // close persistence context
        entityManager.close();
    }




    @Configuration
    @Import(JPAConfig.class)
    static class TestConfig {

        @Bean
        public IPlayerStateTransitionRepo playerStateTransitionRepo() {
            return new JPAPlayerRepository();
        }

    }
}
