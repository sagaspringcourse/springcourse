package rs.saga.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import rs.saga.config.GameConfig;
import rs.saga.config.WebConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-04-19
 */
@ContextConfiguration(classes = {WebConfig.class, GameConfig.class})
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PlayerControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }


    @Test
    public void list() throws Exception {
        mvc.perform(get("/player?id=1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("player"))
                .andExpect(view().name("player/hello"))
                .andExpect(forwardedUrl("/WEB-INF/player/hello.jsp"));
    }

}