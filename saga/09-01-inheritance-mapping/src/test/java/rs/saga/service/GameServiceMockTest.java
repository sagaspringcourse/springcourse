package rs.saga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rs.saga.dao.IGameRepo;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;
import rs.saga.domain.SoccerGame;
import rs.saga.domain.Team;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
public class GameServiceMockTest {
    private Team home;
    private Team away;

    @InjectMocks
    private GameService gameUnderTest;

    @Mock
    private ITeamRepo teamRepo;

    @Mock
    private IGameRepo gameRepo;

    @Before
    public void setup() {
        home = new Team("Crvena Zvezda");
        away = new Team("Partizan");
        gameUnderTest = new GameService(gameRepo);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateSuccess() {
        // setup mock
        doNothing().when(teamRepo).delete(home); // mocking methods that return void
        when(teamRepo.save(home)).thenReturn(home);
        when(gameRepo.save(Mockito.any(SoccerGame.class))).thenReturn(new SoccerGame());
        // initialize with mock
        gameUnderTest.setRepo(teamRepo);

        Team updated = gameUnderTest.update(home);

        assertEquals("Crvena Zvezda", updated.getName());
    }

    @Test(expected = TeamNotFoundException.class)
    public void testUpdateFailure() {
        // setup mock
        doThrow(new TeamNotFoundException()).when(teamRepo).delete(home);
        // initialize with mock
        gameUnderTest.setRepo(teamRepo);

        gameUnderTest.update(home);
    }
}
