package rs.saga.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
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

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Player> findAll() {
        TypedQuery<Player> query = entityManager.createQuery("from Player p order by p.firstName", Player.class);
        return query.getResultList();
    }

    @Override
    public List<Player> findPlayersWithPositionalParameter(Integer ageMin, Integer ageMax) {
        TypedQuery<Player> query = entityManager.createQuery("from Player p where (p.age between ?1 and ?2) and firstName like '%ik%' order by p.firstName", Player.class);
        query.setParameter(1, ageMin);
        query.setParameter(2, ageMax);
        return query.getResultList();
    }

    @Override
    public List<Player> findAllUsingNativeQuery() {
        Query query = entityManager.createNativeQuery("SELECT * FROM s_player", Player.class);

        List<Player> players = query.getResultList();
        return players;
    }

    @Override
    public List<Player> findPlayersUsingNamedParameters(Integer ageMin, Integer ageMax) {
        TypedQuery<Player> query = entityManager.createQuery("from Player p where (p.age between :ageMin and :ageMax) and firstName like '%ik%' order by p.firstName", Player.class);
        query.setParameter("ageMin", ageMin);
        query.setParameter("ageMax", ageMax);
        return query.getResultList();
    }

    @Override
    public List<Team> findTeamsUsingFunction() {
        TypedQuery<Team> query = entityManager.createQuery("select distinct(p.team) from Player p join p.team where lower(p.team.name) = 'crvena zvezda'", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsUsingNamedQuery() {
        TypedQuery<Team> query = entityManager.createNamedQuery("Team.withMoreThanOnePlayer", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public List<Team> findTeamsUsingJoin() {
        TypedQuery<Team> query = entityManager.createQuery("select distinct p.team from Player p join p.team group by p.team.name having count(*) > 2", Team.class);
        List<Team> teams = query.getResultList();
        return teams;
    }

    @Override
    public Long countPlayersUsingStoredProcedure(long playerId) {
        StoredProcedureQuery query = entityManager
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

}
