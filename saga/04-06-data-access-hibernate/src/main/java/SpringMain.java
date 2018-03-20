import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.builder.TeamBuilder;
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

        IGameService game = applicationContext.getBean("game", IGameService.class);
        Team zvezda = TeamBuilder.getInstance().setName("Crvena Zvezda").createTeam();
        Team partizan = TeamBuilder.getInstance().setName("Partizan").createTeam();
        game.playGame(zvezda, partizan);
    }
}
