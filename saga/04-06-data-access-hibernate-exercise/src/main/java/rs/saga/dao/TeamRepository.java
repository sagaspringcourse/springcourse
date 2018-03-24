package rs.saga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Team;


/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
@Repository
public class TeamRepository implements ITeamRepo {

    private SessionFactory sessionFactory;

    @Autowired
    public TeamRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void delete(Team team) throws TeamNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        team = findTeam(team);
        if (team == null) {
            throw new TeamNotFoundException();
        }
        session.delete(team);
    }

    @Override
    public Team findByName(String name) {
        Team team = findTeam(new Team(name));
        return team;
    }

    @Override
    public Team save(Team team) {
        Session session = sessionFactory.getCurrentSession();
        Team found = findTeam(team);
        if (found != null) {
            session.delete(found);
        }
        session.saveOrUpdate(team);
        return team;
    }

    private Team findTeam(Team team) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Team t WHERE t.name = :name");
        query.setParameter("name", team.getName());
        team = (Team) query.uniqueResult();
        return team;
    }

}
