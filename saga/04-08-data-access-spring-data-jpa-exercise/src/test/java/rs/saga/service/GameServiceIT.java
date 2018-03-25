package rs.saga.service;

import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.DBPopulationConfig;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;
import rs.saga.domain.Team;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@Transactional
public class GameServiceIT {

    @Qualifier("gameRepo")
    @Autowired
    private IGameService gameUnderTest;

    @Test
    public void testUpdateSuccess() {
        Team partizan = new Team("Partizan");
        Team updated = gameUnderTest.update(partizan);
        assertEquals("Partizan", updated.getName());
    }

    @Ignore
    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        Team partizan = new Team("Someting new");
        gameUnderTest.update(partizan);
    }


    @Configuration
    @Import(DBPopulationConfig.class)
    @EnableJpaRepositories(basePackages = "rs.saga.dao")
    static class TestConfig {

        @Bean
        public IGameService gameRepo(ITeamRepo teamRepo) {
            GameService gameService = new GameService();
            gameService.setRepo(teamRepo);
            return gameService;
        }

    }

}
