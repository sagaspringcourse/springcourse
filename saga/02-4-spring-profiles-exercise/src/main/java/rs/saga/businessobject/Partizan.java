package rs.saga.businessobject;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-15
 */
@Component("guest")
public class Partizan implements ITeam {
    @Override
    public String name() {
        return "Partizan";
    }
}
