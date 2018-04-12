package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.JPAConfig;
import rs.saga.domain.Team;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class JPAPlayerDAOIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void testFindTeamsWithNikola() {
        List<Team> all = playerRepo.findTeamWithPlayer("Nikola");

        assertEquals(1, all.size());
    }

    @Configuration
    @Import(JPAConfig.class)
    static class TestConfig {

        @Bean
        public IPlayerRepo playerStateTransitionRepo() {
            return new JPAPlayerRepository();
        }

    }
}
