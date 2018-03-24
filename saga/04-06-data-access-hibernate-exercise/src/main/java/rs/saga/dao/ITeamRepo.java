package rs.saga.dao;

import rs.saga.businessobject.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
public interface ITeamRepo {
    void delete(Team team) throws TeamNotFoundException;

    Team save(Team team);
}
