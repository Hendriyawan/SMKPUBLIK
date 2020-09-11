package com.hicoding.smkpubmak.views.activity.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseUploadPhotoProfile(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("image") val image: String
) : Parcelable