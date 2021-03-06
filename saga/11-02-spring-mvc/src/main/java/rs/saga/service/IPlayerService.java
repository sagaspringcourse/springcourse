package rs.saga.service;

import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-16
 */
public interface IPlayerService {
    List<Player> findAll();
    Player findPlayer(Long id);
}
