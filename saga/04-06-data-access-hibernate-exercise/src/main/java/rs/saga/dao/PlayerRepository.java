package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import rs.saga.domain.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public class PlayerRepository implements IPlayerRepo {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public PlayerRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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

    @Override
    public Set<Player> findByFirstName(String firstName) {
        PlayerRowMapper playerRowMapper = new PlayerRowMapper();
        String findByName = "SELECT * FROM S_PLAYER WHERE FIRST_NAME = ?";
        List<Player> query = jdbcTemplate.query(findByName, playerRowMapper, firstName);
        return new HashSet<>(query);
    }

    @Override
    public Set<Player> findByFirstNameNamed(String firstName) {
        PlayerRowMapper playerRowMapper = new PlayerRowMapper();
        String findByName = "SELECT * FROM S_PLAYER WHERE FIRST_NAME = :firstName";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("firstName", firstName);
        List<Player> query = namedParameterJdbcTemplate.query(findByName, parameters, playerRowMapper);
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
