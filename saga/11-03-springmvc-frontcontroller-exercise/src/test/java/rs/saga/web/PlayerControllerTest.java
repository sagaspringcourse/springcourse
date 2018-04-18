package rs.saga.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;
import rs.saga.builder.PlayerBuilder;
import rs.saga.domain.Player;
import rs.saga.service.IPlayerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PlayerControllerTest {

    private PlayerController userController;

    @Before
    public void setUp(){
        userController = new PlayerController(new IPlayerService() {
            @Override
            public List<Player> findAll() {
                List<Player> players = new ArrayList<>();
                players.add(PlayerBuilder.getInstance().nino().createPlayer());
                return players;
            }
        });
    }


    @Test
    public void testFindAllHandler(){
        ExtendedModelMap model = new ExtendedModelMap();
        String viewName = userController.list(model);
        List<Player> players = (List<Player>) model.get("players");
        assertNotNull(players);
        assertEquals(1, players.size());
        assertEquals("Nikola", players.get(0).getFirstName());
        assertEquals("players/list", viewName);
    }
}
