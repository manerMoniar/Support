<?php
	require_once('config.php');

	try{
		$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
		$json = array();

		$sql = "SELECT * FROM etiquetas ORDER BY nombre";
		foreach ($dbh->query($sql) as $row){
			$json[] = array('id'=>$row['id'], 'nombre'=>utf8_encode($row['nombre']));
		}
		
		echo json_encode($json); 

		/*** close the database connection ***/
		$dbh = null;
	}
	catch(PDOException $e)
	{
		echo $e->getMessage();
	}

?>