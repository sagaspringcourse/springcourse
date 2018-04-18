package rs.saga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rs.saga.service.IPlayerService;


@Controller
@RequestMapping("/players")
public class PlayerController {

	private IPlayerService playerService;

	@Autowired
	public PlayerController(IPlayerService playerService) {
		this.playerService = playerService;
	}

	public void setPlayerService(IPlayerService playerService) {
		this.playerService = playerService;
	}

	/**
	 * Handles requests to list all users.
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("players", playerService.findAll());
		return "players/list";
	}

}
