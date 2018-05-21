package rs.saga.service;

import rs.saga.domain.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
public interface ITeamService {


    Team find(String name);

    Team save(Team team);
}
