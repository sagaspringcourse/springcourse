package rs.saga.businessobject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Component
public class Game implements IGame {

    private final ITeam host;
    private final ITeam guest;
    private DataSource dataSource;

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

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
