package rs.saga.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import rs.saga.businessobject.Player;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:slavisa.avramovic@escriba.de">avramovics</a>
 * @since 2018-03-15
 */
@Repository
public class PlayerRepository implements IPlayerRepo {

    private DataSource dataSource;

    @Autowired
    public PlayerRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public int save(Player player) {
        int sqlCode = 0;
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            String insertSQL = "INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, USERNAME, PASSWORD, EMAIL) VALUES (" + player.getId() + ", '" + player.getFirstName() + "', '" + player.getLastName() + "', '" + player.getUsername() + "', '" + player.getPassword() + "', '" + player.getEmail() + "');";
            sqlCode = statement.executeUpdate(insertSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sqlCode;
    }

    @Override
    public Set<Player> findByFirstName(String firstName) throws SQLException {
        Connection connection = dataSource.getConnection();
        String findByName = "SELECT * FROM S_PLAYER WHERE FIRST_NAME = ?";
        PreparedStatement statement = connection.prepareStatement(findByName);
        statement.setString(1, firstName);
        ResultSet resultSet = statement.executeQuery();
        return mapPlayers(resultSet);
    }

    private Set<Player> mapPlayers(ResultSet rs) throws SQLException {
        Set<Player> players = new HashSet<>();
        Player player = null;
        while (rs.next()) {
            player = new Player();
            // set internal entity identifier (primary key)
            player.setId(rs.getLong("ID"));
            player.setFirstName(rs.getString("FIRST_NAME"));
            player.setLastName(rs.getString("LAST_NAME"));
            player.setAddress(rs.getString("ADDRESS"));
            player.setUsername(rs.getString("USERNAME"));
            player.setEmail(rs.getString("EMAIL"));
            players.add(player);
        }
        return players;
    }




}
