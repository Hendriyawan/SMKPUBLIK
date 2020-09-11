<?php
include './connection/connection.php';

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $judul = $_POST['judul'];
    $deskripsi = $_POST['deskripsi'];
    $oleh = $_POST['oleh'];
    $isi = $_POST['isi'];
    $lampiran = $_POST['lampiran'];
    $gambar = $_POST['gambar'];
    $tanggal = $_POST['tanggal'];

    $query = "INSERT INTO tabel_materi(id, judul, deskripsi, oleh, isi, lampiran, gambar, tanggal) VALUES (NULL, '$judul', '$deskripsi', '$oleh', '$isi', '$lampiran', '$gambar', '$tanggal')";
    $sql = mysqli_query($connect, $query);

    if($sql){
        header("Content-Type: application/json");
        $response["success"] = true;
        $response["message"] = "Materi berhasil diupload !";
        echo json_encode($response);
        
    } else {
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["message"] = "Materi gagal diupload !";
        echo json_encode($response);
    }

    mysqli_close($connect);
}

?>
