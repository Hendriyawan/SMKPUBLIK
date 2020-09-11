<?php
include './upload/uploader.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $extension = $_POST["extension"];
    $image = $_POST['image'];
    $random = randomWord(20);

    $path = "upload/images/".$random.$extension;

    $host = gethostname();
    $ip = gethostbyname($host);
    $actualPath = "http://".$ip."/smkpublik/$path";

    $success_created = file_put_contents($path, base64_decode($image));
    if($success_created){
        header("Content-Type: application/json");
        $response["success"] = true;
        $response["message"] = "Gambar berhasil diupload !";
        $response["image"] = $actualPath;
        echo json_encode($response);
    } else {
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["message"] = "Gambar gagal diupload !";
        echo json_encode($response);
    }
}
?>
