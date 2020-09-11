package com.hicoding.smkpubmak.views.activity.signup

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.hicoding.smkpubmak.views.global.Constants
import com.hicoding.smkpubmak.views.global.EndPoint
import com.hicoding.smkpubmak.views.model.RequestRegisterGuru
import com.hicoding.smkpubmak.views.model.RequestRegisterSiswa
import com.hicoding.smkpubmak.views.model.RequestSaveUser

class SignUpPresenter(private var mainView: SignUpView.MainView) : SignUpView.MainPresenter {
    override fun signUpGuru(requestRegisterGuru: RequestRegisterGuru) {
        requestRegisterGuru.apply {
            mainView.onStartProgress()
            AndroidNetworking.post(EndPoint.REGISTER_GURU)
                .addBodyParameter("username", this.username)
                .addBodyParameter("nama", this.nama)
                .addBodyParameter("password", this.password)
                .addBodyParameter("photo", requestRegisterGuru.photo)
                .addBodyParameter("wali_kelas", this.wali_kelas)
                .addBodyParameter("nuptk", this.nuptk.toString())
                .addBodyParameter("email", this.email)
                .setPriority(Priority.MEDIUM)
                .setTag(Constants.TAG_REGISTER_GURU)
                .build()
                .getAsObject(
                    ResponseRegister::class.java,
                    object : ParsedRequestListener<ResponseRegister> {
                        override fun onResponse(response: ResponseRegister) {
                            mainView.onStopProgress()
                            if (response.success) {
                                mainView.onSignUpSuccess(response)
                            } else {
                                mainView.onFailure(response.message)
                            }
                        }

                        override fun onError(anError: ANError?) {
                            mainView.onStopProgress()
                            mainView.onFailure(anError?.message!!)
                        }
                    })
        }
    }

    override fun signUpSiswa(requestRegisterSiswa: RequestRegisterSiswa) {
        requestRegisterSiswa.apply {
            mainView.onStartProgress()
            AndroidNetworking.post(EndPoint.REGISTER_SISWA)
                .addBodyParameter("username", this.username)
                .addBodyParameter("nama", this.nama)
                .addBodyParameter("password", this.password)
                .addBodyParameter("photo", requestRegisterSiswa.photo)
                .addBodyParameter("kelas", this.kelas)
                .addBodyParameter("jurusan", this.jurusan)
                .addBodyParameter("nisn", this.nisn.toString())
                .addBodyParameter("email", this.email)
                .setPriority(Priority.MEDIUM)
                .setTag(Constants.TAG_REGISTER_SISWA)
                .build()
                .getAsObject(
                    ResponseRegister::class.java,
                    object : ParsedRequestListener<ResponseRegister> {
                        override fun onResponse(response: ResponseRegister) {
                            mainView.onStopProgress()
                            if (response.success) {
                                mainView.onSignUpSuccess(response)
                            } else {
                                mainView.onFailure(response.message)
                            }
                        }

                        override fun onError(anError: ANError?) {
                            mainView.onStopProgress()
                            mainView.onFailure(anError?.message!!)
                        }
                    })

        }
    }

    override fun uploadPhoto(photo: String, filename: String) {
        mainView.onStartProgress()
        AndroidNetworking.post(EndPoint.UPLOAD_PHOTO_PROFILE)
            .addBodyParameter("photo", photo)
            .addBodyParameter("filename", filename)
            .setPriority(Priority.MEDIUM)
            .setTag(Constants.TAG_UPLOAD_PHOTO_PROFILE)
            .build()
            .getAsObject(
                ResponseUploadPhotoProfile::class.java,
                object : ParsedRequestListener<ResponseUploadPhotoProfile> {
                    override fun onResponse(response: ResponseUploadPhotoProfile) {
                        mainView.onStopProgress()
                        if (response.success) {
                            mainView.onSuccessUploadPhotoProfile(response.image)
                        } else {
                            mainView.onFailure(response.message)
                        }
                    }

                    override fun onError(anError: ANError?) {
                        mainView.onStopProgress()
                        mainView.onFailure(anError?.message!!)
                    }
                })
    }

    override fun saveUser(requestUser: RequestSaveUser) {
        requestUser.apply {
            mainView.onStartProgress()
            AndroidNetworking.post(EndPoint.SAVE_USER)
                .addBodyParameter("id", this.id)
                .addBodyParameter("username", this.username)
                .addBodyParameter("nama", this.nama)
                .addBodyParameter("password", this.password)
                .addBodyParameter("email", this.email)
                .addBodyParameter("user_as", this.userAs)
                .setPriority(Priority.MEDIUM)
                .setTag(Constants.TAG_SAVE_USER)
                .build()
                .getAsObject(
                    ResponseSaveUser::class.java,
                    object : ParsedRequestListener<ResponseSaveUser> {
                        override fun onResponse(response: ResponseSaveUser) {
                            mainView.onStopProgress()
                            if (response.success) {
                                mainView.onSuccessSaveUser(response)
                            } else {
                                mainView.onFailure(response.message)
                            }
                        }

                        override fun onError(anError: ANError?) {
                            mainView.onStopProgress()
                            mainView.onFailure(anError?.message!!)
                        }
                    })
        }
    }
}