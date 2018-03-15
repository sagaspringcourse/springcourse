package rs.saga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.saga.businessobject.ITeam;
import rs.saga.configurationmetada.GameConfig;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
@ContextConfiguration(classes = GameConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("dev")
public class GameIT {

    @Autowired
    private ITeam home;
    @Autowired
    private ITeam away;
    @Autowired
    private IGame gameUnderTest;
    @Autowired
    private ITeamRepo repo;

    @Test
    public void testUpdateSuccess() {
        // setting up data
        repo.save(home);

        ITeam updated = gameUnderTest.update(home);
        assertEquals("Partizan", updated.name());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        gameUnderTest.update(home);
    }
}
