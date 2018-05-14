package rs.saga.dto;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
public class TeamDTO {

    private List<PlayerDTO> players;
    private String name;

    public TeamDTO() {
    }

    public String getName() {
        return name;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }
}
