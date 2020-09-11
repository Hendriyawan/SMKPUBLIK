package com.hicoding.smkpubmak.views.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestRegisterSiswa(
    var username : String,
    var nama : String,
    var password : String,
    var photo : String,
    var kelas : String,
    var jurusan : String,
    var nisn : Int,
    var email : String
) : Parcelable