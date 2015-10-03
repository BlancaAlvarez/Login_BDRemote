<?php
//Codigo
   require("Connection.php");

   $model = new Connection();
   $db = $model->getConnection();

   $query = "SELECT * FROM empresas";

   try {
       $stmt = $db->prepare($query);
       $result = $stmt->execute();
   } catch (PDOException $ex) {
       $response["success"] = 0;
       $response["message"] = "Problema con la base de datos, vuelve a intetarlo";
       die(json_encode($response));
   }

   if ($stmt->rowCount()) {
       $response["empresas"] = array();
	
       while(($row = $stmt->fetch())) {
           $product = array();
	   $product["id"] = $row["id"];
	   $product["nombre"] = $row["nombre"];
	   array_push($response["empresas"], $product);
       }
       $response["success"] = 1;
       $response["message"] = "Respuesta a Aplicacion Android";

       echo json_encode($response);
    } else {
        $response["success"] = 0;
        $response["message"] = "No products found";
 
	echo json_encode($response);
    }
?>
