<?php
include './connection/connection.php';
include './upload/uploader.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $username = $_POST['username'];
    $nama = $_POST['nama'];
    $password = md5($_POST['password']);
    $photo = $_POST['photo'];
    $wali_kelas = $_POST['wali_kelas'];
    $nuptik = $_POST['nuptik'];
    $email = $_POST['email'];

    $exist = isUsernameEmailExists($username, $email);
    if($exist){
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["message"] = "Username atau email sudah terdaftar !";
        echo json_encode($response);

    } else {
        $query = "INSERT INTO tabel_guru(id, username, nama, password, photo, wali_kelas, nuptik, email) VALUES(NULL, '$username', '$nama', '$password', '$photo', '$wali_kelas', '$nuptik', '$email')";
        $sql = mysqli_query($connect, $query);

        if($sql){
            header("Content-Type: application/json");
            $response["success"] = true;
            $response["id"] = mysqli_insert_id($connect);
            $response["message"] = "Berhasil mendaftar";
            $response["username"] = $username;
            $response["nama"] = $nama;
            $response["password"] = $password;
            $response["email"] = $email;
            $response["user_as"] = "t";
            echo json_encode($response);

        } else {
            header("Content-Type: application/json");
            $response["success"] = false;
            $response["message"] ="Gagal mendaftar, kesalahan upload gambar !";
            echo json_encode($response);
            
        }
        mysqli_close($connect);
    }
}

//here check username and email has registered
function isUsernameEmailExists($username, $email){
    $query = "SELECT * FROM tabel_guru WHERE username='$username' AND email='$email'";
    $result = mysqli_query($connect, $query);
    if(mysqli_num_rows($result) > 0){
        return true;
    }
    return false;
}
?>