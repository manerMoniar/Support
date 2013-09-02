<?php
	require_once('config.php');

	try{
		$json = array();

		$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
		
		$sql = $dbh->prepare ("SELECT u.id, nombre, direccion, COUNT(p.id) AS total, ROUND(AVG(puntos)) AS puntos from usuarios u
							INNER JOIN puntuacion p ON u.id = p.idUsuarioDestino
							WHERE Activo = 1 GROUP BY u.id  ORDER BY nombre LIMIT 10");
		
		$sql->execute();

	    /*** fetch the results ***/
	    $result = $sql->fetchAll();

	    /*** loop of the results ***/
	    foreach($result as $row)
        {
        	$puntos = 0;
        	if(!empty($row['puntos']))
        		$puntos = $row['puntos'];

	        $json[] = array('id'=>$row['id'], 'nombre'=>utf8_encode($row['nombre']), 
        					'direccion'=>utf8_encode($row['direccion']), 'puntos'=>$puntos,
        					'total'=>$row['total']);
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