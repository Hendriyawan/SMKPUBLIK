<?php
include './connection/connection.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST['id'];
    $username = $_POST['username'];
    $nama = $_POST['nama'];
    $password = $_POST['password'];
    $email = $_POST['email'];
    $user_as = $_POST['user_as'];

    $query = "INSERT INTO tabel_user(id, username, nama, password, email, user_as) VALUES ($id, '$username', '$nama', '$password', '$email', '$user_as')";
    $sql = mysqli_query($connect, $query);

    if($sql){
        $tabel = "";

        if($user_as == "t"){
            $tabel .= "tabel_guru";
        } else {
            $tabel .= "tabel_siswa";
        }

        $get_result = "SELECT * FROM $tabel WHERE id='$id'";
        $result = mysqli_query($connect, $get_result);
        
        while($row = mysqli_fetch_object($result)){
            header("Content-Type: application/json");
            $response["success"] = true;
            $response["id"] = $id;
            $response["message"] = "Data user berhasil disimpan.";
            $response["username"] = $username;
            $response["nama"] = $nama;
            $response["email"] = $email;
            $response["user_as"] = $user_as;
            echo json_encode($response);
        }
    } else {
        header("Content-Type: application/json");
        $response["success"] = true;
        $response["message"] = "Gagal menyimpan data user. ";
        echo json_encode($response);
    }
    mysqli_close($connect);
}
?>