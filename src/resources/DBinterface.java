package resources;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
public class DBinterface {
	private boolean connected = false; //Variable guarda el valor TRUE si el programa pudo acceder al servidor.
	private final String institucion = "UVG";
	
	//Variables necesarias para los comandos SQL. 
	String DB_URL = "jdbc:mysql://pproyectop.now.im/Ventas";//"jdbc:mysql://localhost/ventas";
	Connection c = null;
	Statement s = null;
	ResultSet r = null;

	/**
	 * 
	 * @param User: Usuario a ingresar sesion.
	 * @param Pass: Contrasena del usuario.
	 * @return Devuelve TRUE solo si el login fue correcto, cualquier error (de conexion, de SQL o de el usuario) devuelve FALSE.
	 * @throws SQLException
	 */
	public boolean connectDB(String User, String Pass) throws SQLException{
		System.out.println("Conectando a DB.");
		
		try{															//Caso 1: Login fue correcto.
			c = DriverManager.getConnection(DB_URL,User,Pass);
			System.out.println("Login correcto a DB.");
			connected = true;
			return true;
		}catch (com.mysql.jdbc.CommunicationsException e){				//Caso 2: El programa no puede conectarse al servidor.
			//
			System.out.println("No se pudo conectar a DB:"+e.toString());
			connected = false;
			return false;
		}catch( com.mysql.jdbc.exceptions.MySQLSyntaxErrorException e){ //Caso 3: Hubo un error en el comando SQL.
			System.out.println("Error en SQL: "+e.toString());
			connected = true;
			return false;
		}catch (java.sql.SQLException e){								//Caso 4: Login fue incorrecto;
			System.out.println("Credenciales incorrectas DB: "+e.toString());
			connected = true;
			return false;
		}
	}
	
	/**
	 * 
	 * @return Permite saber si la interfaz pudo accesar el servidor.
	 */
	public boolean getConnected(){
		return connected;
	}

	
	/**
	 * 
	 * @param carnet: carnet del usuario
	 * @param institucion: institucion en la que se debe buscar el carnet.
	 * @return Devuelve el balance del usuario requerido en forma de String, o un mensaje que indica que no fue encontrado.
	 * @throws SQLException
	 */
	public Double getBalance(int carnet) throws SQLException{
		
		//Se utiliza PreparedStatement porque este verifica que el comando sea valido para SQL, tambien es mas rapido.
		PreparedStatement ps = c.prepareStatement("SELECT balance FROM Clientes WHERE carnet = ? AND institucion = ?;");
		
		ps.setInt(1, carnet);// PreparedStatement reemplaza el primer "?" con la variable "carnet".
		ps.setString(2, institucion); //Lo mismo sucede con el segundo "?" pero con "institucion".
		
		r = ps.executeQuery();					//Envia el comando al servidor.
		r.next();								//el ResultSet (r) inicia "detras" de la primera columna, al llamar next() se mueve al primer dato.
		try{
			Double ans = r.getDouble(1);		//Como solo deberia haber un dato, este debe estar en la posicion 1 
												//y con getString lo convertimos al tipo de dato requerido
			return ans;
			
		}catch( java.sql.SQLException e){		//En caso no existe.. devolver este mensaje (0).
			return 00.0;
		}
		
	}
	
