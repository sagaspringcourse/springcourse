package rs.saga.dao;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.HibernateConfig;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Profile("hibernate")
public class HibernatePlayerDAOIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void testFindAll() {
        List<Player> all = playerRepo.findAll();

        assertEquals(7, all.size());
    }

    @Test
    public void testFindPlayersPositional() {
        List<Player> all = playerRepo.findPlayersWithPositionalParameter(28, 30);

        assertEquals(2, all.size());
    }

    @Test
    public void testFindPlayersNamed() {
        List<Player> all = playerRepo.findPlayersUsingNamedParameters(28, 30);

        assertEquals(2, all.size());
    }

    @Test
    public void testNamedQuery() {
        List<Team> all = playerRepo.findTeamsUsingNamedQuery();

        assertEquals(2, all.size());
    }

    @Test
    public void testFindTeams() {
        List<Team> all = playerRepo.findTeamsUsingJoin();

        assertEquals(2, all.size());
    }

    @Test
    public void testFindFunctions() {
        List<Team> all = playerRepo.findTeamsUsingFunction();

        assertEquals(1, all.size());
    }

    @Test
    public void testFind() {
        List<Team> all = playerRepo.findTeamsUsingFunction();

        assertEquals(1, all.size());
    }

    @Test
    public void testFindAllUsingNativeQuery() {
        List<Player> players = playerRepo.findAllUsingNativeQuery();

        assertEquals(7, players.size());
    }

    @Test
    public void testCountPlayersUsingStoredProcedure() {
        Long count = playerRepo.countPlayersUsingStoredProcedure(1l);

        assertEquals(new Long(1), count);
    }

    @Import(HibernateConfig.class)
    @Configuration
    static class HibConfig {

        @Bean
        public IPlayerRepo hibernatePlayerRepo(SessionFactory sessionFactory) {
            return new HibernatePlayerRepository(sessionFactory);
        }
    }

}
