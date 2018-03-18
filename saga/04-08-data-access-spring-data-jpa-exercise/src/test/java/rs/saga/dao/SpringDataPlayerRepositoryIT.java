package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.DBTestDataConfig;
import rs.saga.configurationmetada.DataSourceConfig;
import rs.saga.domain.Player;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class SpringDataPlayerRepositoryIT {

    @Autowired
    private SpringDataJPARepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void save() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        Player saved = playerRepo.save(nino);
        assertNotNull(saved.getId());
    }

    @Test
    public void findByFirstName() throws Exception {
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(2, players.size());
    }

    @Test
    public void findByFirstNameAndLastName() throws Exception {
        Set<Player> players = playerRepo.findByFirstNameAndLastName("Nikola", "Stankovic");
        assertEquals(1, players.size());
    }


    @Configuration
    @Import(value = {DataSourceConfig.class, DBTestDataConfig.class})
    @EnableTransactionManagement
    @EnableJpaRepositories
    static class TestConfig {

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