package rs.saga.dao;

import rs.saga.businessobject.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
public interface ITeamRepo {
    Team save(Team team);

    List<Team> findByName(Team team);

}
