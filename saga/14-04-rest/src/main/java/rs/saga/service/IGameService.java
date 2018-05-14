package rs.saga.service;

import rs.saga.domain.Team;
import rs.saga.dto.GameDTO;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
public interface IGameService {
    GameDTO playGame(GameDTO game, Long attackedID);

    GameDTO startGame();

    void playGame(Team home, Team away);

    Team update(Team home);
}
