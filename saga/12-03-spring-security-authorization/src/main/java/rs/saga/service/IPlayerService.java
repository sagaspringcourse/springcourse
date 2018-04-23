package rs.saga.service;

import rs.saga.domain.Player;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-16
 */
public interface IPlayerService {
    void deleteById(Long id);

    List<Player> findAll();

    Player findById(Long id);

    Player findByUsername(String userName);

    List<String> findPlayerRoles(String userName);
}
