package by.gsu.SignPositionService.client.db.postgresql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import by.gsu.SignPositionService.models.Sign;
import by.gsu.SignPositionService.models.Point;

public class DBClient {
	private Connection conn = null;

	public DBClient() {
		super();
		try {
			conn = PostgresqlConnection.newConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DBClient client = new DBClient();

		try {
			System.out.println(client.methodGetSign(3));

			client.methodPostSign(new Sign(1, "11", "111", new Point(20.3, 30.5)));
			System.out.println(client.getListSigns());

			client.methodDeleteSign(5);
			System.out.println(client.getListSigns());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setHost(String host) {
		// TODO Auto-generated method stub

	}

	public List<Sign> getListSigns() throws JsonParseException, JsonMappingException, IOException {

		List<Sign> signs = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sign");
			ResultSet result1 = preparedStatement.executeQuery();

			System.out.println("Выводим statement");
			while (result1.next()) {
				result1.getRow();
				Sign sign = new Sign(

						result1.getInt("id"), result1.getString("name"), result1.getString("description"),
						result1.getString("data"), new Point(result1.getDouble("x"), result1.getDouble("y")));
				signs.add(sign);

				System.out.println("Номер в выборке #" + "\t Номер в базе #" + result1.getInt("id") + "\t"
						+ result1.getString("name") + "\t" + result1.getString("description") + "\t"
						+ result1.getString("data")

				);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return signs;
	}

	public Sign methodGetSign(long id) throws JsonParseException, JsonMappingException, IOException {
		List<Sign> signs = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM sign");
			ResultSet result1 = preparedStatement.executeQuery();

			System.out.println("Выводим statement");
			while (result1.next()) {
				result1.getRow();
				Sign sign = new Sign(

						result1.getInt("id"), result1.getString("name"), result1.getString("description"),
						result1.getString("data"), new Point(result1.getDouble("x"), result1.getDouble("y")));

				System.out.println("Номер в выборке #" + "\t Номер в базе #" + result1.getInt("id") + "\t"
						+ result1.getString("name") + "\t" + result1.getString("description") + "\t"
						+ result1.getString("data"));

				return sign;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void methodPostSign(Sign sign) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("INSERT INTO sign ("
					// + "id,"
					+ "name," + "description," + "data," + "x," + "y" + ") values("
					// + "?,"
					+ "?," + "?," + "?," + "?," + "?" + ")");

			// preparedStatement.setInt(1, (int) sign.getId());
			preparedStatement.setString(1, sign.getName());
			preparedStatement.setString(2, sign.getDescription());
			preparedStatement.setString(3, sign.getData());
			preparedStatement.setString(4, "" + sign.getPoint().getX());
			preparedStatement.setString(5, "" + sign.getPoint().getY());

			// метод принимает значение без параметров
			// темже способом можно сделать и UPDATE
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void methodPutSign(Sign sign) {
	}

	public void methodDeleteSign(long id) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement("DELETE FROM sign WHERE id = ?");
			preparedStatement.setInt(1, (int) id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
