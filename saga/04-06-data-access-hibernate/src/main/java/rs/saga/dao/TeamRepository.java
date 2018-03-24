package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import rs.saga.builder.TeamBuilder;
import rs.saga.domain.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-05
 */
@Repository
public class TeamRepository implements ITeamRepo {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TeamRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public void delete(Team team) throws TeamNotFoundException {
        TeamRowMapper<Team> rowMapper = new TeamRowMapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", team.getName());
        try {
            namedParameterJdbcTemplate.queryForObject("SELECT * FROM s_team WHERE name = :name", params, rowMapper);
        } catch (EmptyResultDataAccessException erdae) {
           throw new TeamNotFoundException();
        }
        namedParameterJdbcTemplate.update("DELETE FROM s_team WHERE name = :name", params);
    }

    @Override
    public Team save(Team team) {
        Team saved = null;
        TeamRowMapper<Team> rowMapper = new TeamRowMapper<>();
        Map<String, Object> params = new HashMap<>();
        params.put("name", team.getName());
        try {
            saved = namedParameterJdbcTemplate.queryForObject("SELECT * FROM s_team WHERE name = :name", params, rowMapper);
        } catch (EmptyResultDataAccessException erdae) {
            // ignore for now
        }
        if (saved == null) {
            namedParameterJdbcTemplate.update("INSERT INTO s_team(NAME) VALUES(:name)", params);
            saved = namedParameterJdbcTemplate.queryForObject("SELECT * FROM s_team WHERE name = :name", params, rowMapper);
        }
        return saved;
    }

    static class TeamRowMapper<T> implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            TeamBuilder teamBuilder = new TeamBuilder();
            teamBuilder.setName(rs.getString("NAME"));
            teamBuilder.setId(rs.getLong("ID"));
            return teamBuilder.createTeam();
        }
    }
}
