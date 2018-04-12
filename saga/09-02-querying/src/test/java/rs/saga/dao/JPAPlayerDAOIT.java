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
public class JPAPlayerDAOIT {

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
    public void testFindTeams() {
        List<Team> all = playerRepo.findTeamsUsingJoin();

        assertEquals(2, all.size());
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

    @Test
    public void testFindFunctions() {
        List<Team> all = playerRepo.findTeamsUsingFunction();

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
