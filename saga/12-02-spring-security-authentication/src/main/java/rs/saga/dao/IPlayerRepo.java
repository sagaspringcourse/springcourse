package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.saga.domain.Player;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface IPlayerRepo extends JpaRepository<Player, Long> {

    Set<Player> findByFirstName(String firstName);

    Player findByCredentials_Username(String username);

    @Query("select r.roleName from Role r left join r.player p where  p.credentials.username = :userName")
    List<String> findPlayerRoles(@Param("userName") String userName);
}
