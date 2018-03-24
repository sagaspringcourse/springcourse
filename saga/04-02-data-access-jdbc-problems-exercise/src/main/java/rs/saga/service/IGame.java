package rs.saga.service;

import rs.saga.businessobject.ITeam;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
public interface IGame {
    void playGame();

    ITeam update(ITeam home);
}
