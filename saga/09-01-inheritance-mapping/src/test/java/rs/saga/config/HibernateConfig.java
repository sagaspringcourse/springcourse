package rs.saga.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@PropertySource("classpath:db/datasource.properties")
@Configuration
@EnableTransactionManagement
public class HibernateConfig {

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
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "create");
        hibernateProp.put("hibernate.format_sql", true);
        hibernateProp.put("hibernate.use_sql_comments", true);
        hibernateProp.put("hibernate.show_sql", true);
        return hibernateProp;
    }

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {
        SessionFactory sessionFactory = new LocalSessionFactoryBuilder(dataSource)
                .scanPackages("rs.saga.domain")
                .addProperties(hibernateProperties())
                .buildSessionFactory();

        return sessionFactory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
