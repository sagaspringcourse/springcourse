package rs.saga.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import rs.saga.dto.GameDTO;
import rs.saga.service.IGameService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-24
 */
@CrossOrigin
@RequestMapping("/game")
@SessionAttributes(value= {"game"})
@RestController
public class GameController {

    private IGameService gameService;

    public GameController(IGameService gameService) {
        this.gameService = gameService;
    }

    @ModelAttribute("game")
    public void game(Model model) {
        GameDTO game = gameService.startGame();
        model.addAttribute("game", game);
    }

    @GetMapping("/start")
    public GameDTO loadGame(@ModelAttribute("game") GameDTO game) {
        game.setMsg(game.getHome().getPlayers().get(0).getName() + " plays");
        return game;
    }

    @GetMapping("/play/{attackedID}")
    public GameDTO play(@PathVariable Long attackedID, @ModelAttribute("game") GameDTO game, Model model) {
        GameDTO gameDTO =  gameService.playGame(game, attackedID);
        model.addAttribute("game", gameDTO);
        return gameDTO;
    }
}
