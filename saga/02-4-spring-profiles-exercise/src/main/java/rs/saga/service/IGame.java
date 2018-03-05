package rs.saga.service;

import rs.saga.businessobject.ITeam;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public interface IGame {
    ITeam play();

    ITeam update(ITeam team);
}
