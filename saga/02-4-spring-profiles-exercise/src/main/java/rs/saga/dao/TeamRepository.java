package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-28
 */
@Repository
public class TeamRepository implements ITeamRepo {

    private Map<String, ITeam> teams = new HashMap<>();

    private DataSource dataSource;

    @Autowired
    public TeamRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ITeam save(ITeam team) {
        return teams.put(team.name(), team);
    }

    @Override
    public boolean delete(ITeam team) throws TeamNotFoundException {
        if (teams.get(team.name()) != null) {
            teams.remove(team.name());
            return true;
        } else {
            throw new TeamNotFoundException();
        }
    }

    @PostConstruct
    public void populateMap() {
        teams.put("CrvenaZvezda", new CrvenaZvezda());
    }
}
