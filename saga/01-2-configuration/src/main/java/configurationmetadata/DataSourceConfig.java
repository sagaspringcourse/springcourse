package configurationmetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-28
 */
@Configuration
public class DataSourceConfig {


    @Bean
    public DataSource dataSource() {
        return new SimpleDriverDataSource();
    }
}
