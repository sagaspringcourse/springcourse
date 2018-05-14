package rs.saga.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rs.saga.domain.Player;
import rs.saga.service.IPlayerService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-05-13
 */
@RequestMapping("/rest/players")
@RestController
public class RestPlayerController {

    private final IPlayerService playerService;

    @Autowired
    public RestPlayerController(IPlayerService playerService) {
        this.playerService = playerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{username}")
    public Player get(@PathVariable String username) {
        return playerService.findByUsername(username);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public Player create(@RequestBody Player player) {
        return playerService.save(player);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/edit")
    public Player edit(@RequestBody Player player) {
        return playerService.save(player);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{username}")
    public void delete(@PathVariable String username) {
        Player player = playerService.findByUsername(username);
        playerService.deleteById(player.getId());
    }
}
