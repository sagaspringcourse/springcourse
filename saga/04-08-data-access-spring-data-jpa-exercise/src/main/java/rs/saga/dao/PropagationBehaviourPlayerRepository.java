package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.saga.domain.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public class PropagationBehaviourPlayerRepository implements IPropagationPlayerRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PropagationBehaviourPlayerRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public int save(Player player) {
        int sqlCode = 0;
        String insertSQL = "INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL) VALUES (" + player.getId() + ", '" + player.getFirstName() + "', '" + player.getLastName() + "', '" + player.getUsername() + "', '" + player.getPassword() + "', '" + player.getEmail() + "');";
        sqlCode = jdbcTemplate.update(insertSQL);
        return sqlCode;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int saveRequired(Player player) {
        return save(player);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public int saveRequiresNew(Player player) {
        return save(player);
    }

    @Transactional(propagation = Propagation.NEVER)
    @Override
    public int saveNever(Player player) {
        return save(player);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    @Override
    public int saveMandatory(Player player) {
        return save(player);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public int saveSupports(Player player) {
        return save(player);
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Override
    public int saveNotSupported(Player player) {
        return save(player);
    }

    @Override
    public Set<Player> findByFirstName(String firstName) {
        PlayerRowMapper playerRowMapper = new PlayerRowMapper();
        String findByName = "SELECT * FROM S_PLAYER WHERE FIRST_NAME = ?";
        List<Player> query = jdbcTemplate.query(findByName, playerRowMapper, firstName);
        return new HashSet<>(query);
    }

    class PlayerRowMapper implements RowMapper<Player> {
        public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
            Player player = new Player();
            // set internal entity identifier (primary key)
            player.setId(rs.getLong("ID"));
            player.setFirstName(rs.getString("FIRST_NAME"));
            player.setLastName(rs.getString("LAST_NAME"));
            player.setAddress(rs.getString("ADDRESS"));
            player.setUsername(rs.getString("USERNAME"));
            player.setEmail(rs.getString("EMAIL"));
            return player;
        }
    }

}
