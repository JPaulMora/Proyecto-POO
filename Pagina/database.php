<?php
class Database
{
    private static $dbName = 'id194073_ventas' ;
    private static $dbHost = 'localhost' ;
    private static $dbUsername = 'id194073_admin';
    private static $dbUserPassword = '12345';

    private static $cont  = null;

    public function __construct() {
        exit('Init function is not allowed');
    }

    public static function connect()
    {
        // One connection through whole application
        if ( null == self::$cont )
        {
            try
            {
                self::$cont =  new PDO( "mysql:host=".self::$dbHost.";"."dbname=".self::$dbName, self::$dbUsername, self::$dbUserPassword);
            }
            catch(PDOException $e)
            {
                die($e->getMessage());
            }
        }
        return self::$cont;
    }

    public static function disconnect()
    {
        self::$cont = null;
    }

    /**
     * @return null
     */
    public static function verify()
    {
        echo("Ejecutando...");
        try{
            echo("Trying...");

            $carnet=$_GET["carnet"];
            $pw=$_GET["pw"];

            $pdo = Database::connect();
            $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            $sql = "SELECT * FROM clientes WHERE  idcli='$carnet' AND passw='$pw'";
            $result=$pdo->query($sql);
            if(in_array($carnet, $result) and in_array($pw, $result)){
                console.log("login exitoso");
            } elseif (result==null){
                header("Location: index.php");
            }
        }catch (Exception $e){
            header("Location: index.php");
            console.log($e);
        }
    }
}
?>