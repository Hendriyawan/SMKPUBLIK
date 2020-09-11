<?php
include './upload/uploader.php';
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $extension = $_POST['extension'];
    $file = $_POST['file'];
    $random = randomWord(20);

    //check extension file
    $path = "upload/";
    if($extension == '.jpg' || $extension == '.png'){
        $path .= "images/".$random.$extension;
    } else {
        $path .= "files/".$random.$extension;
    }
    
    //$host = gethostname();
    //$ip = gethostbyname($host);

    $actualPath = "http://192.168.43.42/smkpublik/$path";

    $success_created = file_put_contents($path, base64_decode($file));
    if($success_created){
        header("Content-Type: application/json");
        $response["success"] = true;
        $response["message"] = "File berhasil diupload !";
        $respose["file"] = $actualPath;
        echo json_encode($response);
    } else {
        header("Content-Type: application/json");
        $response["success"] = false;
        $response["messsage"] = "File gagal diupload !";
        echo json_encode($response);
    }
}
?>