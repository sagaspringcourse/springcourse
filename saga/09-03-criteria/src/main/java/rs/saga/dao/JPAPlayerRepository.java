package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("jpa")
public class JPAPlayerRepository implements IPlayerRepo {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Player> selectCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> root = criteria.from(Player.class);
        criteria.select(root);


        TypedQuery<Player> all = em.createQuery(criteria);
        for (Player player : all.getResultList()) {
            System.out.println(player.getFirstName());
        }

        return all.getResultList();
    }

    @Override
    public List<Player> restrictCriteria() {
        // simple selection
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> root = criteria.from(Player.class);

        Path<Integer> age = root.get("age");

        criteria.select(root).where(cb.and(cb.between(age, 28, 30)));

        TypedQuery<Player> all = em.createQuery(criteria);

        for (Player player : all.getResultList()) {
            System.out.println(player.getFirstName());
        }

        return all.getResultList();
    }

    @Override
    public List<Player> paginationCriteria() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Player> criteria = cb.createQuery(Player.class);
        Root<Player> root = criteria.from(Player.class);
        criteria.select(root);

        int pageCount = 2;
        int pageNumber = 2;

        TypedQuery<Player> query = em.createQuery(criteria);
        query.setFirstResult(pageNumber * pageCount);
        query.setMaxResults(pageCount);

        List<Player> players = query.getResultList();
        for (Player player : players) {
            System.out.println(player.getFirstName());
        }


        return players;
    }

}
