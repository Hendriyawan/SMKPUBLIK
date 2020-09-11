package com.hicoding.smkpubmak.views.model

data class RequestAbsensiSiswa(
    val id_siswa: Int,
    val nama: String,
    val kelas: String,
    val jurusan: String,
    val checkin: Double,
    val checkout: Double,
    val nisn: Int,
    val data: String
)