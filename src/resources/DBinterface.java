package resources;

import java.sql.*;
public class DBinterface {
	
	String DB_URL = "jdbc:mysql://localhost/ventas";
	Connection c = null;
	Statement s = null;
	ResultSet r = null;
	
	public DBinterface(){
		
	}
	
	public boolean connectDB(String User, String Pass) throws SQLException{
		System.out.println("Conectando a DB.");
		
		try{
			c = DriverManager.getConnection(DB_URL,User,Pass);
			System.out.println("Login correcto a DB.");
			return true;
		}catch( com.mysql.jdbc.exceptions.MySQLSyntaxErrorException e){
			System.out.println("Error conectando a la DB.");
			return false;
		}catch (java.sql.SQLException e){
			System.out.println("Credenciales incorrectas DB.");
			return false;
		}
	}

	public String queryDB(String sql) throws SQLException{
		s = c.createStatement();
		r = s.executeQuery(sql);
		String out = "";
		
		while(r.next()){
			out +=r.getString(6)+"\n";
		}
		r.close();
		s.close();
		return out;
	}
	
	public String getBalance(int carnet, String institucion) throws SQLException{
		
		PreparedStatement ps = c.prepareStatement("SELECT balance FROM Clientes WHERE carnet = ? AND institucion = ?;");
		//c.createStatement().executeQuery("SELECT balance FROM ")
		ps.setInt(1, carnet);
		ps.setString(2, institucion);
		
		//System.out.println(ps.toString());    //debug
		
		r = ps.executeQuery();
		r.next();								//cursor inicia "detras" de la primera fila, al llamar next() se mueve al primer dato.
		try{
			String ans = r.getString(1);
			return ans;
		}catch( java.sql.SQLException e){
			return "No Encontrado";
		}
		
	}
	
	public void closeDB() throws SQLException{
		//cerrar ResultSet y Statement en caso estuviesen abiertos.
		r.close();		
		s.close();
		
		//cerrar conexion.
		c.close();
	}
}
