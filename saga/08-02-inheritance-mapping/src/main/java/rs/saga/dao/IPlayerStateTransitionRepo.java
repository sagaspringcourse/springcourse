package rs.saga.dao;


import rs.saga.domain.Player;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerStateTransitionRepo {

    Player get(Long playerId);

    void remove(Player nino);

    int save(Player player);

    Boolean isManaged(Player player);
}
