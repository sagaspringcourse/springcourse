package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface ISpringDataPlayerRepo extends JpaRepository<Player, Long> {

   List<Team> findTeamWithPlayer(String firstName);

}
