package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.saga.businessobject.ITeam;
import rs.saga.dao.ITeamRepo;

import javax.annotation.PostConstruct;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Service("game")
public class Game implements IGame {

    private ITeam home;
    private ITeam away;

    // mandatory dependencies use constructor injection
    @Autowired
    public Game(ITeam home, ITeam away) {
        this.home = home;
        this.away = away;
    }

    @Override
    public void playGame() {
        if (Math.random() < 0.5) {
            System.out.println(away.name() + " won");
        } else {
            System.out.println(home.name() + " won");
        }
    }

}
