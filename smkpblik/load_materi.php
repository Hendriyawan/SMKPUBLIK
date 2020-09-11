<?php
include './connection/connection.php';
if($_SERVER['REQUEST_METHOD'] == 'GET'){
    $query = "SELECT * FROM tabel_materi ORDER BY judul ASC";
    $sql = mysqli_query($connect, $query);

    $materi = array();
    while ($row = mysqli_fetch_array($sql)){
        array_push($materi, array(
            'id'=>$row[0],
            'judul'=>$row[1],
            'deskripsi'=>$row[2],
            'oleh'=>$row[3],
            'isi'=>$row[4],
            'file'=>$row[5],
            'gambar'=>json_encode($row[6]),
            'tanggal'=>$row[7]
        ));
    }
    header("Content-Type: application/json");
    $response["success"] = true;
    $response["materi"] = $materi;
    echo json_encode($response);
    mysqli_close($connect);
}

?>
