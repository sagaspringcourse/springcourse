package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-01-28
 */
@Repository
public class TeamRepository implements ITeamRepo {

    private DataSource dataSource;

    @Autowired
    public TeamRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
