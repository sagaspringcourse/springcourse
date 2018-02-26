package rs.saga.businessobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Scope("prototype")
@Component
public class Game implements IGame{

    private final ITeam host;
    private final ITeam guest;
    @Autowired
    private Referee referee;

    @Autowired
    public Game(ITeam host, ITeam guest) {
        this.host = host;
        this.guest = guest;
    }

    @Override
    public void play() {
        if (Math.random() < 0.5) {
            System.out.println(host.name() + " won ");
        } else {
            System.out.println(guest.name() + " won ");
        }
    }

    @Override
    public Referee getReferee() {
        return referee;
    }

}
