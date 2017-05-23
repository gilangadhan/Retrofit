<?php
	require_once 'include/DB_Function.php';
	
	$db =  new DB_Function();
	
	$response = array("error" => FALSE);
	
	if(
	isset($_GET['id']) && 
	isset($_GET['uuid']) && 
	isset($_GET['jenis_pohon']) && 
	isset($_GET['usia_pohon']) && 
	isset($_GET['kondisi_pohon']) && 
	isset($_GET['latitude']) && 
	isset($_GET['longitude']) && 
	isset($_GET['foto_pohon']) && 
	isset($_GET['keterangan']) ){
		$id = $_GET['id'];
		$uuid = $_GET['uuid'];
		$jenis_pohon = $_GET['jenis_pohon'];
		$usia_pohon = $_GET['usia_pohon'];
		$kondisi_pohon = $_GET['kondisi_pohon'];
		$latitude = $_GET['latitude'];
		$longitude = $_GET['longitude'];
		$foto_pohon = $_GET['foto_pohon'];
		$keterangan = $_GET['keterangan'];
		$tgl = date('Y-m-d');
		
		
		$pohon = $db->simpanPohon($id,$uuid, $jenis_pohon, $usia_pohon,$kondisi_pohon, $latitude, $longitude, $foto_pohon, $keterangan, $tgl);
		if($pohon){
			$response["error"] = false;
			$response["pohon"]["id"] = $pohon["id"];
			$response["pohon"]["uuid"] = $pohon["uuid"];
			$response["pohon"]["jenis_pohon"] = $pohon["jenis_pohon"];
			$response["pohon"]["usia_pohon"] = $pohon["usia_pohon"];
			$response["pohon"]["latitude"] = $pohon["latitude"];
			$response["pohon"]["longitude"] = $pohon["longitude"];
			$response["pohon"]["foto_pohon"] = $pohon["foto_pohon"];
			$response["pohon"]["tgl"] = $pohon["tgl"];
			$response["pohon"]["keterangan"] = $pohon["keterangan"];
			echo json_encode($response);
		}else{
			$response["error"] = true;
			$response["error_msg"] = "Terjadi kesalahan saat melakukan registrasi";
			echo json_encode($response);
		}
		
	}else{
		$response["error"] = true;
		$response["error_msg"] = "Parameter (nama, password, email) ada yang kurang";
		echo json_encode($response);
	}
?>