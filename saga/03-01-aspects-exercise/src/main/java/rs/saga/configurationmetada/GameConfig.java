package rs.saga.configurationmetada;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.dao", "rs.saga.service", "rs.saga.businessobject"})
public class GameConfig {


}
