import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.config.GameConfig;
import rs.saga.domain.Team;
import rs.saga.service.IGameService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(GameConfig.class);

        IGameService game = applicationContext.getBean("gameService", IGameService.class);
        Team zvezda = new Team("Crvena Zvezda");
        Team partizan = new Team("Partizan");
        game.playGame(zvezda, partizan);
    }
}
