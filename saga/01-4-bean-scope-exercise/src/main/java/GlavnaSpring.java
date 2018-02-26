import configurationmetadata.GameConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.businessobject.IGame;
import rs.saga.businessobject.Referee;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class GlavnaSpring {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(GameConfig.class);

        IGame utakmica = context.getBean("game", IGame.class);
        IGame drugaUtakmica = context.getBean("game", IGame.class);
        Referee ref = context.getBean("ref", Referee.class);
        if (utakmica != drugaUtakmica) {
            utakmica.play();
        }

    }
}
