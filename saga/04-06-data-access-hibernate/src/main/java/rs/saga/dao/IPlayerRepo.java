package rs.saga.dao;


import rs.saga.domain.Player;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo {

    int save(Player player) throws SQLException;

    Set<Player> findByFirstName(String firstName) throws SQLException;
}
