package rs.saga.businessobject;

import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-02-26
 */
@Component("home")
public class Partizan implements ITeam {

    public String name() {
        return "Partizan";
    }
}
