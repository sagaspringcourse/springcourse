package rs.saga.mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PlayerDAO {

	public Player findPlayer(int id) {
		
		Player person = new Player();
		Connection connection = this.getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(
				"select id, first_name, last_name, age from s_player where id = ?");
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		person.setPersonId(resultSet.getInt("id"));
		person.setFirstName(resultSet.getString("first_name"));
		person.setLastName(resultSet.getString("last_name"));
		person.setAge(resultSet.getInt("age"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return person;
	}

	private Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/saga", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}
}
