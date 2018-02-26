package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rs.saga.businessobject.ITeam;
import rs.saga.dao.ITeamRepo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

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
    public Game(@Qualifier("crvenaZvezda") ITeam host, @Qualifier("partizan") ITeam guest, ITeamRepo teamRepository) {
        this.host = host;
        this.guest = guest;
        this.teamRepository = teamRepository;
    }

    @PostConstruct
    public void start() {
        System.out.println("choosing side");
    }

    @Override
    public void play() {
        if (Math.random() < 0.5) {
            System.out.println(host.name() + " won ");
        } else {
            System.out.println(guest.name() + " won ");
        }
    }

    @PreDestroy
    public void endGame() {
        System.out.println("closing");
    }

}
