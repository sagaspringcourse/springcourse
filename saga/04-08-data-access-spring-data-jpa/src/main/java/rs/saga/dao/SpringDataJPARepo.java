package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import rs.saga.domain.Player;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface SpringDataJPARepo  extends JpaRepository<Player, Long>{

    Set<Player> findByFirstName(String firstName) throws SQLException;

    Set<Player> findByFirstNameAndLastName(String nikola, String stankvic);
}
