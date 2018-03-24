package rs.saga.domain.entitymapping.onetoonebidirectional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-23
 */
@Repository
@Transactional
public class CredentialsRepository implements ICredentialsRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void persist(Credential credential) {
        entityManager.persist(credential);
    }
}
