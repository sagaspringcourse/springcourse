package rs.saga.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.saga.domain.Team;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
public interface ITeamRepo extends JpaRepository<Team, Long> {

    Team findByName(String team);

}
