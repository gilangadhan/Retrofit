<?php
	require_once 'include/DB_Function.php';
	
	$db =  new DB_Function();
	
	$response = array("error" => FALSE);
	
	if(isset($_POST['email']) && isset($_POST['password']) && isset($_POST['nama'])){
		$email = $_POST['email'];
		$password = $_POST['password'];
		$nama = $_POST['nama'];
		
		if($db->isUserExisted($email)){
			$response["error"] = true;
			$response["error_msg"] = "User telah ada dengan email " . $email;
			echo json_encode($response);
		} else{
			$user = $db->simpanUser($nama, $email, $password);
			if($user){
				$response["error"] = false;
				$response["uuid"] = $user["unique_id"];
				$response["user"]["nama"] = $user["nama"];
				$response["user"]["email"] = $user["email"];
				echo json_encode($response);
			}else{
				$response["error"] = true;
				$response["error_msg"] = "Terjadi kesalahan saat melakukan registrasi";
				echo json_encode($response);
			}
		}
	}else{
		$response["error"] = true;
		$response["error_msg"] = "Parameter (nama, password, email) ada yang kurang";
		echo json_encode($response);
	}
?>