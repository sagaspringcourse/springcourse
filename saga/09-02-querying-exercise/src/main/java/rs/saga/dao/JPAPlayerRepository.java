package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("jpa")
public class JPAPlayerRepository implements IPlayerRepo {

    private EntityManager entityManager;

    @Override
    public List<Team> findTeamWithPlayer(String firstName) {
        return null;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
