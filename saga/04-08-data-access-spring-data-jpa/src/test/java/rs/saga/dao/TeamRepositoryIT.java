package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.TeamBuilder;
import rs.saga.businessobject.Team;
import rs.saga.config.DBTestDataConfig;
import rs.saga.configurationmetada.DataSourceConfig;

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
    @Import(value = {DataSourceConfig.class, DBTestDataConfig.class})
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
        @Bean
        public PlatformTransactionManager transactionManager(DataSource dataSource) {
            return new DataSourceTransactionManager(dataSource);
        }
    }

}