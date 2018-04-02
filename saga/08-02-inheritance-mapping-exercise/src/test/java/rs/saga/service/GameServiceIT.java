package rs.saga.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.DataSourceConfig;
import rs.saga.dao.IGameRepo;
import rs.saga.dao.ITeamRepo;
import rs.saga.domain.Team;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class GameServiceIT {

    @Autowired
    private IGameService gameUnderTest;

    @Autowired
    private IGameRepo gameRepo;

    @Test
    public void testInheritance() {
        Team partizan = new Team("Partizan");
        Team crvenaZvezda = new Team("CrvenaZvezda");
        gameUnderTest.playGame(partizan, crvenaZvezda);

        assertNotNull(gameRepo.findOne(1L));
    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories(basePackages = "rs.saga.dao")
    static class TestConfig {

        @Bean
        public IGameService gameRepo(ITeamRepo teamRepo, IGameRepo gameRepo) {
            GameService gameService = new GameService(gameRepo);
            gameService.setRepo(teamRepo);
            return gameService;
        }

        @Bean
        public EntityManagerFactory entityManagerFactory(DataSource dataSource, Properties hibernateProperties) {
            LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
            factoryBean.setPackagesToScan("rs.saga.domain");
            factoryBean.setDataSource(dataSource);
            factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            factoryBean.setJpaProperties(hibernateProperties);
            factoryBean.afterPropertiesSet();
            return factoryBean.getNativeEntityManagerFactory();
        }



    }

}
