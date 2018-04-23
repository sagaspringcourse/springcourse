package rs.saga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import rs.saga.domain.Player;
import rs.saga.exception.NotFoundException;
import rs.saga.service.IPlayerService;

import java.security.Principal;


@Controller
@RequestMapping("/players")
@SessionAttributes("player")
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

	/**
	 * Handles requests to show detail about one user.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN') or #id == principal.id")
	// @PostAuthorize("#model['username'] == 'badjevic.m'")
	@RequestMapping(value = "/show/{id:[\\d]*}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model, Principal principal) throws NotFoundException {
		Player user = playerService.findById(id);
		if (user == null) {
			throw new NotFoundException(Player.class, id);
		}
		model.addAttribute("player", user);
		model.addAttribute("username", user.getCredentials().getUsername());
		String viewName = "players/show";
		if (principal.getName().equals(user.getCredentials().getUsername())) {
			viewName = "players/form";
		}
		return viewName;
	}

	/**
	 * Handles requests to delete  a user
	 */
	@RequestMapping(value = "/delete/{id:[\\d]*}", method = RequestMethod.GET)
	public String delete(@PathVariable Long id, Model model) throws NotFoundException {
		Player user = playerService.findById(id);
		if (user == null) {
			throw new NotFoundException(Player.class, id);
		}
		playerService.deleteById(id);
		model.addAttribute("confirmationMessage", "user.deleted");
		model.addAttribute("players", playerService.findAll());
		return "users/list";
	}

	/**
	 * Handles requests to save  a user
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String delete(@ModelAttribute Player player, Model model) {
		Player saved = playerService.save(player);
		return "players/form";
	}

}
