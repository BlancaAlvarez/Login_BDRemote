<?php

    class Connection {
		
        public function getConnection() {
	    $host = "localhost";
	    $dbName = "u651189321_bdrem";
	    $root = "u651189321_fcfm";
	    $password = "cdis123";
			
	    try {
		$connect = new PDO("mysql:host=$host;dbname=$dbName;", $root, $password);
	    } catch (PDOException $ex) {
		die("Failed to connect to the database: " . $ex->getMessage());
            }
			
	    return $connect;
        }
    }
?>
