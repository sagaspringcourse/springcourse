package rs.saga.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.dao", "rs.saga.service"})
@Import(DataSourceConfig.class)
@EnableAspectJAutoProxy
public class GameConfig {


}
