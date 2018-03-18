package rs.saga.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;
import rs.saga.businessobject.Partizan;
import rs.saga.dao.ITeamRepo;
import rs.saga.dao.TeamNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
public class GameMockTest {
    private ITeam home;
    private ITeam away;

    @InjectMocks
    private Game gameUnderTest;

    @Mock
    private ITeamRepo teamRepo;

    @Before
    public void setup() {
        home = new CrvenaZvezda();
        away = new Partizan();
        gameUnderTest = new Game(home, away);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateSuccess() {
        // setup mock
        doNothing().when(teamRepo).delete(home); // mocking methods that return void
        when(teamRepo.save(home)).thenReturn(home);
        // initialize with mock
        gameUnderTest.setRepo(teamRepo);

        ITeam updated = gameUnderTest.update(home);

        assertEquals("Crvena Zvezda", updated.name());
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
