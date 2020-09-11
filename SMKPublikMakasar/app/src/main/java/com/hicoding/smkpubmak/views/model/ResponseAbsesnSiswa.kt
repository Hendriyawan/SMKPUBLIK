package com.hicoding.smkpubmak.views.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseAbsesnSiswa(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String
) : Parcelable