import org.junit.Test;
import rs.saga.businessobject.ITeam;
import rs.saga.service.Game;

import static junit.framework.TestCase.assertNull;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-30
 */
public class SpringTest {


    private ITeam host;
    private Game game;

    @Test
    public void testUpdateTeamFailed() {
        ITeam update = game.update(host);
        assertNull(update);
    }

}
