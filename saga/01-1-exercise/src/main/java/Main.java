import rs.saga.businessobject.IRequest;
import rs.saga.businessobject.IResponse;
import rs.saga.businessobject.Request;
import rs.saga.businessobject.Response;
import rs.saga.businessobject.SimpleOperationsService;

import java.util.Date;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class Main {

    public static void main(String[] args) {
        IRequest request = new Request(new Date(2018, 11, 11), new Date(2018, 11, 20), "Fluffy");
        IResponse response = new Response("John");
        SimpleOperationsService service = new SimpleOperationsService(request, response);
        System.out.println(service.createResponse());
    }
}
