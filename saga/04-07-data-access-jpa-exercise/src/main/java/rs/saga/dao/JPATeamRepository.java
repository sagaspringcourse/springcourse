package rs.saga.dao;

import org.springframework.stereotype.Repository;
import rs.saga.businessobject.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public class JPATeamRepository implements ITeamRepo {


    private EntityManager entityManager;


    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Team save(Team team) {
        return null;
    }

    @Override
    public List<Team> findByName(Team team) {
        return null;
    }


}
