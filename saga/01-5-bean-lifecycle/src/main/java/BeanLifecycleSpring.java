import configurationmetadata.GameConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import rs.saga.service.IGame;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class BeanLifecycleSpring {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);

        IGame utakmica = context.getBean("game", IGame.class);
        utakmica.play();

        ((AbstractApplicationContext)context).close();
    }
}
