package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.saga.domain.Team;
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
        System.out.println("Applying post processor");
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
    private void postGame() {
        System.out.println("Destroying GameService bean");
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
