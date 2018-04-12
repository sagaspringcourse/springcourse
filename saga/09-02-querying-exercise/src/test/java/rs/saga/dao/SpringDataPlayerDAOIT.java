package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.SpringDataConfig;
import rs.saga.domain.Team;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration(classes = SpringDataConfig.class)
@RunWith(SpringRunner.class)
@Transactional
public class SpringDataPlayerDAOIT {

    @Autowired
    private ISpringDataPlayerRepo playerRepo;

    @Test
    public void testFindTeamsWithNikola() {
        List<Team> all = playerRepo.findTeamWithPlayer("Nikola");

        assertEquals(1, all.size());
    }


}