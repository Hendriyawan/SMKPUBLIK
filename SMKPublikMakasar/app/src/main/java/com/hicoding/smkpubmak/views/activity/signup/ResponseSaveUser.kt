package com.hicoding.smkpubmak.views.activity.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSaveUser(
    @SerializedName("success") var success: Boolean,
    @SerializedName("id") var id: String,
    @SerializedName("message") var message : String,
    @SerializedName("username") var username: String,
    @SerializedName("nama") var nama: String,
    @SerializedName("email") var email: String,
    @SerializedName("user_as") var userAs: String
) : Parcelable