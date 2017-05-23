<?php 
class DB_Function{
	private $conn;
	
	//constructor
	function __construct(){
		require_once 'DB_Connect.php';
		$db = new DB_Connect();
		$this->conn = $db->connect();
	}
	
	//destructor
	function __destruct(){
	}
	
	//function untuk menyimpan
	public function simpanUser($nama, $email, $password){
		$uuid = uniqid('', true);
		$hash = $this->hashSSHA($password);
		$encrypted_password = $hash["encrypted"]; //ecrypted password
		$salt = $hash['salt']; //salt
		
		$stmt = $this->conn->prepare("insert into tbl_user(unique_id, nama, email, encrypted_password, salt) values (?, ?, ?, ?, ?)");
		$stmt->bind_param("sssss", $uuid, $nama, $email, $encrypted_password, $salt);
		$result = $stmt->execute();
		$stmt->close();
		
		//cek jika sudah sukses
		if($result){
			$stmt= $this->conn->prepare("select * from tbl_user where email = ?");
			$stmt->bind_param("s", $email);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			return $user;
		}else{
			return false;
		}
	}
	
	//login
	
	public function getUserByEmailAndPassword($email, $password){
		$stmt = $this->conn->prepare("select * from tbl_user where email = ?");
		$stmt->bind_param("s", $email);
		
		if($stmt->execute()){
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			
			//verifikasi password user
			$salt = $user['salt'];
			$encrypted_password = $user['encrypted_password'];
			$hash = $this->checkhashSSHA($salt, $password);
			
			//cek password jika sesuai
			if ($encrypted_password == $hash){
				//autentikasi user berhasil
				return $user;
			}
		}else{
			return null;
		}
	}
	
	//cek untuk user ada atau tidak
	public function isUserExisted($email){
		$stmt = $this->conn->prepare("select * from tbl_user where email  = ?");
		$stmt->bind_param("s", $email);
		
		$stmt->execute();
		$stmt->store_result();
		if($stmt->num_rows>0){
			$stmt->close();
			return true;
		}else{
			$stmt->close();
			return false;
		}
	}
	
	//encrpting password 
	public function hashSSHA($password){
		$salt = sha1(rand());
		$salt = substr($salt, 0, 10);
		$encrypted = base64_encode(sha1($password.$salt, true).$salt);
		$hash = array("salt"=> $salt, "encrypted"=> $encrypted);
		return $hash;
	}
	
	//descrypting password
	public function checkhashSSHA($salt, $password){
		$hash= base64_encode(sha1($password.$salt, true).$salt);
		return $hash;
	}
	
	//function untuk menyimpan
	public function simpanPohon($id,$uuid, $jenis_pohon, $usia_pohon,$kondisi_pohon, $latitude, $longitude, $foto_pohon, $keterangan, $tgl){
		$stmt = $this->conn->prepare("
		INSERT INTO `tbl_pohon` (`id`, `uuid`, `tgl`, `jenis_pohon`, `usia_pohon`, `kondisi_pohon`, `latitude`, `longitude`, `foto_pohon`, `keterangan`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		$stmt->bind_param("ssssssssss", $id,$uuid, $tgl, $jenis_pohon, $usia_pohon, $kondisi_pohon, $latitude, $longitude, $foto_pohon ,$keterangan);
		$result = $stmt->execute();
		$stmt->close();
		
		//cek jika sudah sukses
		if($result){
			$stmt= $this->conn->prepare("select * from tbl_pohon where id = ?");
			$stmt->bind_param("s", $id);
			$stmt->execute();
			$user = $stmt->get_result()->fetch_assoc();
			$stmt->close();
			return $user;
		}else{
			return false;
		}
	}
}