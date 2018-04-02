package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.HibernateConfig;
import rs.saga.domain.Team;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration(classes = HibernateConfig.class)
@RunWith(SpringRunner.class)
public class HibernateTeamStateTransitionIT {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void toggleTeamStates() {

        // Team in transient state
        Team team = new Team("Crvena Zvezda");

        // TODO: open persistence context and create transaction

        // TODO: move team to managed state

        // TODO: assert that the team is managed (check if contained in session)

        // TODO: commit and close persistence context

        // TODO: fetch from DB as managed entity

        // TODO: assert entity is managed

        // TODO: remove entity from session and assert not managed

        // TODO: commit and close
    }
}
