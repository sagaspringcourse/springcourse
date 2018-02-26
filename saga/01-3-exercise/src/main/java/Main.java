import rs.saga.businessobject.SimpleOperationsService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class Main {

    public static void main(String[] args) {
        SimpleOperationsService service = new SimpleOperationsService();
        System.out.println(service.createResponse());
    }
}
