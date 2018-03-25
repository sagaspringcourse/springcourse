package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import rs.saga.config.DBTestDataConfig;
import rs.saga.configurationmetada.DataSourceConfig;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@RunWith(SpringRunner.class)
@ContextConfiguration
public class JPATeamRepositoryIT {
    @Test
    public void save() throws Exception {
    }

    @Test
    public void findByName() throws Exception {
    }

    @Configuration
    @Import(value = {DataSourceConfig.class, DBTestDataConfig.class})
    @EnableTransactionManagement
    static class TestConfig {

        @Bean
        public ITeamRepo teamRepo() {
            return new JPATeamRepository();
        }

        @Bean
        public Properties hibernateProperties() {
            Properties hibernateProp = new Properties();
            hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
            hibernateProp.put("hibernate.hbm2ddl.auto", "update");
            hibernateProp.put("hibernate.format_sql", true);
            hibernateProp.put("hibernate.use_sql_comments", true);
            hibernateProp.put("hibernate.show_sql", true);
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

        @Bean
        public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }
    }
}