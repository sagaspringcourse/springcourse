package rs.saga.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.saga.dto.GameDTO;
import rs.saga.service.IGameService;

import javax.servlet.http.HttpSession;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
@CrossOrigin
@RequestMapping("/game")
@RestController
public class GameController {

    private IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/start")
    public GameDTO loadGame() {
        GameDTO game = gameService.startGame();
        game.setMsg(game.getHome().getPlayers().get(0).getName() + " plays");
        return game;
    }

    @PostMapping(value = "/play/{attackedID}")
    public GameDTO play(@PathVariable Long attackedID, @RequestBody GameDTO game, Model model, HttpSession session) {
        GameDTO gameDTO =  gameService.playGame(game, attackedID);
        return gameDTO;
    }
}
