package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Team;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
@Repository
public class TeamRepository implements ITeamRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void delete(Team team) throws TeamNotFoundException {
        team = findTeam(team);
        if (team == null) {
            throw new TeamNotFoundException();
        }
        entityManager.remove(team);
    }

    @Override
    public Team findByName(String name) {
        Team team = findTeam(new Team(name));
        return team;
    }

    @Override
    public Team save(Team team) {
        Team found = findTeam(team);
        if (found != null) {
            entityManager.remove(found);
        }
        entityManager.persist(team);
        return team;
    }

    private Team findTeam(Team team) {
        try {
            javax.persistence.Query query = entityManager.createQuery("FROM Team t WHERE t.name = :name");
            query.setParameter("name", team.getName());
            team = (Team) query.getSingleResult();
        } catch (Exception e) {
            team = null;
        }
        return team;
    }

}
