package rs.saga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.saga.builder.TeamBuilder;
import rs.saga.domain.Team;
import rs.saga.config.DataSourceConfig;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;
import rs.saga.dao.TeamRepository;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class GameIT {

    @Autowired
    private IGameService gameUnderTest;
    @Autowired
    private ITeamRepo repo;

    @Test
    public void testUpdateSuccess() {
        Team partizan = TeamBuilder.getInstance().setName("Partizan").createTeam();

        // setting up data
        repo.save(partizan);

        Team updated = gameUnderTest.update(partizan);
        assertEquals("Partizan", updated.getName());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        Team partizan = TeamBuilder.getInstance().setName("Someting new").createTeam();
        gameUnderTest.update(partizan);
    }

    @Configuration
    @Import(DataSourceConfig.class)
    static class TestGameConfig {
        @Bean
        public IGameService game() {
            return new GameService();
        }

        @Bean
        public ITeamRepo teamRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            return new TeamRepository(namedParameterJdbcTemplate);
        }
    }
}
