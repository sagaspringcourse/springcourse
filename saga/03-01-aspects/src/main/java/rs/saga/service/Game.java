package rs.saga.service;

import rs.saga.businessobject.ITeam;
import rs.saga.dao.ITeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Service("game")
public class Game implements IGame {

    private ITeam home;
    private ITeam away;
    private ITeamRepo repo;

    // mandatory dependencies use constructor injection
    @Autowired
    public Game(ITeam home, ITeam away) {
        this.home = home;
        this.away = away;
    }

    @PostConstruct
    private void preGame() {
        System.out.println("Choosing side:");
    }

    @Override
    public void playGame() {
        if (Math.random() < 0.5) {
            System.out.println(away.name() + " won");
        } else {
            System.out.println(home.name() + " won");
        }
    }

    @PreDestroy
    public void postGame() {
        System.out.println("End of the game");
    }

    // optional dependencies should use setter injection
    @Autowired
    public void setRepo(ITeamRepo repo) {
        this.repo = repo;
    }


    @Override
    public ITeam update(ITeam team) {
        repo.delete(team);
        ITeam updated = repo.save(team);
        return updated;
    }
}
