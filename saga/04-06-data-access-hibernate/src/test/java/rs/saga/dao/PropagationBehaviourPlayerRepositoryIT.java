package rs.saga.dao;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.IllegalTransactionStateException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Player;

import javax.sql.DataSource;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class PropagationBehaviourPlayerRepositoryIT {

    @Autowired
    private IPropagationPlayerRepo playerRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Transactional
    @Test
    public void saveRequired() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveRequired(nino);
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(3, players.size());
    }

    @Ignore
    @Transactional
    @Test
    public void saveRequiresNew() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveRequiresNew(nino);
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(3, players.size());
    }

    @Test(expected = IllegalTransactionStateException.class)
    public void saveMandatory() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveMandatory(nino);
    }

    @Transactional
    @Test(expected = IllegalTransactionStateException.class)
    public void saveNever() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveNever(nino);
    }

    @Transactional
    @Test
    public void saveSupports() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveSupports(nino);
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(3, players.size());
    }


    @Ignore
    @Transactional
    @Test
    public void saveNotSupported() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        playerRepo.saveNotSupported(nino);
        Set<Player> players = playerRepo.findByFirstName("Nikola");
        assertEquals(3, players.size());
    }


    @Configuration
    @Import(DataSourceConfig.class)
    @EnableTransactionManagement
    static class TestConfig {

        @Bean
        public NamedParameterJdbcTemplate namedJdbcTemplate(DataSource dataSouce) {
            return new NamedParameterJdbcTemplate(dataSouce);
        }

        @Bean
        public JdbcTemplate jdbcTemplate(DataSource dataSouce) {
            return new JdbcTemplate(dataSouce);
        }

        @Bean
        public IPropagationPlayerRepo playerRepo(DataSource dataSource) {
            return new PropagationBehaviourPlayerRepository(jdbcTemplate(dataSource), namedJdbcTemplate(dataSource));
        }

        @Value("classpath:db/schema.sql")
        private Resource schemaScript;

        @Value("classpath:db/test-data.sql")
        private Resource dataScript;

        @Bean
        public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
            final DataSourceInitializer initializer = new DataSourceInitializer();
            initializer.setDataSource(dataSource);
            initializer.setDatabasePopulator(databasePopulator());
            return initializer;
        }

        private DatabasePopulator databasePopulator() {
            final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.addScript(schemaScript);
            populator.addScript(dataScript);
            return populator;
        }

        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

}