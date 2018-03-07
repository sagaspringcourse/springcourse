import configurationmetadata.PetSitterConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rs.saga.businessobject.IOperationsService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class SpringMain {

    public static void main(String[] args) {
        ApplicationContext petSitterApp = new AnnotationConfigApplicationContext(PetSitterConfig.class);
        IOperationsService operationsService = petSitterApp.getBean("operationsService", IOperationsService.class);
        System.out.println(operationsService.createResponse());
    }
}
