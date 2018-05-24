package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseService {
	private String username;
	private String password;
	private String server;
	private String port;
	private Connection conn;
	
	public DatabaseService(String username, String password, String server, String port) {
		super();
		this.username = username;
		this.password = password;
		this.server = server;
		this.port = port;
		this.conn = buildConnection();
	}

	public Connection buildConnection() {
		Connection conn = null;
		String dbPath = "jdbc:oracle:thin:@" + this.server + ":" + this.port + ":XE";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(dbPath, this.username, this.password);
			
			System.out.println("DatabaseService:  Connection Successful");
		} catch (Exception e) {
			System.out.println("DatabaseService:  Failed to connect to database.");
			e.printStackTrace();
		}
		return conn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public Connection getConnection() {
		return conn;
	}

	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}

