package rs.saga.businessobject;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class Game implements IGame {
    private final ITeam host;
    private final ITeam guest;

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
}
