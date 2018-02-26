import configurationmetadata.GameConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.businessobject.IGame;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class GlavnaSpring {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(GameConfig.class);

        IGame utakmica = context.getBean("game", IGame.class);
        utakmica.play();
        System.out.println(utakmica.getReferee());

        IGame utakmica2 = (IGame) context.getBean("game");
        utakmica2.getReferee().setName("Doe");
        System.out.println(utakmica2.getReferee());

        System.out.println(utakmica.getReferee());
    }
}
