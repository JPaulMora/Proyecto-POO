<?php 
	
	require 'database.php';

	if ( !empty($_GET)) {

		$producto=$_GET["producto"];
		$restaurante=$_GET["rest"];
		$hoy = date("Y-m-d H:i:s");
		$carnet=1;
		$precio=1;
		$valid=true;
		// insert data
		if ($valid) {
			$pdo = Database::connect();
			$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
			$sql = "INSERT INTO Transacciones (restaurante,producto,hoy) values(?, ?, ?,?,?)";
			$q = $pdo->prepare($sql);
			$q->execute(array($carnet,$restaurante,$precio, $hoy, $producto));
			header("Location: index.php");
		}
	}
?>
