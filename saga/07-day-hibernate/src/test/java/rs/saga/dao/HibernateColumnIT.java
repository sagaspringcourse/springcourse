package rs.saga.dao;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.TestDataSourceConfig;
import rs.saga.domain.column.Player;
import rs.saga.domain.column.PlayerBuilder;

import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@RunWith(SpringRunner.class)
@Transactional
public class HibernateColumnIT {

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    public void testSave() throws Exception {
        Player nino = new PlayerBuilder()
                .setFirstName("Nikola")
                .setLastName("Ninovic")
                .setEmail("ninovic.n@saga.rs")
                .setAddress("Novi Beograd")
                .setPassword("test")
                .setUsername("ninovic.n")
                .createPlayer();
        assertNotNull(getSession().save(nino));
    }

    /**
     * test exception when email is nullable
     */
    @Test(expected = PropertyValueException.class)
    public void givenEmailIsNull_whenSaved_thanHibernateException() throws Exception {
        Player nino = new PlayerBuilder()
                .setFirstName("Nikola")
                .setLastName("Ninovic")
                .setAddress("Novi Beograd")
                .setPassword("test")
                .setUsername("ninovic.n")
                .createPlayer();
        getSession().save(nino);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Configuration
    @Import(TestDataSourceConfig.class)
    @EnableTransactionManagement
    static class TestConfig {
        @Bean
        public SessionFactory sessionFactory(DataSource dataSource, Properties hibernateProperties) {
            SessionFactory sessionFactory = new LocalSessionFactoryBuilder(dataSource)
                    .addAnnotatedClasses(Player.class)
                    .addProperties(hibernateProperties)
                    .buildSessionFactory();

            return sessionFactory;
        }

        @Bean
        public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
            return new HibernateTransactionManager(sessionFactory);
        }

    }
}