package rs.saga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.saga.configurationmetada.GameConfig;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-09
 */
@ContextConfiguration(classes = GameConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TimeExecutionAspectIT  {

    @Autowired
    private IGame game;

    @Test
    public void testAdvisedMethods() {
        /*
            TODO exercise 03-01, test is not asserting anything, but console output should be something like

            Method name took 2
            Partizan won
            Method playGame took 13
        */
        game.playGame();
    }
}
