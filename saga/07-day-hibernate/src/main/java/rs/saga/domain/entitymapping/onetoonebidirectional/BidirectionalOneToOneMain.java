package rs.saga.domain.entitymapping.onetoonebidirectional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-23
 */
public class BidirectionalOneToOneMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(BidirectionalOneToOneConfig.class);

        ICredentialsRepo credentialsRepository = applicationContext.getBean("credentialsRepository", ICredentialsRepo.class);
        Player player = new Player(null, "firstName", "", "", "", "", "");
        Credential credential = new Credential("test", "test", player);
        credentialsRepository.persist(credential);


    }
}
