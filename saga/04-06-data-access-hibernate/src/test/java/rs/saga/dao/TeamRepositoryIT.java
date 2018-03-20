package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.TeamBuilder;
import rs.saga.domain.Team;
import rs.saga.config.DataSourceConfig;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class TeamRepositoryIT {

    @Autowired
    private ITeamRepo teamRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(teamRepo);
    }

    @Test
    public void save() throws Exception {
        Team zvezda = new TeamBuilder().setName("Crvena Zvezda").createTeam();
        Team saved = teamRepo.save(zvezda);
        assertNotNull(saved);
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
        public TeamRepository teamRepo(DataSource dataSource) {
            return new TeamRepository(namedJdbcTemplate(dataSource));
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