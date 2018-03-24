package rs.saga.dao;

import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.DBTestDataConfig;
import rs.saga.configurationmetada.DataSourceConfig;
import rs.saga.domain.Player;

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
public class HibernatePlayerRepositoryIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void save() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        int returnCode = playerRepo.save(nino);
        assertNotNull(returnCode);
    }

    @Test
    public void findByFirstName() throws Exception {
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(2, players.size());
    }

    @Configuration
    @Import(value = {DataSourceConfig.class, DBTestDataConfig.class})
    @EnableTransactionManagement
    static class TestConfig {

        @Bean
        public IPlayerRepo playerRepo(DataSource dataSource) {
            return new HibernatePlayerRepository(sessionFactory(dataSource));
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

}