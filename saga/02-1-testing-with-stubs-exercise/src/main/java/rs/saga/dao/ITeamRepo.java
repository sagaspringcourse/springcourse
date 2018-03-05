package rs.saga.dao;

import rs.saga.businessobject.ITeam;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-28
 */
public interface ITeamRepo {

    boolean delete(ITeam team) throws TeamNotFoundException;

    ITeam findByName(String name) throws TeamNotFoundException;

    ITeam save(ITeam team);
}
