package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
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
    public List<Player> findAll() {
        Query query = getSession().createQuery("select p from Player p");
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public List<Team> findTeamsUsingJoin() {
        Query query = getSession().createQuery("select distinct p.team from Player p join p.team group by p.team.name having count(*) > 2");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsUsingFunction() {
        Query query = getSession().createQuery("select distinct(p.team) from Player p join p.team where lower(p.team.name) = 'Crvena Zvezda'");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsUsingNamedQuery() {
        Query query = getSession().getNamedQuery("Team.withMoreThanOnePlayer");
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Player> findPlayersWithPositionalParameter(Integer ageMin, Integer ageMax) {
        Query query = getSession().createQuery("from Player p where (p.age between ? and ?) and firstName like '%ik%' order by p.firstName");
        query.setParameter(0, ageMin);
        query.setParameter(1, ageMax);
        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public List<Player> findAllUsingNativeQuery() {
        NativeQuery query = getSession().createNativeQuery("select * from s_player")
                .addEntity(Player.class);
        List<Player> players = query.list();
        return players;
    }

    @Override
    public Long countPlayersUsingStoredProcedure(long playerId) {
        StoredProcedureQuery query = getSession()
                .createStoredProcedureQuery("count_players")
                .registerStoredProcedureParameter(
                        "playerId", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter(
                        "playerCount", Long.class, ParameterMode.OUT)
                .setParameter("playerId", playerId);

        query.execute();

        Long playerCount = (Long) query
                .getOutputParameterValue("playerCount");

        return playerCount;
    }


    @Override
    public List<Player> findPlayersUsingNamedParameters(Integer ageMin, Integer ageMax) {
        Query query = getSession().createQuery("from Player p where (p.age between :ageMin and :ageMax) and firstName like '%ik%' order by p.firstName");
        query.setParameter("ageMin", ageMin);
        query.setParameter("ageMax", ageMax);
        List<Player> players = query.getResultList();
        return players;
    }


}
