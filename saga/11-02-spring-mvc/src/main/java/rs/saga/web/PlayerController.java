package rs.saga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import rs.saga.service.IPlayerService;


@Controller
@RequestMapping("/player")
public class PlayerController {

    private IPlayerService playerService;

    @Autowired
    public PlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(@RequestParam Long id, Model model) {
        model.addAttribute("player", playerService.findPlayer(id));
        return "player/hello";
    }

}
