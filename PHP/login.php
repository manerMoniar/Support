<?php
	require_once('config.php');

	try{
		$json = array();

		if(isset($_POST['email'], $_POST['password'])){
			$email = $_POST['email'];
			$pass = $_POST['password'];

			$dbh = new PDO("mysql:host=$hostname;dbname=$database", $username, $password);
			
			$sql = $dbh->prepare ("SELECT email, contrasenia FROM usuarios WHERE activo = 1 AND email = :email AND contrasenia = :pass");
			$sql->bindParam(':email', $email, PDO::PARAM_STR);
			$sql->bindParam(':pass', $pass, PDO::PARAM_STR);
			$sql->execute();

		    /*** fetch the results ***/
		    $result = $sql->fetchAll();

		    if (count($result) == 1)
		    	$json[] = array('logStatus'=>1);
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