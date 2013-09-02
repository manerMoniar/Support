<?php
	require_once('config.php');

	try{
		$json = array();

		if(isset($_POST['idUsuario'])){
			$idUsuario = $_POST['idUsuario'];
			$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
			
			$sql = $dbh->prepare ("SELECT usuarios.id, nombre, direccion, email, telefono, COUNT(puntuacion.id) AS total, ROUND(AVG(puntos)) AS puntos from usuarios 
									INNER JOIN puntuacion ON usuarios.id = puntuacion.idUsuarioDestino
									WHERE Activo = 1 and usuarios.id = :idUsuario");
			$sql->bindParam(':idUsuario', $idUsuario, PDO::PARAM_INT);
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