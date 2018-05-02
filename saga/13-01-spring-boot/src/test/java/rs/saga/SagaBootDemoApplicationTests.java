package rs.saga;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.builder.PlayerBuilder;
import rs.saga.dao.IPlayerRepo;
import rs.saga.domain.Player;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SagaBootDemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private IPlayerRepo playerRepo;

	@Before
	public void setUp() throws Exception {
		assertNotNull(playerRepo);
	}

	@Test
	public void save() throws Exception {
		Player nino = PlayerBuilder.getInstance().nino().createPlayer();
		Player player = playerRepo.save(nino);
		assertNotNull(player.getId());
	}

	@Test
	public void findByFirstName() throws Exception {
		playerRepo.save(PlayerBuilder.getInstance().nino().createPlayer());
		Set<Player> players = playerRepo.findByFirstName("Nikola");
		assertEquals(1, players.size());
	}

}
