<?php
    require("Connection.php");
	
    $model = new Connection();
    $db = $model->getConnection();

    if (!empty($_POST)) {

        if (empty($_POST['username']) || empty($_POST['password'])) {
	    $response["success"] = 0;
	    $response["message"] = "Por favor entre el usuairo y el password";
	    die(json_encode($response));
	}
		
	$query = " SELECT 1 FROM users WHERE username = :user";

	try {
	    $stmt = $db->prepare($query);
	    $stmt->bindParam(':user', $_POST['username']);
	    $result = $stmt->execute();
	} catch (PDOException $ex) {
	    $response["success"] = 0;
	    $response["message"] = "Database Error1. Please Try Again!";
	    die(json_encode($response));
	}

	if ($row = $stmt->fetch()) {
	    $response["success"] = 0;
	    $response["message"] = "Lo siento el usuario ya existe";
	    die(json_encode($response));
	}

	$query = "INSERT INTO users ( username, password ) VALUES ( :user, :pass ) ";

	try {
	    $stmt = $db->prepare($query);
	    $stmt->bindParam(':user', $_POST['username']);
	    $stmt->bindParam(':pass', $_POST['password']);
	    $result = $stmt->execute();
	} catch (PDOException $ex) {
	    $response["success"] = 0;
	    $response["message"] = "Error base de datos2. Porfavor vuelve a intentarlo";
	    die(json_encode($response));
	}

	$response["success"] = 1;
	$response["message"] = "El usuario se ha agregado correctamente";
	echo json_encode($response);

    } else {
?>
	<h1>Register</h1> 
	<form action="RegisterController.php" method="post"> 
	    Username:<br /> 
	    <input type="text" name="username" value="" /> 
	    <br /><br /> 
	    Password:<br /> 
	    <input type="password" name="password" value="" /> 
	    <br /><br /> 
	    <input type="submit" value="Register New User" /> 
	</form>
<?php
    }
?>
