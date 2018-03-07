package configurationmetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rs.saga.businessobject.IOperationsService;
import rs.saga.businessobject.IRequest;
import rs.saga.businessobject.IResponse;
import rs.saga.businessobject.Request;
import rs.saga.businessobject.SimpleOperationsService;

import java.util.Date;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-07
 */
@Configuration
@ComponentScan("rs.saga.businessobject")
public class PetSitterConfig {

    @Bean
    public IRequest request() {
        return new Request(new Date(2018, 11, 11), new Date(2018, 11, 20), "Fluffy");
    }

    @Bean
    public IOperationsService operationsService(IResponse response) {
     return new SimpleOperationsService(request(), response);
    }
}
