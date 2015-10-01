<?php
	require("Connection.php");
	
	$model = new Connection();
	$db = $model->getConnection();
	
	if (!empty($_POST)) {
		$query = "SELECT id, username, password 
					FROM users 
				  WHERE username = :username";
		try {
			$stmt = $db->prepare($query);
			$stmt->bindParam(':username', $_POST['username']);
			$result = $stmt->execute();
		} catch (PDOException $ex) {
			$response["success"] = 0;
			$response["message"] = "Problema con la base de datos, vuelve a intetarlo";
			die(json_encode($response));
		}

		if (($row = $stmt->fetch())) {
			if ($_POST['password'] === $row['password']) {
				$response["success"] = 1;
				$response["message"] = "Login correcto!";
				die(json_encode($response));
			} else {
				$response["success"] = 0;
				$response["message"] = "Login INCORRECTO";
				die(json_encode($response));
			}
		}
		
		// Close Connection
		$db = null;
	} else {
?>
		<h1>Login</h1> 
		<form action="LoginController.php" method="post"> 
			Username:<br /> 
			<input type="text" name="username" placeholder="username" /> 
			<br /><br /> 
			Password:<br /> 
			<input type="password" name="password" placeholder="password" value="" /> 
			<br /><br /> 
			<input type="submit" value="Login" /> 
		</form> 
		<a href="register.php">Register</a>
<?php
	}
?>