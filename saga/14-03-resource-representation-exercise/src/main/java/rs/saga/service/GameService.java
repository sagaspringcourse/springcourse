package rs.saga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.dao.ITeamRepo;
import rs.saga.domain.Player;
import rs.saga.domain.Team;
import rs.saga.dto.GameDTO;
import rs.saga.dto.PlayerDTO;
import rs.saga.dto.TeamDTO;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Service
@Transactional
public class GameService implements IGameService {

    private ITeamRepo repo;
    private ITeamService teamService;

    @PostConstruct
    private void preGame() {
        System.out.println("Choosing side:");
    }

    @Override
    public GameDTO playGame(GameDTO game, Long attackedId) {
        PlayerDTO attacker = game.getHome().getPlayers().iterator().next();
        PlayerDTO defender = getDefender(game.getAway(), attackedId);

        if (Math.random() < 0.5) {
            game.getAway().getPlayers().add(attacker);
            game.getHome().getPlayers().remove(attacker);
        } else {
            game.getHome().getPlayers().add(defender);
            game.getAway().getPlayers().remove(defender);
        }

        TeamDTO players = game.getHome();
        game.setHome(game.getAway());
        game.setAway(players);
        game.setMsg(game.getHome().getPlayers().get(0).getName() + " plays");
        if (game.getHome().getPlayers().size() == 0) {
            game.setMsg(game.getAway().getName().toUpperCase() + " WINS!!!!");
        }
        if (game.getAway().getPlayers().size() == 1) {
            game.setMsg(game.getHome().getName().toUpperCase() + " WINS!!!!");
        }

        return game;
    }

    private PlayerDTO getDefender(TeamDTO away, final Long attackedId) {
        return away.getPlayers().stream().filter(playerDTO -> Objects.equals(playerDTO.getId(), attackedId)).findFirst().get();
    }

    @Override
    public GameDTO startGame() {
        Team zvezda = teamService.find("Crvena Zvezda");
        Team partizan = teamService.find("Partizan");
        GameDTO gameDTO = new GameDTO();
        gameDTO.setAway(convertToDto(zvezda));
        gameDTO.setHome(convertToDto(partizan));
        return gameDTO;
    }

    @Override
    public void playGame(Team home, Team away) {
        if (Math.random() < 0.5) {
            System.out.println(away.getName() + " won");
        } else {
            System.out.println(home.getName() + " won");
        }
    }

    @PreDestroy
    private void postGame() {
        System.out.println("End of the game");
    }

    // optional dependencies should use setter injection
    @Autowired
    public void setRepo(ITeamRepo repo) {
        this.repo = repo;
    }


    @Override
    public Team update(Team team) {
        repo.delete(team);
        Team updated = repo.save(team);
        return updated;
    }

    public TeamDTO convertToDto(Team team) {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName(team.getName());
        List<PlayerDTO> players = team.getPlayers().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        teamDTO.setPlayers(players);
        return teamDTO;
    }

    public PlayerDTO convertToDto(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getFirstName());
        playerDTO.setDisplayName(upperCaseFirstLetter(player.getFirstName()) + upperCaseFirstLetter(player.getLastName()));
        return playerDTO;
    }

    private String upperCaseFirstLetter(String string) {
        return String.valueOf(string.charAt(0)).toUpperCase();
    }

    @Autowired
    public void setTeamService(ITeamService teamService) {
        this.teamService = teamService;
    }
}
