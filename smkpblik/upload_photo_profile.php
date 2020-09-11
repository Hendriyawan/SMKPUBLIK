<?php
include './upload/uploader.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $photo = $_POST['photo'];
    $filename = $_POST['filename'];

    $host = gethostname();
    $ip = gethostbyname($host);

    $random = randomWord(20);
    $path = "upload/photo/".$random."_".$filename;
    $actualPath = "http://".$ip."/smkpublik/$path";

    $success_created = file_put_contents($path, base64_decode($photo));
    if($success_created){
        header("Content-Type: application/json");
        $response["success"] = true;
        $response["message"] = "Photo berhasil diupload !";
        $response["image"] = $actualPath;
        echo json_encode($response);
        
    } else {
        $response["success"] = false;
        $response["message"] = "Photo gagal diupload !";
        echo json_encode($response);
    }
}
?>