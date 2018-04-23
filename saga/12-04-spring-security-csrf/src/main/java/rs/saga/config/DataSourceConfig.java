package rs.saga.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@PropertySource("classpath:db/datasource.properties")
@EnableTransactionManagement
public class DataSourceConfig {

    @Value("${driverClassName}")
    private String driverClassName;
    @Value("${url}")
    private String url;
    @Value("${musername}")
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
    public DataSource dataSource() {
        DriverManagerDataSource mysqlDS = new DriverManagerDataSource();
        mysqlDS.setDriverClassName(driverClassName);
        mysqlDS.setUsername(username);
        mysqlDS.setPassword(password);
        mysqlDS.setUrl(url);
        return mysqlDS;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public Properties hibernateProperties() {
        Properties hibernateProp = new Properties();
        hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        hibernateProp.put("hibernate.hbm2ddl.auto", "update");
        hibernateProp.put("hibernate.format_sql", false);
        hibernateProp.put("hibernate.use_sql_comments", false);
        hibernateProp.put("hibernate.show_sql", false);
        return hibernateProp;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("rs.saga.domain");
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setJpaProperties(hibernateProperties());
        factoryBean.afterPropertiesSet();
        return factoryBean.getNativeEntityManagerFactory();
    }


}
