package rs.saga.configurationmetada;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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

    @Profile("h2")
    @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource simpleDriverDataSource = new DriverManagerDataSource();
        simpleDriverDataSource.setDriverClassName(driverClassName);
        simpleDriverDataSource.setUsername(username);
        simpleDriverDataSource.setPassword(password);
        simpleDriverDataSource.setUrl(url);
        return simpleDriverDataSource;
    }

    @Value("${mysqlDriverClassName}")
    private String mysqlDriverClassName;
    @Value("${mysqlUrl}")
    private String mysqlUrl;
    @Value("${mysqlUsername}")
    private String mysqlUsername;
    @Value("${mysqlPassword}")
    private String mysqlPassword;

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource mysqlDS = new DriverManagerDataSource();
        mysqlDS.setDriverClassName(mysqlDriverClassName);
        mysqlDS.setUsername(mysqlUsername);
        mysqlDS.setPassword(mysqlPassword);
        mysqlDS.setUrl(mysqlUrl);
        return mysqlDS;
    }

}
