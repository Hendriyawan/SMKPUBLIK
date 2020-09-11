<?php
include './connection/connection.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id_siswa = $_POST['id_siswa'];
    $nama = $_POST['nama'];
    $kelas = $_POST['kelas'];
    $jurusan = $_POST['jurusan'];
    $checkin = $_POST['checkin'];
    $checkout = $_POST['checkout'];
    $nisn = $_POST['nisn'];
    $date = $_POST['date'];
    $latlong = $_POST['latlong'];

    $query = "INSERT INTO tabel_absen_siswa (id, id_siswa, nama, kelas, jurusan, checkin, checkout, nisn, date, latlong) VALUES (NULL, '$id_siswa', '$nama', '$kelas', '$jurusan', '$checkin', '$checkout', '$nisn', '$date', '$latlong');";
    $sql = mysqli_query($connect, $query);

    if($sql){
        header("Conntent-Type: application/json");
        $response["success"] = true;
        $response["message"] = "Berhasil absen.";
        echo json_encode($response);
        
    } else {
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["message"] = "Gagal absen.";
        echo json_encode($response);
    }
    mysqli_close($connect);
}
?>