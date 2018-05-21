package rs.saga.dto;

import rs.saga.domain.Team;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
public class TeamDTO {

    private List<PlayerDTO> players;
    private String name;

    public TeamDTO() {
    }

    public TeamDTO convertToDto(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(team.getName());
        List<PlayerDTO> players = team.getPlayers().stream()
                .map(PlayerDTO::convertToDto)
                .collect(Collectors.toList());
        teamDTO.setPlayers(players);
        return teamDTO;
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
