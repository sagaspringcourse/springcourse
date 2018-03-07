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
import rs.saga.service.Game;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-30
 */
public class MockingTest {
    private ITeam home;
    private ITeam away;

    @Mock
    private ITeamRepo teamRepo;

    @InjectMocks
    private Game gameUnderTest;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateTeamSuccess() {
        home = new CrvenaZvezda();
        away = new Partizan();
        gameUnderTest = new Game(home, away, teamRepo);
        when(teamRepo.delete(home)).thenReturn(Boolean.FALSE);
        when(teamRepo.save(home)).thenReturn(home);

        ITeam updated = gameUnderTest.update(home);

        assertNotNull(updated);
        verify(teamRepo, times(1)).delete(home);
        assertThat(updated.name(), is("CrvenaZvezda"));
    }

    @Test(expected = RuntimeException.class)
    public void testUpdateTeamFailure() {
        home = new CrvenaZvezda();
        away = new Partizan();
        gameUnderTest = new Game(home, away, teamRepo);
        when(teamRepo.delete(home)).thenThrow(new TeamNotFoundException());

        gameUnderTest.update(home);
    }

}
