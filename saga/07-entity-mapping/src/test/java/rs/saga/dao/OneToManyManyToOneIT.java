package rs.saga.dao;

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
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class OneToManyManyToOneIT {

    @Autowired
    private ITeamRepo teamRepo;

    @Test
    public void testOneToManyBidirectional() throws Exception {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        Player slave = PlayerBuilder.getInstance().slave().createPlayer();

        Team buducnost = new Team("Buducnost");
        buducnost.getPlayers().add(nino);
        buducnost.getPlayers().add(slave);

        slave.setTeam(buducnost);
        nino.setTeam(buducnost);

        teamRepo.save(buducnost);

        // asserting saving by checking that ID is generated and assigned
        assertNotNull(buducnost.getId());

        assertEquals(2, teamRepo.findOne(buducnost.getId()).getPlayers().size());
    }

    @Configuration
    @EnableJpaRepositories
    @Import(DataSourceConfig.class)
    static class TestConfig {
    }

}