package rs.saga.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import rs.saga.builder.PlayerBuilder;
import rs.saga.domain.Player;
import rs.saga.dto.PlayerDTO;
import rs.saga.service.IPlayerService;

import static org.mockito.Mockito.when;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-05-13
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = RestPlayerController.class, secure = false)
public class RestPlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPlayerService playerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPlayer() throws Exception {
        when(playerService.findPlayer("slave")).thenReturn(PlayerDTO.convertToDto(PlayerBuilder.getInstance().slave().createPlayer()));

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players/slave"))

                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void create() throws Exception {
        Player slave = PlayerBuilder.getInstance().slave().createPlayer();
        when(playerService.save(slave)).thenReturn(slave);

        mockMvc.perform(MockMvcRequestBuilders.put("/rest/players/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(slave)))

                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void edit() throws Exception {
        Player slave = PlayerBuilder.getInstance().slave().createPlayer();
        when(playerService.save(slave)).thenReturn(slave);

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(slave)))

                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void delete() throws Exception {
        Player slave = PlayerBuilder.getInstance().slave().createPlayer();
        when(playerService.findByUsername("slave")).thenReturn(slave);

        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/players/delete/slave"))

                .andDo(MockMvcResultHandlers.print())

                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

}