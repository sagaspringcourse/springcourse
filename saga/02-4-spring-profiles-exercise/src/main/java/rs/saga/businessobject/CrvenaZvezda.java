package rs.saga.businessobject;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Component("host")
@Profile("production")
public class CrvenaZvezda implements ITeam {
    @Override
    public String name() {
        return "CrvenaZvezda";
    }
}
