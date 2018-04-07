package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
@Transactional
@Profile("hibernate")
public class HibernatePlayerRepository implements IPlayerStateTransitionRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public HibernatePlayerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void remove(Player nino) {
        getSession().remove(nino);
    }


    @Override
    public int save(Player player) {
        int sqlCode = 0;
        try {
            getSession().save(player);
        } catch (Exception e) {
            sqlCode = 1;
        }
        System.out.println("Is Entity managed: " + isManaged(player));
        return sqlCode;
    }

    @Override
    public Boolean isManaged(Player player) {
        return getSession().contains(player);
    }

    @Override
    public Player get(Long playerId) {
        return getSession().get(Player.class, playerId);
    }
}
