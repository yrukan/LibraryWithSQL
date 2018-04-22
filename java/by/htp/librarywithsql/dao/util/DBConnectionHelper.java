package by.htp.librarywithsql.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnectionHelper {

	private DBConnectionHelper() {

	}

	public static Connection connect() {
		Connection connection = null;

		try {
			ResourceBundle rb = ResourceBundle.getBundle("db_config");

			String url = rb.getString("db.url");
			String login = rb.getString("db.login");
			String pass = rb.getString("db.pass");

			Class.forName(rb.getString("db.driver"));
			connection = DriverManager.getConnection(url, login, pass);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}
		return connection;
	}

	public static void disconnect(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}