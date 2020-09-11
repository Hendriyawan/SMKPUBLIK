<?php

function randomWord($length){
    $filename = '';
    $keys = array_merge(range(0, 9), range('a', 'z'));
    for ($i = 0; $i < $length; $i++){
        $filename .= $keys[array_rand($keys)];
    }
    return $filename;
}
?>