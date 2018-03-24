package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.saga.businessobject.ITeam;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
@Repository
public class TeamRepository implements ITeamRepo {
    private Map<String, ITeam> teams = new HashMap<String, ITeam>();

    private DataSource dataSource;

    @Autowired
    public TeamRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(ITeam team) throws TeamNotFoundException {
        if (teams.containsKey(team.name())) {
            teams.remove(team.name());
        } else {
            throw new TeamNotFoundException();
        }
    }

    @Override
    public ITeam save(ITeam team) {
        teams.put(team.name(), team);
        return team;
    }
}
