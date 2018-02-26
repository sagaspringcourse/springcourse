package configurationmetadata;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.saga.businessobject.CrvenaZvezda;
import rs.saga.businessobject.ITeam;
import rs.saga.businessobject.IGame;
import rs.saga.businessobject.Partizan;
import rs.saga.businessobject.Game;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Configuration
public class GameConfig {

    @Bean
    public ITeam host() {
        return new CrvenaZvezda();
    }

    @Bean
    public ITeam guest() {
        return new Partizan();
    }

    @Bean
    public IGame game() {
        return new Game(host(), guest());
    }
}
