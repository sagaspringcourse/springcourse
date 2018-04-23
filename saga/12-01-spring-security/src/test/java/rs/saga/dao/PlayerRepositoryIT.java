package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.JPAConfig;
import rs.saga.domain.Player;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class PlayerRepositoryIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void save() throws Exception {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        Player player = playerRepo.save(nino);
        assertNotNull(player.getId());
    }

    @Test
    public void findByFirstName() throws Exception {
        playerRepo.save(PlayerBuilder.getInstance().nino().createPlayer());
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(1, players.size());
    }

    @Configuration
    @Import(JPAConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}