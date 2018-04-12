package rs.saga.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import rs.saga.config.JPAConfig;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-30
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
public class JPAPlayerDAOIT {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void selectCriteria() throws Exception {
        assertEquals(7, playerRepo.selectCriteria().size());
    }

    @Test
    public void restrictCriteria() throws Exception {
        assertEquals(3, playerRepo.restrictCriteria().size());
    }

    @Test
    public void paginationCriteria() throws Exception {
        assertEquals(2, playerRepo.paginationCriteria().size());
    }

    @Configuration
    @Import(JPAConfig.class)
    static class TestConfig {

        @Bean
        public IPlayerRepo playerStateTransitionRepo() {
            return new JPAPlayerRepository();
        }

    }
}
