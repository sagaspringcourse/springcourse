package rs.saga.service;

import org.junit.Before;
import org.junit.Test;
import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;
import rs.saga.businessobject.Partizan;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-07
 */
public class GameStubTest {

    private ITeam home;
    private ITeam away;
    private Game gameUnderTest;

    @Before
    public void setup() {
        home = new CrvenaZvezda();
        away = new Partizan();
        gameUnderTest = new Game(home, away);
    }

    @Test
    public void testUpdateSuccess() {
        // initialize with stub
        gameUnderTest.setRepo(new ITeamRepo() {
            @Override
            public void delete(ITeam team) throws TeamNotFoundException {
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        });

        ITeam updated = gameUnderTest.update(home);

        assertEquals("Crvena Zvezda", updated.name());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        // initialize with stub
        gameUnderTest.setRepo(new ITeamRepo() {
            @Override
            public void delete(ITeam team) throws TeamNotFoundException {
                throw new TeamNotFoundException();
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        });
        gameUnderTest.update(home);
    }

}