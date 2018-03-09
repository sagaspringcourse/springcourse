package rs.saga.dao;

import org.springframework.stereotype.Repository;
import rs.saga.businessobject.ITeam;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
@Repository
public class TeamRepository implements ITeamRepo {


    @Override
    public void delete(ITeam team) throws TeamNotFoundException {
    }

    @Override
    public ITeam save(ITeam team) {
        return null;
    }
}
