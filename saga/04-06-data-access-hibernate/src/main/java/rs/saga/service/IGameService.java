package rs.saga.service;


import rs.saga.domain.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
public interface IGameService {
    void playGame(Team home, Team away);

    Team update(Team home);
}
