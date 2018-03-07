package rs.saga.businessobject;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Service
public class Game implements IGame {

    public Game() {
        System.out.println("instantiating game");
    }

    @PostConstruct
    public void start() {
        System.out.println("init game");
    }


    @Override
    public void play() {
        System.out.println("play game");
    }

    @PreDestroy
    public void endGame() {
        System.out.println("destroy game");
    }
}
