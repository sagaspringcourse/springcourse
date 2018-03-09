package rs.saga.configurationmetada;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@PropertySource("classpath:db/datasource.properties")
public class DataSourceConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${username}")
    private String username;
    @Value("${password}")
    private String password;

    // Beans implementing BeanFactoryPostProcessor must use static modifier inside the Java Configuration
    // so that they are created before other beans
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource simpleDriverDataSource = new DriverManagerDataSource();
        simpleDriverDataSource.setDriverClassName(driverClassName);
        simpleDriverDataSource.setUsername(username);
        simpleDriverDataSource.setPassword(password);
        simpleDriverDataSource.setUrl(url);
        return simpleDriverDataSource;
    }


}
