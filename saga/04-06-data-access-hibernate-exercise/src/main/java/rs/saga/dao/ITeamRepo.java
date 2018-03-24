package rs.saga.dao;

import rs.saga.domain.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
public interface ITeamRepo {
    void delete(Team team) throws TeamNotFoundException;

    Team findByName(String buducnost);

    Team save(Team team);
}
