package rs.saga.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyClass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-31
 */
@Table(name = "s_multiplayergame")
@Entity
public class MultiPlayerGame extends Game {

    @OneToMany
    private List<Player> players;


    @OneToOne
    @JoinColumn(name = "PLAYER_ID", referencedColumnName = "ID")
    private Player winner;

    public MultiPlayerGame() {
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
