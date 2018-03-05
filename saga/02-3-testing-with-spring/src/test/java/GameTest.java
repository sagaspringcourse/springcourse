import configurationmetadata.GameConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.businessobject.ITeam;
import rs.saga.service.Game;

import static junit.framework.TestCase.assertNull;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-30
 */
@ContextConfiguration(classes = GameConfig.class)
@RunWith(SpringRunner.class)
public class GameTest {


    @Autowired
    private ITeam host;

    @Autowired
    private Game game;

    @Test
    public void testUpdateTeamFailed() {
        ITeam update = game.update(host);

        assertNull(update);
    }

}
