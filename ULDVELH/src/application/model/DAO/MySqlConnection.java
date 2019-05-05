package application.model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	
	private static String url = "jdbc:mysql://sindinpierre.ddns.net:3306/uldvelh?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "application";
	private static String passwd = "application";

	private static Connection connect;
	
	
	public static Connection startConnection(){
	    if(connect == null){
		try {
		    connect = DriverManager.getConnection(url, user, passwd);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	    }
	    return connect;
	}
	
	public static void endConnection(){
	    if(connect != null){
	    	try {
				connect.close();
				connect=null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
	}

}
