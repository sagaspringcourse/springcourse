package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.saga.businessobject.ITeam;
import rs.saga.dao.ITeamRepo;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Service
public class Game implements IGame {

    private final ITeam host;
    private final ITeam guest;
    private ITeamRepo teamRepository;

    @Autowired
    public Game(ITeam host, ITeam guest, ITeamRepo teamRepository) {
        this.host = host;
        this.guest = guest;
        this.teamRepository = teamRepository;
    }

    @Override
    public ITeam play() {
        ITeam winner = null;
        if (Math.random() < 0.5) {
            winner = host;
        } else {
            winner = guest;
        }
        return winner;
    }

    @Override
    public ITeam update(ITeam team) {
        teamRepository.delete(team);
        return teamRepository.save(team);
    }


}
