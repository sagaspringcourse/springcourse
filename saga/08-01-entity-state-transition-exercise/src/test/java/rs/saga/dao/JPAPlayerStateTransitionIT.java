package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.JPAConfig;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration(classes = JPAConfig.class)
@RunWith(SpringRunner.class)
public class JPAPlayerStateTransitionIT {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    public void toggleTeamStates() {

        // Team in transient state
        Team team = new Team("Crvena Zvezda");

        // TODO: open persistence context and create transaction

        // TODO: move team to managed state

        // TODO: assert that the team is managed (check if contained in entity manager)

        // TODO: commit and close persistence context

        // TODO: fetch from DB as managed entity

        // TODO: assert entity is managed

        // TODO: remove entity from entity manager and assert not managed

        // TODO: commit and close
    }
}
