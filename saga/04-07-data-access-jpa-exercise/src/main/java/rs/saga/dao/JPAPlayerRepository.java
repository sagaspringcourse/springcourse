package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public class JPAPlayerRepository implements IPlayerRepo {


    private EntityManager entityManager;

    @PersistenceContext
    void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public int save(Player player) {
        int sqlCode = 0;
        try {
            entityManager.persist(player);
        } catch (Exception e) {
            sqlCode = 1;
        }
        return sqlCode;
    }

    @Override
    public Set<Player> findByFirstName(String firstName) {
        javax.persistence.Query query = entityManager.createQuery("FROM Player p WHERE p.firstName= :firstName");
        query.setParameter("firstName", firstName);
        List<Player> players = query.getResultList();
        return new HashSet<>(players);
    }

    @Override
    public Set<Player> findByFirstNameNamed(String firstName) {
        return null;
    }

}
