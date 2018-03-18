package rs.saga.dao;

import rs.saga.businessobject.ITeam;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
public interface ITeamRepo {
    void delete(ITeam team) throws TeamNotFoundException;

    ITeam save(ITeam team);
}
