package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBconnection {
	private static Connection conn = null;
	
	static {
		conectar();
	}
	
	public DBconnection() {
		conectar();
	}
	
	private static Properties loadProps() {
		Properties props = new Properties();
		props.put("url", "jdbc:mysql://localhost:3306/ezzie");
		props.put("user", "root");
		props.put("password", "admin");
		props.put("useTimezone", "true");
		props.put("serverTimezone", "UTC");
		props.put("autoReconnect", "true");
		return props;
	}
	
	private static void conectar() {
		try {
			if(conn == null) {
				Properties props = loadProps();
				String url = props.getProperty("url");
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn = DriverManager.getConnection(url, props);
				conn.setAutoCommit(false);
			}
		}catch(Exception e) {
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
