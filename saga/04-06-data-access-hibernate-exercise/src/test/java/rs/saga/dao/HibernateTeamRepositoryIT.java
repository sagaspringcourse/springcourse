package rs.saga.dao;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.config.DBPopulationConfig;
import rs.saga.domain.Team;

import static org.junit.Assert.assertNotNull;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@RunWith(SpringRunner.class)
@Transactional
public class HibernateTeamRepositoryIT {

    @Autowired
    private ITeamRepo teamRepo;

    @Test
    public void save() throws Exception {
        teamRepo.save(new Team("Buducnost"));
        Team buducnost = teamRepo.findByName("Buducnost");

        // asserting saving by checking that ID is generated and assigned
        assertNotNull(buducnost.getId());
    }

    @Test
    public void findByName() throws Exception {
        Team partizan = teamRepo.findByName("Partizan");

        assertNotNull(partizan.getId());
    }


    @Configuration
    @Import(DBPopulationConfig.class)
    @EnableTransactionManagement
    static class TestConfig {

        @Bean
        public ITeamRepo teamRepo(SessionFactory sessionFactory) {
            return new TeamRepository(sessionFactory);
        }

    }

}