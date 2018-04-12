package rs.saga.dao;


import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo {


    List<Player> findAll();

    List<Player> findPlayersWithPositionalParameter(Integer ageMin, Integer ageMax);

    List<Player> findPlayersUsingNamedParameters(Integer ageMin, Integer ageMax);

    List<Team> findTeamsUsingJoin();

    List<Team> findTeamsUsingFunction();

    List<Team> findTeamsUsingNamedQuery();

    List<Player> findAllUsingNativeQuery();

    Long countPlayersUsingStoredProcedure(long playerId);

}
