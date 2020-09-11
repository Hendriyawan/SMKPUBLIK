package com.hicoding.smkpubmak.views.activity.signup

import com.hicoding.smkpubmak.views.model.RequestRegisterGuru
import com.hicoding.smkpubmak.views.model.RequestRegisterSiswa
import com.hicoding.smkpubmak.views.model.RequestSaveUser

class SignUpView {
    interface MainPresenter {

        fun uploadPhoto(photo : String, filename : String)
        fun signUpGuru(requestRegisterGuru: RequestRegisterGuru)
        fun signUpSiswa(requestRegisterSiswa: RequestRegisterSiswa)
        fun saveUser(requestUser : RequestSaveUser)
    }

    interface MainView {
        fun onStartProgress()
        fun onStopProgress()
        fun onSignUpSuccess(response: ResponseRegister)
        fun onSuccessUploadPhotoProfile(image : String)
        fun onSuccessSaveUser(response: ResponseSaveUser)
        fun onFailure(message: String)
    }
}