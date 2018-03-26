package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Player;

import java.sql.SQLException;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public interface SpringDataJPAPlayerRepo extends JpaRepository<Player, Long>{

    Set<Player> findByFirstName(String firstName) throws SQLException;

    Set<Player> findByFirstNameAndLastName(String firstName, String lastName);
}
