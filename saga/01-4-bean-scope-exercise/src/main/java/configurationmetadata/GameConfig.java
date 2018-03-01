package configurationmetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import rs.saga.businessobject.Referee;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.businessobject"})
public class GameConfig {

    @Bean
    public Referee ref() {
        return new Referee("John");
    }
}

