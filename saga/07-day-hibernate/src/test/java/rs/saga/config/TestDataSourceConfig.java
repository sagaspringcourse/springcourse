package rs.saga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@PropertySource("classpath:db/datasource.properties")
public class TestDataSourceConfig {

    // Beans implementing BeanFactoryPostProcessor must use static modifier inside the Java Configuration
    // so that they are created before other beans
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
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


    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        return hibernateProp;
    }

}