	/**
	 * 
	 * @param carnet Carnet o ID a cambiarle el credito.
	 * @param d Cantidad que representa el nuevo credito.
	 * @throws SQLException
	 */
	public void setBalance(int carnet, double d) throws SQLException{
		//Se utiliza PreparedStatement porque este verifica que el comando sea valido para SQL, tambien es mas rapido.
				PreparedStatement ps = c.prepareStatement("UPDATE `Clientes` SET `balance` = ? WHERE `carnet` = ?;");
				BigDecimal b = new BigDecimal(d, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				ps.setBigDecimal(1, b);
				ps.setInt(2, carnet);

				System.out.println("setBalance update returned "+ps.executeUpdate());
	}
	
	/**
	 *  Funcion registra una compra o gasto en la base de datos.
	 * @param carnet Carnet o ID del comprador
	 * @param total El total cargado al cliente.
	 * @throws SQLException
	 */
	public void regCompra(int carnet, double total) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Transacciones` (`tran_id`, `comprador`,`institucion`, `monto`, `fecha`) VALUES (NULL, ?, ?, ?, NULL);");
		
		BigDecimal b = new BigDecimal(total, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		ps.setInt(1, carnet);
		ps.setString(2, institucion);
		ps.setBigDecimal(3, b);
		//System.out.println(ps.toString());
		
		System.out.println("regCompra update returned "+ps.executeUpdate());
	}

	/**
	 * 
	 * @return Devuelve los Productos registrados en un TableModel para utilizar en un JTable.
	 * @throws SQLException
	 */
	public TableModel getProductos() throws SQLException{
		PreparedStatement ps = c.prepareStatement("select * from Productos;");
		r = ps.executeQuery();
		return DbUtils.resultSetToTableModel(r);
	}
	
	/**
	 * 
	 * @return Devuelve los Estudiantes registrados en un TableModel para utilizar en un JTable.
	 * @throws SQLException
	 */
	public TableModel getEstudiantes() throws SQLException{
		PreparedStatement ps = c.prepareStatement("select * from Clientes;");
		r = ps.executeQuery();
		return DbUtils.resultSetToTableModel(r);
	}
	
	
	public String[] getAsArray(int mode) throws SQLException{
		PreparedStatement ps = null;
		if (mode == 1){
			ps = c.prepareStatement("select nombres from Clientes;");
		}else if (mode == 0){
			ps = c.prepareStatement("select nombres from Empleados;");
		}
		System.out.println(ps.toString());
		
		r = ps.executeQuery();
		int rowcount = 0;
		while (r.next()) {
		  rowcount++;
		}
		String[] sts = new String[rowcount];
		r.absolute(1);
		
		for (int i=0; i < rowcount; i++){
			sts[i] = r.getString(1);
			r.next();
		}
		return sts;
	}
	/**
	 * 
	 * @return Devuelve los Empleados registrados en un TableModel para utilizar en un JTable.
	 * @throws SQLException
	 */
	public TableModel getEmpleados() throws SQLException{
		PreparedStatement ps = c.prepareStatement("select * from Empleados;");
		r = ps.executeQuery();
		return DbUtils.resultSetToTableModel(r);
	}
	
	/**
	 * 
	 * @param carnet El carnet del estudiante.
	 * @param nombres Nombre o nombres de la persona.
	 * @param apellidos Apelldo o Apelldos de la persona.
	 * @param balance Credito inical con el que se guarda este registro.
	 * @throws SQLException
	 */
	public void addEstudiante(int carnet, String nombres, String apellidos, double balance) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Clientes` (`carnet`, `institucion`, `nombres`, `apellidos`, `balance`) VALUES (?, ?, ?, ?, ?);");
		
		BigDecimal b = new BigDecimal(balance, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		ps.setInt(1, carnet);
		ps.setString(2, institucion);
		ps.setString(3, nombres);
		ps.setString(4, apellidos);
		ps.setBigDecimal(5, b);
		
		System.out.println("addEstudiante update returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param DPI: Numero de DPI del empleado a agregar a la base de datos.
	 * @param nombres Nombre o nombres de la persona.
	 * @param apellidos Apelldo o Apelldos de la persona.
	 * @throws SQLException 
	 */
	public void addEmpleado(long DPI, String nombres, String apellidos) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Empleados` (`nombres`, `apellidos`, `dpi`) VALUES (?, ?, ?);");
		ps.setString(1, nombres);
		ps.setString(2, apellidos);
		BigDecimal b = new BigDecimal(DPI, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		ps.setBigDecimal(3, b);
		
		System.out.println("addEmpleado update returned "+ps.executeUpdate());
	}
	/**
	 * Este metodo debe ser llamado al finaizar el programa para que se cierre la conexion a la DB.
	 * @throws SQLException
	 */
	public void closeDB() throws SQLException{
		//cerrar ResultSet y Statement en caso estuviesen abiertos.
		r.close();		
		s.close();
		
		//cerrar conexion.
		c.close();
		System.out.println("Desconectando DB.");
	}
}
