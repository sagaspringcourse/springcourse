package rs.saga.businessobject;

import java.util.Date;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-07
 */
public interface IRequest {
    Date getEndAt();

    String getPet();

    Date getStartAt();
}
