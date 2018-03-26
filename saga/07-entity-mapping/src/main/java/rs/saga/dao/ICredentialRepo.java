package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.saga.domain.Credentials;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface ICredentialRepo extends JpaRepository<Credentials, Long> {
}
