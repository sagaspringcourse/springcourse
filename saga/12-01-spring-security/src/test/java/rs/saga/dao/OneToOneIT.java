package rs.saga.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.config.JPAConfig;
import rs.saga.domain.Credentials;
import rs.saga.domain.Player;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class OneToOneIT {

    @Autowired
    private IPlayerRepo playerRepo;

    @Autowired
    private ICredentialsRepo credentialsRepo;

    @Before
    public void setUp() throws Exception {
        assertNotNull(playerRepo);
    }

    @Test
    public void testOneToOneBidirectional() throws Exception {
        Credentials credentials = new Credentials();
        credentials.setPassword("pass");
        credentials.setUsername("ninovic.n");

        Player nino = PlayerBuilder.getInstance().nino().createPlayer();
        credentials.setPlayer(nino);
        nino.setCredentials(credentials);

        credentialsRepo.save(credentials);

        assertNotNull(nino.getId());

        nino = playerRepo.findOne(nino.getId());
        assertThat(nino.getCredentials().getUsername(), is("ninovic.n"));
    }

    @Configuration
    @Import(JPAConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}