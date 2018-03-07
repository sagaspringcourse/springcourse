package rs.saga.businessobject;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-30
 */
@Component("ref")
public class Referee {

    private String name;
    private String dateTimeString = LocalDateTime.now().toString();

    public Referee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateTime() {
        return dateTimeString;
    }

    @Override
    public String toString() {
        return "Referee{" +
                "name='" + name + '\'' +
                ", dateTimeString='" + dateTimeString + '\'' +
                '}';
    }
}
