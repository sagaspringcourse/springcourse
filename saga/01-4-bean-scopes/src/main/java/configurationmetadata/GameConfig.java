package configurationmetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import rs.saga.businessobject.Referee;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.businessobject"})
@Import(DataSourceConfig.class)
public class GameConfig {

    @Scope("prototype")
    @Bean
    public Referee referee() {
        return new Referee("John");
    }
}

