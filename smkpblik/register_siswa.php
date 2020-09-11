<?php
include './connection/connection.php';
include './upload/uploader.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $username = $_POST['username'];
    $nama = $_POST['nama'];
    $password = md5($_POST['password']);
    $photo = $_POST['photo'];
    $kelas = $_POST['kelas'];
    $jurusan = $_POST['jurusan'];
    $nisn = $_POST['nisn'];
    $email = $_POST['email'];

    $exist = isUsernameEmailExist($connect, $username, $email);
    if($exist){
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["message"] = "Username atau email sudah terdaftar !.";
        echo json_encode($response);

    } else {
        $query = "INSERT INTO tabel_siswa(id, username, nama, password, photo, kelas, jurusan, nisn, email) VALUES (NULL, '$username', '$nama', '$password', '$photo', '$kelas', '$jurusan', '$nisn', '$email')";
        $sql = mysqli_query($connect, $query);

        if($sql){
            header("Content-Type: application/json");
            $response["success"] = true;
            $response["id"] = mysqli_insert_id($connect);
            $response["message"] = "Berhasil mendaftar !";
            $response["username"] = $username;
            $response["nama"] = $nama;
            $response["password"] = $password;
            $response["email"] = $email;
            $response["user_as"] = "s";
            echo json_encode($response);

        } else {
            header("Content-Type: application/json");
            $response["success"] = false;
            $response["message"] = "Gagal mendaftar";
            echo json_encode($response);
            
        }
        mysqli_close($connect);
    }
}

function isUsernameEmailExist($connect, $username, $email){
    $query = "SELECT * FROM tabel_siswa WHERE username='$username' AND email='$email'";
    $result = mysqli_query($connect, $query);
    if(mysqli_num_rows($result) >0){
        return true;
    }
    return false;
}
?>
