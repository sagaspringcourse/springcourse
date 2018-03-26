package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Player;
import rs.saga.domain.Skill;
import rs.saga.domain.Team;

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

    @Autowired
    private ITeamRepo teamRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    @Commit
    public void save() throws Exception {
        Skill skill = new Skill();
        skill.setType("Defense");
        skill.setValue(50);

        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        nino.getSkills().add(skill);
        skill.getPlayers().add(nino);

        Team team = new Team("Buducnost");
        nino.setTeam(team);
        team.getPlayers().add(nino);

        Player player = playerRepo.save(nino);

        assertNotNull(player.getId());
    }

    @Test
    public void findByFirstName() throws Exception {
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(2, players.size());
    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories
    @EnableTransactionManagement
    static class TestConfig {

    }
}