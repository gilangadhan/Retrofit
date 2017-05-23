<?php
	require_once 'include/DB_Function.php';
	

	$db =  new DB_Function();
	
	$response = array("error" => FALSE);
	
	if(isset($_GET['email']) && isset($_GET['password'])){
		$email = $_GET['email'];
		$password = $_GET['password'];
		
		$user = $db->getUserByEmailAndPassword($email, $password);
		if($user != false){
			$response["error"] = false;
			$response["uuid"] = $user["unique_id"];
			$response["user"]["nama"] = $user["nama"];
			$response["user"]["email"] = $user["email"];
			echo json_encode($response);
		}else{
			$response["error"] = true;
			$response["error_msg"] = "Login gagal. password/email salah";				
            echo json_encode($response);
		}
	}else{
		$response["error"] = true;
		$response["error_msg"] = "Parameter (Email/Password) ada yang kurang";
		echo json_encode($response);
	}
?>