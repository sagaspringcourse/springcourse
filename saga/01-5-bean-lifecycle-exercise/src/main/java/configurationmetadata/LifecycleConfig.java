package configurationmetadata;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.businessobject"})
public class LifecycleConfig {

}
