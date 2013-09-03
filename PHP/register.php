<?php
	require_once('config.php');

	try{
		$json = array();

		if(isset($_POST['email'], $_POST['password'], $_POST['name'], $_POST['address'], $_POST['telephone'])){
			$email = $_POST['email'];
			$pass = $_POST['password'];
			$name = $_POST['name'];
			$address = $_POST['address'];
			$telephone = $_POST['telephone'];

			$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
			
			$sql = $dbh->prepare ("INSERT INTO usuarios (email, contrasenia, nombre, direccion, telefono) VALUES (:email, :pass, :name, :address, :telephone)");
			$sql->bindParam(':email', $email, PDO::PARAM_STR);
			$sql->bindParam(':pass', $pass, PDO::PARAM_STR);
			$sql->bindParam(':name', $name, PDO::PARAM_STR);
			$sql->bindParam(':address', $address, PDO::PARAM_STR);
			$sql->bindParam(':telephone', $telephone, PDO::PARAM_STR);
			$response = $sql->execute();

		    if($response)
		    	$json[] = array('registerStatus'=>1);
		    else
		    	$json[] = array('registerStatus'=>0);

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