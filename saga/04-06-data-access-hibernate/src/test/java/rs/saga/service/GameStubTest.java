package rs.saga.service;

import org.junit.Before;
import org.junit.Test;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;
import rs.saga.domain.Team;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-07
 */
public class GameStubTest {

    private Team home;
    private Team away;
    private GameService gameUnderTest;

    @Before
    public void setup() {
        home = new Team(1l, "Crvena Zvezda");
        away = new Team(2l, "Partizan");
        gameUnderTest = new GameService();
    }

    @Test
    public void testUpdateSuccess() {
        // initialize with stub
        gameUnderTest.setRepo(new ITeamRepo() {
            @Override
            public void delete(Team team) throws TeamNotFoundException {
            }

            @Override
            public Team save(Team team) {
                return team;
            }
        });

        Team updated = gameUnderTest.update(home);

        assertEquals("Crvena Zvezda", updated.getName());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        // initialize with stub
        gameUnderTest.setRepo(new ITeamRepo() {
            @Override
            public void delete(Team team) throws TeamNotFoundException {
                throw new TeamNotFoundException();
            }

            @Override
            public Team save(Team team) {
                return team;
            }
        });
        gameUnderTest.update(home);
    }

}