package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Player;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
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
    public int save(Player player) {
        int sqlCode = 0;
        try {
            getSession().save(player);
        } catch (Exception e) {
            sqlCode = 1;
        }
        return sqlCode;
    }

    @Override
    public Set<Player> findByFirstName(String firstName) {
        Query query = getSession().createQuery("FROM Player p WHERE p.firstName= :firstName");
        query.setParameter("firstName", firstName);
        List<Player> players = query.getResultList();
        return new HashSet<>(players);
    }

    @Override
    public Set<Player> findByFirstNameNamed(String firstName) {
        return null;
    }

}
