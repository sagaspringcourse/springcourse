package rs.saga.dao;


import rs.saga.domain.Player;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPropagationPlayerRepo {

    int save(Player player) throws SQLException;
    int saveRequired(Player player);
    int saveRequiresNew(Player player);
    int saveNever(Player player);
    int saveMandatory(Player player);
    int saveSupports(Player player);
    int saveNotSupported(Player player);
    Set<Player> findByFirstName(String firstName) throws SQLException;
}
