package rs.saga.domain.entitymapping.onetomanyunidirectional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-23
 */
public class UnidirectionalOneToManyMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(UnidirectionalOneToManyConfig.class);
    }
}
