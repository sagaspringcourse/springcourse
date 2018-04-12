package rs.saga.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("hibernate")
public class HibernatePlayerRepository implements IPlayerRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernatePlayerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Player> selectCriteria() {
        Criteria cPlayers = getSession().createCriteria(Player.class);
        List<Player> players = cPlayers.list();
        return players;
    }

    @Override
    public List<Player> restrictCriteria() {
        Session session = getSession();

        Criterion criterion = Restrictions.between("age", 28, 30);
        Criterion nameCriteria = Restrictions.eq("firstName", "Manja");
        Criteria cPlayers = session.createCriteria(Player.class);
        cPlayers.add(Restrictions.or(criterion, nameCriteria));

        List<Player> players = cPlayers.list();
        return players;
    }

    @Override
    public List<Player> paginationCriteria() {

        int pageCount = 2;
        int pageNumber = 2;
        Criteria cPlayers = getSession().createCriteria(Player.class);
        cPlayers.setMaxResults(pageCount);
        cPlayers.setFirstResult(pageNumber * pageCount);

        List<Player> players = cPlayers.list();
        return players;
    }
}
