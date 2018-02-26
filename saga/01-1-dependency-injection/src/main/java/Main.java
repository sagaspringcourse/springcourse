import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;
import rs.saga.businessobject.IGame;
import rs.saga.businessobject.Partizan;
import rs.saga.businessobject.Game;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
public class Main {

    public static void main(String[] args) {
        ITeam crvenaZvezda = new CrvenaZvezda();
        ITeam partizan = new Partizan();
        IGame game = new Game(crvenaZvezda, partizan);
        game.play();
    }
}
