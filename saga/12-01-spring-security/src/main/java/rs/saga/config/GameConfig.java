package rs.saga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import rs.saga.dao.ITeamRepo;
import rs.saga.service.GameService;
import rs.saga.service.IGameService;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Configuration
@ComponentScan(basePackages = {"rs.saga.service"})
@Import(DataSourceConfig.class)
@EnableJpaRepositories(basePackages = {"rs.saga.dao"})
public class GameConfig {

    @Bean
    public IGameService gameService(ITeamRepo teamRepo) {
        GameService gameService = new GameService();
        gameService.setRepo(teamRepo);
        return gameService;
    }

}
