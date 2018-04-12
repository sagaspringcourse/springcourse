package rs.saga.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.saga.domain.Player;
import rs.saga.domain.Team;

import java.util.List;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
public interface ISpringDataPlayerRepo extends JpaRepository<Player, Long> {

   List<Player> findAllByOrderByFirstNameAsc();

   @Query("select p from Player p where (p.age between 28 and 30) and p.firstName like '%ik%' order by p.firstName")
   List<Player> findAllPlayers();


   @Query("select p from Player p where (p.age between :minAge and :maxAge) and p.firstName like '%ik%' order by p.firstName")
   List<Player> findPlayersParameters(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);

   @Query("select distinct p.team from Player p join p.team group by p.team.name having count(*) > 2")
   List<Team> findTeams();

}
