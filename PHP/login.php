<?php
	require_once('config.php');

	try{
		$json = array();

		if(isset($_POST['email'], $_POST['password'])){
			$email = $_POST['email'];
			$pass = $_POST['password'];

			$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
			
			$sql = $dbh->prepare ("SELECT u.id, nombre, direccion, email, telefono, COUNT(p.id) AS total, ROUND(AVG(puntos)) AS puntos from usuarios u
									LEFT JOIN puntuacion p ON u.id = p.idUsuarioDestino
									WHERE Activo = 1 AND email = :email AND contrasenia = :pass");
			$sql->bindParam(':email', $email, PDO::PARAM_STR);
			$sql->bindParam(':pass', $pass, PDO::PARAM_STR);
			$sql->execute();

		    /*** fetch the results ***/
		    $result = $sql->fetchAll();

		    if (count($result) == 1){
		    	foreach($result as $row)
        		{
        			if($row['id'] != null){
	        			$puntos = 0;

			        	if(!empty($row['puntos']))
			        		$puntos = $row['puntos'];

	        			$json[] = array('logStatus'=>1, 'id'=>$row['id'], 'nombre'=>utf8_encode($row['nombre']), 
			        					'direccion'=>utf8_encode($row['direccion']), 'puntos'=>$puntos, 'email'=>$row['email'], 
        								'telefono'=>$row['telefono'], 'total'=>$row['total']);	
        			}
        			else{
        				$json[] = array('logStatus'=>0);
        			}
        		}	
		    }
		    else
		    	$json[] = array('logStatus'=>0);

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