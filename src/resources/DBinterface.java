package resources;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.*;

import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;
public class DBinterface {
	private boolean connected = false; //Variable guarda el valor TRUE si el programa pudo acceder al servidor.
	private final String institucion = "UVG";
	
	
	String DB_URL = "jdbc:mysql://sql9.freemysqlhosting.net/sql9145095";
	Connection c = null;
	Statement s = null;
	ResultSet r = null;

	/**
	 * Clase dedicada solamente a interactuar con la base de datos, esto previene errores en el input del usuario y facilita el corregir errores.
	 * @param User: Usuario a ingresar sesion.
	 * @param Pass: Contrasena del usuario.
	 * @return Devuelve TRUE solo si el login fue correcto, cualquier error (de conexion, de SQL o de el usuario) devuelve FALSE.
	 * @throws SQLException En caso no se puede conectar con la DB.
	 */
	//TODO connectDB()
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
	 * @return Devuelve el balance del usuario requerido en forma de String, o un mensaje que indica que no fue encontrado.
	 * @throws SQLException En caso no se puede obtener el balance del estudiante.
	 */
	//TODO getBalance
	public Double getBalance(int carnet) throws SQLException{
		
		//Se utiliza PreparedStatement porque este verifica que el comando sea valido para SQL, tambien es mas rapido.
		PreparedStatement ps = c.prepareStatement("SELECT balance FROM Estudiantes WHERE carnet = ? AND institucion = ?;");
		
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
	 * @throws SQLException En caso no se pudo modificar el balance de un estudiante.
	 */
	//TODO setBalance
	public void setBalance(int carnet, double d) throws SQLException{
		//Se utiliza PreparedStatement porque este verifica que el comando sea valido para SQL, tambien es mas rapido.
				PreparedStatement ps = c.prepareStatement("UPDATE `Estudiantes` SET `balance` = ? WHERE `carnet` = ?;");
				BigDecimal b = new BigDecimal(d, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
				ps.setBigDecimal(1, b);
				ps.setInt(2, carnet);

				System.out.println("setBalance update returned "+ps.executeUpdate());
	}
	
	/**
	 *  Funcion registra una compra o gasto en la base de datos.
	 * @param carnet Carnet o ID del comprador.
	 * @param dpi el DPI del vendedor que registra la compra.
	 * @param total El total cargado al cliente.
	 * @throws SQLException En caso no se pudo registrar la compra.
	 */
	//TODO regCompra
	public void regCompra(int carnet, double total,double dpi) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Transacciones` (`tran_id`, `comprador`,`vendedor`,`institucion`, `monto`, `fecha`) VALUES (NULL, ?, ?, ?, ?, NULL);");
		
		BigDecimal b = new BigDecimal(total, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		BigDecimal d = new BigDecimal(dpi, MathContext.DECIMAL32).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		ps.setInt(1, carnet);
		ps.setBigDecimal(2, d);
		ps.setString(3, institucion);
		ps.setBigDecimal(4, b);
		
		System.out.println("regCompra update returned "+ps.executeUpdate());
	}

	/**
	 * 
	 * @param carnet: el carnet de la persona a revisar.
	 * @return Devuelve un TableModel con las compras hechas por el cliente.
	 * @throws SQLException En caso no se pudo obtener las compras del cliente.
	 */
	//TODO getComprasPorCliente
	public TableModel getComprasPorCliente(int carnet) throws SQLException{
		PreparedStatement ps = c.prepareStatement("SELECT tran_id,monto FROM `Transacciones` WHERE comprador = ?;");
		ps.setInt(1, carnet);
		r = ps.executeQuery();
		
		return setNotEditable(DbUtils.resultSetToTableModel(r));
	}
	
	/**
	 * 
	 * @param dpi el DPI de la persona a revisar.
	 * @return Devuelve un TableModel con las ventas hechas por el empleado.
	 * @throws SQLException En caso no se pudo obtener las ventas del empleado.
	 */
	//TODO getVentasPorEmp
	public TableModel getVentasPorEmp(double dpi) throws SQLException{
		PreparedStatement ps = c.prepareStatement("SELECT tran_id,monto FROM `Transacciones` WHERE vendedor = ?;");
		BigDecimal d = new BigDecimal(dpi, MathContext.DECIMAL32).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		ps.setBigDecimal(1, d);
		r = ps.executeQuery();
		return setNotEditable(DbUtils.resultSetToTableModel(r));
	}
	
	/**
	 * @param Item Puede ser "Empleados", "Usuarios" o "Productos".
	 * @return Devuelve los Estudiantes, Empleados o Productos segun lo que diga Item registrados en un TableModel para utilizar en un JTable.
	 * @throws SQLException En caso no se pudo obtener los "items".
	 */
	//TODO getItems
	public TableModel getItems(String Item) throws SQLException{
		PreparedStatement ps = c.prepareStatement("select * from "+Item+";");
		r = ps.executeQuery();
		return setNotEditable(DbUtils.resultSetToTableModel(r));
	}

	/**
	 * 
	 * @param mode: Mode 1 = estudiantes, Mode 0 = Empleados.
	 * @return Devuelve un array de "items" en forma reconocible, que se usa en un JComboBox
	 * @throws SQLException En caso no se pudo obtener los "items".
	 */
	//TODO getAsArray
	public String[] getAsArray(int mode) throws SQLException{
		PreparedStatement ps = null;
		if (mode == 1){
			ps = c.prepareStatement("select nombres,carnet from Estudiantes;");
		}else if (mode == 0){
			ps = c.prepareStatement("select nombres,DPI from Empleados;");
		}else if (mode == 2){
			ps = c.prepareStatement("select id,producto from Productos;");
		}
		r = ps.executeQuery();
		int rowcount = 0;
		while (r.next()) {
		  rowcount++;
		}
		String[] sts = new String[rowcount];
		r.absolute(1);
		
		for (int i=0; i < rowcount; i++){
			sts[i] = r.getString(1)+" "+r.getString(2);
			r.next();
		}
		return sts;
	}
	
	
	/**
	 * 
	 * @param carnet El carnet del estudiante.
	 * @param nombres Nombre o nombres de la persona.
	 * @param apellidos Apelldo o Apelldos de la persona.
	 * @param balance Credito inical con el que se guarda este registro.
	 * @throws SQLException En caso no se pudo agregar el estudiante.
	 */
	//TODO addEstudiante
	public void addEstudiante(int carnet, String nombres, String apellidos, double balance) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Estudiantes` (`carnet`, `institucion`, `nombres`, `apellidos`, `balance`) VALUES (?, ?, ?, ?, ?);");
		
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
	 * @throws SQLException En caso no se pudo agregar el empleado.
	 */
	//TODO addEmpleado
	public void addEmpleado(long DPI, String nombres, String apellidos) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Empleados` (`nombres`, `apellidos`, `dpi`) VALUES (?, ?, ?);");
		ps.setString(1, nombres);
		ps.setString(2, apellidos);
		BigDecimal b = new BigDecimal(DPI, MathContext.DECIMAL32).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		ps.setBigDecimal(3, b);
		
		System.out.println("addEmpleado update returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param producto Nombre del producto.
	 * @param descripcion Descripcion del producto.
	 * @param precio Valor de pago por producto.
	 * @throws SQLException En caso no se pudo agregar el producto.
	 */
	//TODO addProducto
	public void addProducto(String producto, String descripcion, double precio) throws SQLException{
		PreparedStatement ps = c.prepareStatement("INSERT INTO `Productos` (`producto`, `descripcion`, `precio`) VALUES (?, ?, ?);");
		
		BigDecimal c = new BigDecimal(precio, MathContext.DECIMAL32).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		ps.setString(1, producto);
		ps.setString(2, descripcion);
		ps.setBigDecimal(3, c);
		
		System.out.println("addProducto update returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param carnet: Carnet del estudiante a eliminar.
	 * @throws SQLException En caso no se pudo eliminar el estudiante.
	 */
	//TODO delEstudiante
	public void delEstudiante(int carnet) throws SQLException{
		PreparedStatement ps = c.prepareStatement("DELETE FROM `Estudiantes` WHERE `carnet` = ?;");
		ps.setInt(1, carnet);
		System.out.println("delEstudiante returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param DPI: DPI de la persona a eliminar.
	 * @throws SQLException En caso no se pudo eliminar el empleado.
	 */
	//TODO delEmpleado
	public void delEmpleado(double DPI) throws SQLException {
		PreparedStatement ps = c.prepareStatement("DELETE FROM `Empleados` WHERE `DPI` = ?;");
		BigDecimal b = new BigDecimal(DPI, MathContext.DECIMAL32).setScale(0, BigDecimal.ROUND_HALF_EVEN);
		ps.setBigDecimal(1, b);
		System.out.println("delEmpleado returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param name: Carnet del estudiante a eliminar.
	 * @throws SQLException En caso no se pudo eliminar el producto.
	 */
	//TODO delEstudiante
	public void delProducto(String name) throws SQLException{
		PreparedStatement ps = c.prepareStatement("DELETE FROM `Productos` WHERE `producto` = ?;");
		ps.setString(1, name);
		System.out.println("delProducto returned "+ps.executeUpdate());
	}
	
	/**
	 * 
	 * @param m TableModel con la informacion y formato requerido
	 * @return Devuelve una copia del TableModel recibido pero que no permite edicion.
	 */
	//TODO setNotEditable
	public TableModel setNotEditable(TableModel m){
		return new NotEditableModel(m);
	}
	
	/**
	 * Este metodo debe ser llamado al finaizar el programa para que se cierre la conexion a la DB.
	 * @throws SQLException En caso no se pudo cerrar la coneccion, usualmente significa que se perdio en algun momento.
	 */
	//TODO closeDB
	public void closeDB() throws SQLException{
		//cerrar ResultSet y Statement en caso estuviesen abiertos.
		if(r != null){
			r.close();
		}
		if (s != null){
			s.close();
		}
		
		//cerrar conexion.
		c.close();
		System.out.println("Desconectando DB.");
	}

	
}
