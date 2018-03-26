package rs.saga.dao;

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
import rs.saga.config.DataSourceConfig;
import rs.saga.domain.Credentials;
import rs.saga.domain.Player;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@Transactional
public class CredentialsRepositoryIT {

    @Autowired
    private ICredentialRepo credentialRepo;

    @Autowired
    private IPlayerRepo playerRepo;

    @Test
    public void save() throws Exception {
        Player nino = new PlayerBuilder().setFirstName("Nikola").setLastName("Ninovic").setEmail("nikola.n@saga.rs").createPlayer();
        Credentials credentials = new Credentials();
        credentials.setUsername("ninovic.n");
        credentials.setPassword("secret");
        credentials.setPlayer(nino);
        nino.setCredentials(credentials);
        credentialRepo.save(credentials);

        System.out.println(playerRepo.getOne(nino.getId()).getCredentials().getUsername());
    }

    @Configuration
    @Import(DataSourceConfig.class)
    @EnableJpaRepositories
    static class TestConfig {

    }
}