package rs.saga.domain.entitymapping.onetoonebidirectional;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-23
 */
public interface ICredentialsRepo {

    void persist(Credential credential);
}
