package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.saga.businessobject.Team;
import rs.saga.dao.ITeamRepo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Service("game")
public class GameService implements IGameService {

    private ITeamRepo repo;

    @PostConstruct
    private void preGame() {
        System.out.println("Choosing side:");
    }

    @Override
    public void playGame(Team home, Team away) {
        if (Math.random() < 0.5) {
            System.out.println(away.getName() + " won");
        } else {
            System.out.println(home.getName() + " won");
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
    public Team update(Team team) {
        repo.delete(team);
        Team updated = repo.save(team);
        return updated;
    }
}
