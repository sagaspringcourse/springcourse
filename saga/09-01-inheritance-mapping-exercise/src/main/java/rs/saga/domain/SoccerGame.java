package rs.saga.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-31
 */
@Entity
@Table(name = "s_soccer")
public class SoccerGame extends Game {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "HOME_TEAM_ID", referencedColumnName = "ID")
    private Team home;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AWAY_TEAM_ID", referencedColumnName = "ID")
    private Team away;

    @OneToOne
    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    private Team winner;

    public SoccerGame() {
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}
