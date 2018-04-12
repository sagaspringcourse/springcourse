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
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Player;
import rs.saga.domain.Skill;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class ManyToManyIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Autowired
    private ISkillRepo skillRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }


    @Test
    public void testManyToManyBidirectional() throws Exception {
        Player nino = PlayerBuilder.getInstance().nino().createPlayer();

        Skill defense = new Skill("defense", 50);
        Skill offense = new Skill("offense", 100);

        nino.getSkills().add(defense);
        nino.getSkills().add(offense);

        // managing the inverse side
        defense.getPlayers().add(nino);
        offense.getPlayers().add(nino);

        playerRepo.save(nino);

        // accessing an owning side through the inverse side
        List<Skill> allSkills = skillRepo.findAll();
        assertNotNull(allSkills);
        assertTrue(allSkills.size() == 2);

        List<Player> players = allSkills.iterator().next().getPlayers();
        assertThat(players.size(), is(1));
        assertThat(players.iterator().next().getFirstName(), is("Nikola"));
    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}