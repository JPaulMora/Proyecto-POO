package gui;

import java.sql.*;

import resources.DBinterface;

public class Ventas {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName(JDBC_DRIVER);
		DBinterface db = new DBinterface();
		db.connectDB("User1", "12345");
		//String r = db.queryDB("SELECT * FROM Clientes WHERE client_id = 1;");//"SELECT carnet FROM Clientes WHERE client_id = 2;");
		db.getBalance(15799, "UVG");
		System.out.println(db.getBalance(15799, "'UVG"));
		
		
		System.out.println(db.queryDB("SELECT * FROM Clientes WHERE institucion = 'UVG'"));
		db.closeDB();
	}

}