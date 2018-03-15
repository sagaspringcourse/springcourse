package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.businessobject.Player;
import rs.saga.configurationmetada.DataSourceConfig;

import javax.sql.DataSource;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class PlayerRepositoryIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void findByFirstName() throws Exception {
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(2, players.size());
    }

    @Configuration
    static class TestConfig {

        @Bean
        public IPlayerRepo playerRepo(DataSource dataSource) {
            return new PlayerRepository(dataSource);
        }
    }

}