package rs.saga.dao;


import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo {

    List<Player> joinCriteria(String firstName, String teamName);

    List<Player> selectCriteria();

    List<Player> restrictCriteria();

    List<Player> paginationCriteria();
}
