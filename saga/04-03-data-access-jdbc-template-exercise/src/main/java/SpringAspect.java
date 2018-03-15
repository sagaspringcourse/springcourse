import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.configurationmetada.GameConfig;
import rs.saga.service.IGame;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
public class SpringAspect {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(GameConfig.class);

        IGame game = applicationContext.getBean("game", IGame.class);
        game.playGame();
    }
}
