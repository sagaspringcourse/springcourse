package rs.saga.dto;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
public class GameDTO {

    private TeamDTO home;
    private TeamDTO away;
    private String msg;

    public GameDTO() {
    }

    public TeamDTO getHome() {
        return home;
    }

    public void setHome(TeamDTO home) {
        this.home = home;
    }

    public TeamDTO getAway() {
        return away;
    }

    public void setAway(TeamDTO away) {
        this.away = away;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
