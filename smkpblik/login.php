<?php
include './connection/connection.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
	$username = $_POST['username'];
	$password = md5($_POST['password']);

	$query = "SELECT * FROM tabel_user WHERE username='$username' AND password='$password'";
	$login = mysqli_query($connect, $query);
	$validate = mysqli_num_rows($login);

	if($validate > 0){
		while($row = mysqli_fetch_object($login)){
			header("Content-Type: application/json");
			$response["success"] = true;
			$response["message"] = "Berhasil login.";
			$response["id"] = $row->id;
			$response["username"] = $row->username;
			$response["nama"] = $row->nama;
			$response["email"] = $row->email;
			$response["user_as"] = $row->user_as;
			echo json_encode($response);
		}
			
	} else {
		header("Content-Type: application/json");
		$response["success"] = false;
		$response["message"] = "Gagal login";
		$response["password"] = md5($password);
		echo json_encode($response);
	}
	mysqli_close($connect);
}
?>