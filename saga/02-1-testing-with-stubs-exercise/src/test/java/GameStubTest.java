import org.junit.Test;
import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;
import rs.saga.businessobject.Partizan;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;
import rs.saga.service.Game;
import rs.saga.service.IGame;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-30
 */
public class GameStubTest {
    private ITeam home;
    private ITeam away;
    private ITeamRepo teamRepo;
    private IGame gameUnderTest;

    @Test
    public void testUpdateTeamSuccess() {
        home = new CrvenaZvezda();
        away = new Partizan();
        teamRepo = new ITeamRepo() {
            @Override
            public boolean delete(ITeam team) throws TeamNotFoundException {
                return false;
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        };
        gameUnderTest = new Game(home, away, teamRepo);

        ITeam updated = gameUnderTest.update(home);

        assertNotNull(updated);
        assertThat(updated.name(), is("CrvenaZvezda"));
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateTeamFailure() {
        home = new CrvenaZvezda();
        away = new Partizan();
        teamRepo = new ITeamRepo() {
            @Override
            public boolean delete(ITeam team) throws TeamNotFoundException {
                throw new TeamNotFoundException();
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        };

        gameUnderTest = new Game(home, away, teamRepo);

        gameUnderTest.update(home);
    }

    @Test
    public void testFindByNameSuccess() {
        home = new CrvenaZvezda();
        away = new Partizan();
        teamRepo = new ITeamRepo() {
            @Override
            public boolean delete(ITeam team) throws TeamNotFoundException {
                return false;
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        };
        gameUnderTest = new Game(home, away, teamRepo);

        ITeam found = gameUnderTest.findByName("CrvenaZvezda");

        assertNotNull(found);
        assertThat(found.name(), is("CrvenaZvezda"));
    }

    @Test(expected = TeamNotFoundException.class)
    public void testFindByNameFailure() {
        home = new CrvenaZvezda();
        away = new Partizan();
        teamRepo = new ITeamRepo() {
            @Override
            public boolean delete(ITeam team) throws TeamNotFoundException {
                throw new TeamNotFoundException();
            }

            @Override
            public ITeam save(ITeam team) {
                return team;
            }
        };

        gameUnderTest = new Game(home, away, teamRepo);

        gameUnderTest.findByName("CrvenaZvezda");
    }
}
