package com.hicoding.smkpubmak.views.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestSaveUser(
    var id: String,
    var username: String,
    var nama: String,
    var password: String,
    var email: String,
    var userAs: String
) : Parcelable