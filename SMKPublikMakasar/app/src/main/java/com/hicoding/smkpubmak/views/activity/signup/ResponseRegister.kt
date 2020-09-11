package com.hicoding.smkpubmak.views.activity.signup

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/*
$response["success"] = true;
                $response["message"] = "Berhasil mendaftar";
                $response["username"] = $username;
                $response["password"] = $password;
                $response["user_as"] = "t";
 */
@Parcelize
data class ResponseRegister(
    @SerializedName("success") val success: Boolean,
    @SerializedName("id") val id: String,
    @SerializedName("message") val message: String,
    @SerializedName("username") val username: String,
    @SerializedName("nama") val nama : String,
    @SerializedName("password") val password: String,
    @SerializedName("email") val email :String,
    @SerializedName("user_as") val userAs: String
) : Parcelable