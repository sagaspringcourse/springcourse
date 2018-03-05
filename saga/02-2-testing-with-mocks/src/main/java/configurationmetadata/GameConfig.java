package configurationmetadata;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.businessobject", "rs.saga.dao", "rs.saga.service"})
@Import(DataSourceConfig.class)
public class GameConfig {

}
