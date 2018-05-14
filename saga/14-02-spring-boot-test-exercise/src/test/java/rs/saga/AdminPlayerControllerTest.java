package rs.saga;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import rs.saga.builder.PlayerBuilder;
import rs.saga.service.IPlayerService;
import rs.saga.web.AdminPlayerController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AdminPlayerController.class, secure = false)
public class AdminPlayerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPlayerService service;

	@Test
	public void getPlayersList() throws Exception {
		when(service.findAll()).thenReturn(PlayerBuilder.getInstance().createPlayers());

		mockMvc.perform(MockMvcRequestBuilders.get("/admin/players/list/"))

				.andDo(MockMvcResultHandlers.print())

				.andExpect(status().isOk())
				.andExpect(view().name("players/list"));
	}

}