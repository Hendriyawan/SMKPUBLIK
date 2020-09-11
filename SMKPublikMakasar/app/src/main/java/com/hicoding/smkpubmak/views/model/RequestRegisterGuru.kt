package com.hicoding.smkpubmak.views.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestRegisterGuru(
    var username: String,
    var nama: String,
    var password: String,
    var photo: String,
    var wali_kelas: String,
    var nuptk: Int,
    var email: String
) : Parcelable